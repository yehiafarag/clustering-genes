/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import java.util.List;

/**
 *
 * @author Yehia Mokhtar
 */
public class ClassBean {

    private List<GeneBean> objectSet;
    private boolean object;

    public void setC1(ClassBean c1) {
        this.c1 = c1;
    }

    public void setC2(ClassBean c2) {
        this.c2 = c2;
    }
    private ClassBean c1;

    public ClassBean getC1() {
        return c1;
    }

    public ClassBean getC2() {
        return c2;
    }
    private ClassBean c2;


    public void setObject(boolean object) {
        this.object = object;
    }

    public boolean isObject() {
        return object;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setObjectSet(List<GeneBean> objectSet) {
        this.objectSet = objectSet;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public List<GeneBean> getObjectSet() {
        return objectSet;
    }

    public double getValue() {
        return value;
    }
    private double value;
    private String id;
    @Override
    public String toString()
    {
        return id;
    }

}
