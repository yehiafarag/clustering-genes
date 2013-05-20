/*
 * this class to read the score matrix from text file
 *
 */

package com.model.scorematrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yehia
 */
public class ScoreMatrixReader {

        public double getScore(char a, char b, String FilePath)throws IOException
        {
            double score = 0;
            File file = new File(FilePath);//get the file
            ArrayList<Object> objectList = readTextFile(file);//get data from the file
            HashMap<String,Double> ScoreMatrix =(HashMap<String,Double>) objectList.get(0);
            Map<Character,Integer> letterMap =(Map<Character,Integer>) objectList.get(1);
            int i = letterMap.get(b);
            int j = letterMap.get(a);
            String key = String.valueOf(i)+","+String.valueOf(j);
            if(ScoreMatrix.get(key) == null)
                 key= String.valueOf(j)+","+String.valueOf(i);
            score = ScoreMatrix.get(key);
            return score;
        }
        private ArrayList<Object> readTextFile(File file) throws IOException//function to extract data from txt file
	{
                Map<Character,Integer> letterMap = new HashMap<Character,Integer>();//letters mapping ds
	        Map<String,Double> ScoreMatrix = new HashMap<String,Double>();
                ArrayList<Object> objectList = new ArrayList<Object>();
		FileReader fr = new FileReader(file);
		BufferedReader bufRdr  = new BufferedReader(fr);
		String line = null;
		int row = 0;
		int index=0;
		int i = 1;
		int j = 1;

		while((line = bufRdr.readLine()) != null && row < 1000)
		{
                    line = line.toUpperCase();
                     String[] strArr = line.trim().split("\\s");

			if(index == 0)//init the letter mapping table
			{
				for(int x=0;x<strArr.length;x++){
					letterMap.put(strArr[x].charAt(0),(x+1));
				}
				index++;
				continue;
			}

			//fille the table with the scores
			for(int x=1;x<strArr.length;x++)
			{
				ScoreMatrix.put(String.valueOf(i)+","+String.valueOf(j),Double.valueOf(strArr[x]));
				j++;
			}
			i++;//increase i for column value
			j = 1;//reset value of j to 0

                      
		}
		objectList.add(ScoreMatrix);
                objectList.add(letterMap);
		return objectList;
	}


}
