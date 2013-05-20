/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globalalignment;



/**
 *
 * @author Yehia
 */
public class ExperimentBean {
    private double gOpen;
    private double gExtend;
    private String sequenceQ;
    private String sequenceD;

    private String scoreMatrixPath;

    public void setGOpen(double gOpen)
    {
        this.gOpen = gOpen;
    }
    public double getGOpen()
    {
        return this.gOpen;
    }
    public void setGExtend(double gExtend)
    {
        this.gExtend = gExtend;
    }
    public double getGExtend()
    {
        return this.gExtend;
    }
    public void setSequenceQ(String sequenceQ)
    {
        this.sequenceQ = sequenceQ;
    }
    public String getSequenceQ()
    {
        return this.sequenceQ;
    }
    public void setSequenceD(String sequenceD)
    {
        this.sequenceD = sequenceD;
    }
    public String getSequenceD()
    {
        return this.sequenceD;
    }
    public void setScoreMatrixPath(String scoreMatrixPath)
    {
        this.scoreMatrixPath = scoreMatrixPath;
    }
    public String getScoreMatrixPath()
    {
        return this.scoreMatrixPath;
    }


}
