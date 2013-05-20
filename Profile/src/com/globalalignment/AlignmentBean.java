/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.globalalignment;

/**
 *
 * @author Yehia
 */
public class AlignmentBean {
    private String q ;
    private String d;
    private double score;
    public void setQ(String q)
    {
        this.q = q;
    }
    public String getQ()
    {
        return q;
    }
     public void setD(String d)
    {
        this.d = d;
    }
    public String getD()
    {
        return d;
    }
     public void setScore(double score)
    {
        this.score = score;
    }
    public double getScore()
    {
        return score;
    }


}
