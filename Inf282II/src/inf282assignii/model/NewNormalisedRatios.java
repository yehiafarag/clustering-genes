/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf282assignii.model;

import inf282assignii.model.beans.GeneIntensitie;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Yehia Mokhtar
 */
public class NewNormalisedRatios {


    public  Map<Integer,String> calculateNewNormalisedRatios(double d, GeneIntensitie[] giList)
    {
          DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
          otherSymbols.setGroupingSeparator('.');
          DecimalFormat decim=  new DecimalFormat("#.##",otherSymbols);
        Map<Integer,String> normalizedTable = new TreeMap<Integer, String>();
        int index =0;
        for(GeneIntensitie gi:giList)
        {
             String finalValue ="";
            String key = "p` "+(index+1)+" / q` "+(index+1);
            double value = (gi.getP()/gi.getQ())*d;
            if(value== 0.0){
                if(index < 10)
                     finalValue = key+" "+'\t'+"="+'\t'+decim.format(value)+'\t'+"No Differentially Expressed Gene";
                else
                    finalValue =  key+    '\t'+"="+'\t'+decim.format(value)+'\t'+"No Differentially Expressed Gene";
            } else{
                  if(index < 10)
                     finalValue = key+" "+'\t'+"="+'\t'+decim.format(value)+'\t'+"Differentially Expressed Gene";
                else
                    finalValue =  key+    '\t'+"="+'\t'+decim.format(value)+'\t'+"Differentially Expressed Gene";
            }
            normalizedTable.put(index, finalValue);
            index++;

        }
        return normalizedTable;

    }

}
