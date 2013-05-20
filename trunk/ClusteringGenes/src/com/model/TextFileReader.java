/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import com.validator.ValidatFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Yehia Mokhtar
 */
public class TextFileReader {

    //method to validate andextract genes data from txt file  
    private ValidatFile validator = new ValidatFile();
    private  ExperimentBean exp;


 /*
    this method to get theinformation fromtext file
 * path @param  "path to text file"
 * return expermintBean which contain list of geneBeans
 */
    public ExperimentBean readFile(String path)
    {
       int marker =1;
        try{
            File file = new File(path);
            if(! file.exists())
                return null;
            FileReader fr = new FileReader(file);
            BufferedReader bufRdr  = new BufferedReader(fr);
            String line = "";
            int index = 0;
            Map<String,GeneBean> genMap = new HashMap<String, GeneBean>();
            Map<Integer,GeneBean> genIdMap = new TreeMap<Integer, GeneBean>();
            while((line = bufRdr.readLine()) != null )
		{
                    line = line.toUpperCase();
                   
                    String[] strArr = line.trim().split("\\s\\s");
                    if(index == 0){
                       boolean check =  validator.checkFile(strArr);
                       if(!check)
                           return null;
                        index++;
                        continue;
                        
                    }
                  GeneBean gb = new GeneBean();
                  exp = new ExperimentBean(); 
                  if(strArr.length == 17){
                       String s = strArr[0];
                       String[] o= s.split("\\s");
                       gb.setProbeID(o[0]);
                       gb.setGeneID(o[1]);
                       double c4l = Double.valueOf(o[2]);
                       gb.setCORT_4L(c4l);

                           double c4r=0.0;
                    try{
                        c4r = Double.valueOf(strArr[1]);
                    }catch(NumberFormatException nfExp){nfExp.printStackTrace();c4r=0.0;}
                    gb.setCORT_4R(c4r);
                    double c6l = Double.valueOf(strArr[2]);
                    gb.setCORT_6L(c6l);
                    double c6r = 0.0;
                    try{
                   c6r = Double.valueOf(strArr[3]);
                    }catch(NumberFormatException nfExp){nfExp.printStackTrace();c6r=0.0;}
                    gb.setCORT_6R(c6r);

                    double c8l = Double.valueOf(strArr[4]);
                    gb.setCORT_8L(c8l);
                    double c8r = Double.valueOf(strArr[5]);
                    gb.setCORT_8R(c8r);


                     double c4lh = Double.valueOf(strArr[6]);
                    gb.setHIPP_4L(c4lh);
                    double c4rh = Double.valueOf(strArr[7]);
                    gb.setHIPP_4R(c4rh);

                    double c6lh = Double.valueOf(strArr[8]);
                    gb.setHIPP_6L(c6lh);
                    double c6rh = Double.valueOf(strArr[9]);
                    gb.setHIPP_6R(c6rh);

                    double c8lh = Double.valueOf(strArr[10]);
                    gb.setHIPP_8L(c8lh);
                    double c8rh = Double.valueOf(strArr[11]);
                    gb.setHIPP_8R(c8rh);


                    double c4ls = Double.valueOf(strArr[12]);
                    gb.setSTR_4L(c4ls);
                    double c4rs = Double.valueOf(strArr[13]);
                    gb.setSTR_4R(c4rs);

                    double c6ls = Double.valueOf(strArr[14]);
                    gb.setSTR_6L(c6ls);
                    double c6rs = Double.valueOf(strArr[15]);
                    gb.setSTR_6R(c6rs);

                    double c8ls = Double.valueOf(strArr[16]);
                    gb.setSTR_8L(c8ls);
                        
                     }
                    
                     else if(strArr.length == 19)
                    {  gb.setProbeID(strArr[0]);
                    gb.setGeneID(strArr[1]);
                    double c4l = Double.valueOf(strArr[2]);
                    gb.setCORT_4L(c4l);
                         double c4r=0.0;
                    try{
                        c4r = Double.valueOf(strArr[3]);
                    }catch(NumberFormatException nfExp){nfExp.printStackTrace();c4r=0.0;}
                    gb.setCORT_4R(c4r);
                    double c6l = Double.valueOf(strArr[4]);
                    gb.setCORT_6L(c6l);
                    double c6r = 0.0;
                    try{
                   c6r = Double.valueOf(strArr[5]);
                    }catch(NumberFormatException nfExp){nfExp.printStackTrace();c6r=0.0;}
                    gb.setCORT_6R(c6r);

                    double c8l = Double.valueOf(strArr[6]);
                    gb.setCORT_8L(c8l);
                    double c8r = Double.valueOf(strArr[7]);
                    gb.setCORT_8R(c8r);


                     double c4lh = Double.valueOf(strArr[8]);
                    gb.setHIPP_4L(c4lh);
                    double c4rh = Double.valueOf(strArr[9]);
                    gb.setHIPP_4R(c4rh);

                    double c6lh = Double.valueOf(strArr[10]);
                    gb.setHIPP_6L(c6lh);
                    double c6rh = Double.valueOf(strArr[11]);
                    gb.setHIPP_6R(c6rh);

                    double c8lh = Double.valueOf(strArr[12]);
                    gb.setHIPP_8L(c8lh);
                    double c8rh = Double.valueOf(strArr[13]);
                    gb.setHIPP_8R(c8rh);


                    double c4ls = Double.valueOf(strArr[14]);
                    gb.setSTR_4L(c4ls);
                    double c4rs = Double.valueOf(strArr[15]);
                    gb.setSTR_4R(c4rs);

                    double c6ls = Double.valueOf(strArr[16]);
                    gb.setSTR_6L(c6ls);
                    double c6rs = Double.valueOf(strArr[17]);
                    gb.setSTR_6R(c6rs);

                    double c8ls = Double.valueOf(strArr[18]);
                    gb.setSTR_8L(c8ls);

                    
                     }
                    gb.setGeneratedID("G"+marker);
                    
                    genMap.put(gb.getProbeID(), gb);
                    genIdMap.put(marker, gb);
                    marker++;
                     
            }

            if(exp != null){
                exp.setGeneMap(genMap);
                exp.setGeneIdMap(genIdMap);
            }

        }catch(Exception e){e.printStackTrace();}
        return exp;
    }

}
