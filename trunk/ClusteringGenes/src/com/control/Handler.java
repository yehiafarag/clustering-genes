/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.control;

import clusteringgens.ViewResults;
import com.model.AgglomerativeClustering;
import com.model.ClassBean;
import com.model.ExperimentBean;
import com.model.TextFileReader;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author Yehia Mokhtar
 */
public class Handler {
    private TextFileReader reader = new TextFileReader();
    private AgglomerativeClustering aggClust = new AgglomerativeClustering();
  
    public void processData(String path,JLabel errorLabel)
    {
        if(path== null || path.equals(""))
        {
            errorLabel.setVisible(true);
            errorLabel.setText("Not Valid Path");
            return;
        }
       
        ExperimentBean exp = reader.readFile(path);
        if(exp == null)
        {
            errorLabel.setVisible(true);
            errorLabel.setText("This File is Not Valid");
        }
        else
        {
      
          Map<Integer,List<ClassBean>> itrMap= aggClust.performAgglomerativeClustering(exp.getGeneMap());

         
          ViewResults vr =new ViewResults();
          vr.showResults(itrMap,exp);
          vr.setVisible(true);

          

        }





    }


}
