/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Yehia Mokhtar
 */
public class ExperimentBean {

   private Map<String,GeneBean> geneMap;
   private Map<String,Map<Double,List<GeneBean>>>  distanceMap;
   private Map<Integer,GeneBean> geneIdMap;

    public void setGeneIdMap(Map<Integer, GeneBean> geneIdMap) {
        this.geneIdMap = geneIdMap;
    }

    public Map<Integer, GeneBean> getGeneIdMap() {
        return geneIdMap;
    }

    public Map<String,Map<Double,List<GeneBean>>>  getDistanceMap() {
        return distanceMap;
    }

    public void setDistanceMap(Map<String,Map<Double,List<GeneBean>>>  distanceMap) {
        this.distanceMap = distanceMap;
    }

    public void setGeneMap(Map<String, GeneBean> geneMap) {
        this.geneMap = geneMap;
    }

    public Map<String, GeneBean> getGeneMap() {
        return geneMap;
    }

}
