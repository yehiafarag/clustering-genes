/*
 * this class to validate the input data from the user
 * validate G open,G extend
 * the Q sequences
 * the D sequences
 * the score matric file
 */

package com;

import com.model.AlignmentsReader;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Yehia
 */
public class InputValidator {

    AlignmentsReader ar = new AlignmentsReader();
    public boolean validateInput(JTextField jTextField1,JTextArea jTextField2,JLabel wrongFile,JLabel textAreaError,JTextField jTextField3,JTextField jTextField4 )
    {
         int v = 0;
        wrongFile.setText("");
        textAreaError.setText("");
        jTextField1.setBackground(Color.white);
        jTextField2.setBackground(Color.white);

         jTextField3.setBackground(Color.white);
         jTextField4.setBackground(Color.white);
        
        String str1 = jTextField1.getText();
        String str2 = jTextField2.getText();
        int p = 0;
        try{
         p= Integer.valueOf(jTextField3.getText());
         if(p < 1)
             throw new NumberFormatException();
        }catch(NumberFormatException e){
         jTextField3.setBackground(Color.red);
         jTextField3.requestFocusInWindow();
         
        }
        int q = 0;
       try{
         q= Integer.valueOf(jTextField4.getText());
         if(q < 1)
             throw new NumberFormatException();
        }catch(NumberFormatException e){
         jTextField4.setBackground(Color.red);
         jTextField4.requestFocusInWindow();

        }
        

       if(str1.equalsIgnoreCase("Please choose matrix score file") ||str1.equalsIgnoreCase("")){
           jTextField1.setBackground(Color.red);
           wrongFile.setText("Please choose file !");
           v++;

       }
       if(str2.equalsIgnoreCase("")){
           jTextField2.setBackground(Color.red);
           textAreaError.setText("please enter valid data");
           v++;
        }

        if(p > q)
        {
             jTextField3.setBackground(Color.red);
        }


        if(v > 0){
            return false;
        }
        else{
            if(ar.vaidateAlignments(str2, (p-1), (q-1)))
                return true;
            else{
                 jTextField2.setBackground(Color.red);
                 jTextField3.setBackground(Color.red);
                 jTextField4.setBackground(Color.red);
                textAreaError.setText("error in Start and  End Value");
                return false;
            }

        }
    }


    public boolean validateField(JTextField jTextField)
    {
        String str = jTextField.getText();
        if(str == null || str.equals(""))
        {
            jTextField.setBackground(Color.red);
            return false;
        }
        else
        {
             jTextField.setBackground(Color.white);
             return true;
        }
    }

}
