/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globalalignment;

import com.model.scorematrix.Cell;
import java.util.ArrayList;

/**
 *
 * @author Yehia
 */
public class ResultBean {
    private double[][] matrix;
    private AlignmentBean bestAlignment;
    private double gapScore;

    public void setGapScore(double gapScore) {
        this.gapScore = gapScore;
    }

    public double getGapScore() {
        return gapScore;
    }

    public void setBestAlignment(AlignmentBean bestAlignment) {
        this.bestAlignment = bestAlignment;
    }

    public AlignmentBean getBestAlignment() {
        return bestAlignment;
    }
    double finalScore;

    private Cell cornerCell;

    public void setCornerCell(Cell cornerCell) {
        this.cornerCell = cornerCell;
    }

    public Cell getCornerCell() {
        return cornerCell;
    }


    private String profile;
    

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }


     public void setFinalScore(double finalScore)
    {
        this.finalScore = finalScore;
    }
    public double  getFinalScore()
    {
        return  finalScore;
    }
    public void setMatrix(double[][] matrix)
    {
        this.matrix = matrix;
    }
    public double[][]  getMatrix()
    {
        return  matrix;
    }
   

}
