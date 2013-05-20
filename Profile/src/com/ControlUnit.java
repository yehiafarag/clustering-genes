/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.globalalignment.AlignmentComparator;
import com.globalalignment.ExperimentBean;
import com.globalalignment.ResultBean;
import com.model.AlignmentsReader;
import com.model.scorematrix.ScoreMatrixReader;

/**
 *
 * @author Yehia
 */
public class ControlUnit {

    ScoreMatrixReader smr = new ScoreMatrixReader();
    AlignmentsReader ar = new AlignmentsReader();
    ResultBean rb = new ResultBean();

    @SuppressWarnings("CallToThreadDumpStack")
    public ResultBean handelExp(ExpBean exp) {
        exp = ar.read(exp.getAllignments(), exp.getStart(), exp.getEnd(), exp);
        //the controller will read the allignments
        try {
            exp = ar.creatProfileCharProfile(exp);
            rb.setProfile(exp.getProfile());
            rb.setGapScore(exp.getGapScore());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rb;



    }

    @SuppressWarnings("CallToThreadDumpStack")
    public double getScore(ExpBean exp, char a, char b) {
        double score = 0;

        try {
            score = smr.getScore(a, b, exp.getScoreMatrixPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;


    }

    public ResultBean compareAlignment(ExperimentBean exp) {
        AlignmentComparator ac = new AlignmentComparator();
        ResultBean resultbean = ac.getResults(exp);
        return resultbean;

    }
}
