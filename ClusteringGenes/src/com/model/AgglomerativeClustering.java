/*
 * this class is responsable for performing clustring 
 */

package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Yehia Mokhtar
 */
public class AgglomerativeClustering {
    private DistCalculator calc = new DistCalculator();

    public Map<Integer,List<ClassBean>> performAgglomerativeClustering(Map<String,GeneBean>geneMap)
    {
       
       List<ClassBean> C = new ArrayList<ClassBean> ();
       List<ClassBean> C2 = new ArrayList<ClassBean>();
       int iter= 0;
       Map<Integer,List<ClassBean>>  itrMap = new TreeMap<Integer, List<ClassBean> >();
        for(String str:geneMap.keySet())
        {

           ClassBean ci  = new ClassBean();
           GeneBean gb = geneMap.get(str);
           List<GeneBean>objects = new ArrayList<GeneBean>();
           objects.add(gb);
           String key="["+gb.getProbeID()+"]";
           ci.setId(key);
           ci.setObject(true);
           ci.setObjectSet(objects);
           C.add(ci);

        }
        C2.addAll(C);
        itrMap.put(iter, new ArrayList<ClassBean>(C2));
        
        for(int index= 0;index<C.size();index++)
        {
           if(C2.size()==1)
               break;
           iter++;
           ClassBean[] minDistSet= calcDistance(C2);
           ClassBean c1 = minDistSet[0];
           ClassBean c2 = minDistSet[1];
           List<GeneBean>newList = new ArrayList<GeneBean>();
           newList.addAll(c1.getObjectSet());
           newList.addAll(c2.getObjectSet());
           ClassBean c = new ClassBean();
           c.setObject(false);
           c.setId(c1.getId()+c2.getId());

           if(c1.isObject()||c2.isObject())
           {}
            else
           {
                c.setC1(c1);
                c.setC2(c2);
            }
           c.setObjectSet(newList);
           C2.remove(c1);
           C2.remove(c2);
           C2.add(c);

           itrMap.put(iter, new ArrayList<ClassBean>(C2));
                   

        }
        return itrMap;
    }

    private ClassBean[] calcDistance(List<ClassBean> C)
    { 
            Map<Double,ClassBean[]> distTable = new TreeMap<Double,ClassBean[]>();
           
                   
           for (ClassBean cb1 : C) {
            for (ClassBean cb2 : C) {  
                   double distance = 0.0;
                if (cb1.getId().equals(cb2.getId())) {
                } else {
                    if (cb1.isObject() && cb2.isObject()) {
                        distance = calc.calc2ObjectDistance(cb1.getObjectSet().get(0), cb2.getObjectSet().get(0));
                    } else if (cb1.isObject() && !cb2.isObject())//||!cb1.isObject()&&cb2.isObject())
                    {
                        distance = calc.calcDAvgObjectClass(cb2.getObjectSet(), cb1.getObjectSet().get(0));

                    } else if (!cb1.isObject() && cb2.isObject()) {
                        distance = calc.calcDAvgObjectClass(cb1.getObjectSet(), cb2.getObjectSet().get(0));

                    } else if (!cb1.isObject() && !cb2.isObject() && cb1.getC1() == null && cb2.getC1() == null) {
                        distance = calc.calcDAvgClassClass(cb1.getObjectSet(), cb2.getObjectSet());

                    } else if (!cb1.isObject() && !cb2.isObject() && cb1.getC1() != null) {
                        distance = calc.UPGMA(cb1.getC1(), cb1.getC2(), cb2);

                    }
                    distTable.put(distance, new ClassBean[]{cb1, cb2});

                }



            }


        }


            ClassBean[] minDist = new ClassBean[2];
          
            
            for(Double d:distTable.keySet()){  
                int index = 0;
                ClassBean[] cbArr=distTable.get(d);
               for(ClassBean cb:cbArr){
                minDist[index]= cb;
                index++;
                } break; 
            }
            return minDist;

    }

   /* private List<ClassBean> getMinDist(Map<Double,ClassBean[]> distTable)
    {
       // Map<Double,List<GeneBean>> tempMap = new TreeMap<Double, List<GeneBean>>();
        for(String str:distMap.keySet())
        {
            for (String key : ci.keySet())
            {
                if(str.contains(key))//("["+key+",")||str.contains("["+key+",")||str.contains(","+key+",")||str.contains(","+key+"]"))
                {
                    tempMap.putAll(distMap.get(str));

                }
            }



        } 
        List<GeneBean> l = null;
        for(Double key:tempMap.keySet()){
           l = tempMap.get(key);
           break;
        }
         return l;

    }


    *
    *
    private Map<String,Map<Double,List<GeneBean>>> updateDistanceMatr(Map<String,Map<Double,List<GeneBean>>> distMap, List<Map<String,Set<GeneBean>>> C1, List<GeneBean>  l )
    {
        Map<String,Map<Double,List<GeneBean>>> updatedDistMap = new HashMap<String, Map<Double, List<GeneBean>>>();
        updatedDistMap.putAll(distMap);
         for(String str:distMap.keySet())
        {
            for (GeneBean gb : l)
            {
                if(str.contains("["+gb.getProbeID()+"]"))//("["+key+",")||str.contains("["+key+",")||str.contains(","+key+",")||str.contains(","+key+"]"))
                {
                   updatedDistMap.remove(str);
                }
            }

        }
         
         for(Map<String,Set<GeneBean>> sets:C1)
         {
            
            
            for(Set<GeneBean> set:sets.values() )
            {
                if(set.size()==1)///set has object
                {
                    for(GeneBean gb1:set)
                    {
                        boolean test = false;
                         for(GeneBean gb2:l)
                        {
                            if(gb1.getGeneID().equalsIgnoreCase(gb2.getGeneID()))
                            {
                                test = true;
                                break;
                            }

                        }
                        if(test)
                            continue;
                        double avgD = dAvg(distMap, l, gb1);
                        System.out.println(" avg dis is "+avgD);
                        String keyNew = "["+gb1.getProbeID()+"]";
                        for(GeneBean gb2:l)
                        {
                            keyNew = keyNew+"["+gb2.getProbeID()+"]";                    
                        }
                        l.add(gb1);
                        Map<Double,List<GeneBean>> temM = new TreeMap<Double, List<GeneBean>>();
                        temM.put(avgD, l);
                        updatedDistMap.put(keyNew, temM);

                    }
            
                }
                else
                {
                    //class to class dist

                }
            }
        }
       
        return updatedDistMap;
     }
   

    * 
    */

   

}
