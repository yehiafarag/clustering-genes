/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Yehia Mokhtar
 */
public class ExpBean {

    private String profile;
    private String scoreMatrixPath;
    private String sequenceQ;
    private double gapScore;

    public double getGapScore() {
        return gapScore;
    }

    public void setGapScore(double gapScore) {
        this.gapScore = gapScore;
    }

    public void setSequenceQ(String sequenceQ) {
        this.sequenceQ = sequenceQ;
    }

    public String getSequenceQ() {
        return sequenceQ;
    }

    private int start;

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }
    private int end;

    private Map<Integer, List<Character>> positionList;

    public void setFullCharSet(Set<Character> fullCharSet) {
        this.fullCharSet = fullCharSet;
    }

    public void setPositionList(Map<Integer, List<Character>> positionList) {
        this.positionList = positionList;
    }

    public Set<Character> getFullCharSet() {
        return fullCharSet;
    }

    public Map<Integer, List<Character>> getPositionList() {
        return positionList;
    }
    private   Set<Character> fullCharSet ;
    public void setScoreMatrixPath(String scoreMatrixPath) {
        this.scoreMatrixPath = scoreMatrixPath;
    }

    public String getScoreMatrixPath() {
        return scoreMatrixPath;
    }

    public void setAllignments(String allignments) {
        this.allignments = allignments;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAllignments() {
        return allignments;
    }

    public String getProfile() {
        return profile;
    }
    private String allignments;


}
