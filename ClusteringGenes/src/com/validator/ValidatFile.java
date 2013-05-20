/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.validator;

/**
 *
 * @author Yehia Mokhtar
 */
public class ValidatFile {

    //method to validat the text file
    public boolean checkFile(String[] strArr)
    {
        if( strArr != null && strArr.length == 6 && strArr[0].contains("PROBE ID")&&strArr[2].contains("4R STR")&&strArr[5].contains("8L STR"))
            return true;
        else
            return false;
    }

}
