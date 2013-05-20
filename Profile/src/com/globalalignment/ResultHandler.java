/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globalalignment;

import java.util.ArrayList;

/**
 *
 * @author Yehia
 */
public class ResultHandler {

    public ArrayList<AlignmentBean> alignmentFilter(ArrayList<AlignmentBean> ab)
    {
        ArrayList<AlignmentBean> alf = new ArrayList<AlignmentBean>();
        System.out.println(ab.size());
        for(int x=0;x<ab.size();x++)
        {
            AlignmentBean alb= ab.get(x);
           // System.out.println(" d is "+ab.get(x).getD()+"  and length is "+ab.get(x).getD().length());
            if(alb.getD().contains(",")){
                alf.add(alb);
                System.out.println(alb.getD());
            }
        }
        
         
        return alf;

    }
    public double getHighScore(ArrayList<AlignmentBean> alf)
    {
        double score = 0;
        for(AlignmentBean alb:alf)
        {
            if(score < alb.getScore())
                score = alb.getScore();
        }
        return score;

    }

}
