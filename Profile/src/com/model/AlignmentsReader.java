/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import com.ExpBean;
import com.model.scorematrix.ScoreMatrixReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Yehia Mokhtar
 */
public class AlignmentsReader {
    ScoreMatrixReader smr = new ScoreMatrixReader();

    public ExpBean read(String alinments,int start,int end,ExpBean exp)
    {
       String[] alimArr = alinments.split("\\s");
       Map<Integer, List<Character>> positionList = new TreeMap<Integer, List<Character>>();
       Set<Character> fullCharSet = new HashSet<Character>();
       
       for(int index =start; index <end;index++)
       {
            List<Character> l1 = new ArrayList<Character>();
           
            for(String strs :alimArr)
            {
                

                    char a = strs.charAt(index);
                   // if(a != '-')
                    {
                        l1.add(a);
                        //fullCharSet.add(a);
                        }
                
            }
            positionList.put(index, l1);

       }

       positionList = this.filterList(positionList,fullCharSet);
       exp.setFullCharSet(fullCharSet);
       exp.setPositionList(positionList);
       return exp;


    }


    private Map<Integer, List<Character>> filterList(Map<Integer, List<Character>> positionList, Set<Character> fullCharSet)
    {
        Map<Integer, List<Character>> filterdList = new HashMap<Integer, List<Character>>();
        for(int i:positionList.keySet())
        {
            List<Character> filL = new ArrayList<Character>();
            List<Character> l= positionList.get(i);
            int counter = 0;
            for(char c:l)
            {
                if(c == '-'){
                    counter++;
                }
                else{
                    filL.add(c);
                    fullCharSet.add(c);

                }
            }
            if (counter >= ((l.size()+1)/2))
                    ;
            else
                filterdList.put(i, filL);

        }
        return filterdList;


    }

    public  ExpBean creatProfileCharProfile(ExpBean exp)throws  IOException
    {
        Map<Integer,Character> finalProfile = new TreeMap<Integer, Character>();

        ArrayList<Double> negativeScores = new ArrayList<Double>();

        for(int pos:exp.getPositionList().keySet()){
            List<Character> charList = exp.getPositionList().get(pos);
            Map<Character, Double> profCharPos = new HashMap<Character, Double>();
            for(char c: exp.getFullCharSet())
            {
                 double score = this.profCalc(charList, c, exp);
                 profCharPos.put(c, score);
                 if(score < 0)
                    negativeScores.add(score);
            }
            char c = this.getMaxScoreCharacter(profCharPos);
            finalProfile.put(pos, c);

        }

        Double avdGapScore = this.getAvgGapScore(negativeScores);
        exp.setGapScore(avdGapScore);
        StringBuilder sb = new StringBuilder();
        for(char c:finalProfile.values() )
            sb.append(c);
        String profile= sb.toString();
        exp.setProfile(profile);
        return exp;
    }
    
    private double profCalc(List<Character> charList,char a,ExpBean exp) throws  IOException
    {
        int size = charList.size(); 
        double profA = (1.0/Double.valueOf(size)) * this.RbaTrb(exp, charList, a);
        return profA;
    }
    
    
    private double RbaTrb(ExpBean exp,List<Character> charList,char a)throws IOException
    {  double total = 0d;
        for(char c: exp.getFullCharSet()){

           double k =  this.repOfChar(charList, c) * Double.valueOf(smr.getScore(c, a, exp.getScoreMatrixPath()));
           total = total + k;
        }
        return total;
    
    }
    
    
    private double repOfChar(List<Character> charList,char a)
    {
        double repOfA = 0D;
         for(char c:charList)
        {
            if(c == a)
               repOfA++;
        }
         return repOfA;
    }

    private char getMaxScoreCharacter( Map<Character, Double> profCharPos)
    {
        double highScore = -100000000000000000000000000.0D;
        char f = 'a';
        for (char c:profCharPos.keySet())
        {
            if(highScore < profCharPos.get(c))
            {
                highScore = profCharPos.get(c);
                f = c;
            }
        }


        return f;
    }



    public boolean vaidateAlignments(String alinments,int start,int end)
    {

         String[] alinArr = alinments.split("\\s");
         String str = alinArr[0];
         if(str.length()>= (end-1) && start < end && start >= 0)
             return true;
         else
             return false;

    }
    
    private boolean validatSequance(String str)
    {
        int counter = 0;
        for(char c:str.toCharArray())
        {
            if(c == '-')
                counter++;
        }
        if ((counter >= (str.length()/2)))
                return false;
        else return true;
    }

    private double getAvgGapScore(ArrayList<Double> negativeScores)
    {
        double counter = 0d;
        for(double d:negativeScores)
            counter += d;
        double gap =  counter/Double.valueOf(negativeScores.size())*2.0;
        return gap;


    }
}
