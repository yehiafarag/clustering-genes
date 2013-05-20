/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import java.util.List;

/**
 *this class normalise the gene data according to normalised Euclidean metric
 * @author Yehia Mokhtar
 */
public class DistCalculator {

 /*   public Map<String,Map<Double,List<GeneBean>>> distanceBetweenObjects(Map<String,GeneBean> geneMap)
    {
        Map<String,Map<Double,List<GeneBean>>>distanceMap = new HashMap<String,Map<Double,List<GeneBean>>>();
         for(String key1:geneMap.keySet())
        {
              for(String key2:geneMap.keySet())
              {
                  if(key1.equals(key2))
                  {}
                  else
                  {
                        String newKey1 = "["+key1+"]["+key2+"]";
                        String newKey2 = "["+key2+"]["+key1+"]";
                        if(distanceMap.containsKey(newKey1)||distanceMap.containsKey(newKey2))
                        {}
                        else
                        {
                            List l = new ArrayList<GeneBean>();
                            double d = this.calcDistance(geneMap.get(key1), geneMap.get(key2));
                            l.add(geneMap.get(key1));
                            l.add(geneMap.get(key2));
                            Map<Double,List<GeneBean>> tm = new HashMap<Double, List<GeneBean>>();
                            tm.put(d, l);
                            distanceMap.put(newKey1, tm);

                        }

                  }

              }

        }
        return distanceMap;


    }
*/



/*
     public double calc2ObjectDistance(GeneBean g1,GeneBean g2)
    {

       double d1 = g1.getCORT_4L()-g2.getCORT_4L();
       d1 = d1*d1;

       double d4 = g1.getCORT_4R()-g2.getCORT_4R();
       d4 = d4*d4;


       double d = (d1+d4)/2.0;


       d = Math.sqrt(d);
       return d;

    }
 * */












    public double calc2ObjectDistance(GeneBean g1,GeneBean g2)
    {

       double d1 = g1.getCORT_4L()-g2.getCORT_4L();
       d1 = d1*d1;
       double d2 = g1.getCORT_6L()-g2.getCORT_6L();
       d2 = d2*d2;
        double d3 = g1.getCORT_8L()-g2.getCORT_8L();
       d3 = d3*d3;
       double d4 = g1.getCORT_4R()-g2.getCORT_4R();
       d4 = d4*d4;
       double d5 = g1.getCORT_6R()-g2.getCORT_6R();
       d5 = d5*d5;
       double d6 = g1.getCORT_8R()-g2.getCORT_8R();
       d6 = d6*d6;
       double d7 = g1.getHIPP_4L()-g2.getHIPP_4L();
       d7 = d7*d7;
       double d8 = g1.getHIPP_6L()-g2.getHIPP_6L();
       d8 = d8*d8;
       double d9 = g1.getHIPP_8L()-g2.getHIPP_8L();
       d9 = d9*d9;
       double d10 = g1.getHIPP_4R()-g2.getHIPP_4R();
       d10 = d10*d10;
       double d11 = g1.getHIPP_6R()-g2.getHIPP_6R();
       d11 = d11*d11;
       double d12 = g1.getHIPP_8R()-g2.getHIPP_8R();
       d12 = d12*d12;
       double d13 = g1.getSTR_4L()-g2.getSTR_4L();
       d13 = d13*d13;
       double d14 = g1.getSTR_6L()-g2.getSTR_6L();
       d14 = d14*d14;
       double d15 = g1.getSTR_8L()-g2.getSTR_8L();
       d15 = d15*d15;
       double d16 = g1.getSTR_4R()-g2.getSTR_4R();
       d16 = d16*d16;
       double d17 = g1.getSTR_6R()-g2.getSTR_6R();
       d17 = d17*d17;

       double d = (d1+d2+d3+d4+d5+d6+d7+d8+d9+d10+d11+d12+d13+d14+d15+d16+d17)/17.00;
     
       
       d = Math.sqrt(d);
       return d;

    }

     
      public double calcDAvgObjectClass(List<GeneBean>  l,GeneBean gb)
    {
        double d =0.0;
       
               for(GeneBean gene:l)
               {                   
                      d=d+calc2ObjectDistance(gene, gb);
                  

               }
        
        return (d/(double)l.size());

    }

      public double calcDAvgClassClass(List<GeneBean>  l1,List<GeneBean>  l2)
      {
           double d =0.0;

               for(GeneBean gb1:l1)
               {
                    for(GeneBean gb2:l2)
                   {

                         d=d+calc2ObjectDistance(gb1, gb2);

                   }



               }

        return (d/((double)l1.size()*(double)l2.size()));
      }

       public double UPGMA(ClassBean C1,ClassBean C2,ClassBean C3)
    {
          double avg =0.0;
          double d1 = ((double) C1.getObjectSet().size())* calcDAvgClassClass(C1.getObjectSet(),C3.getObjectSet());
          double d2 = ((double) C2.getObjectSet().size())* calcDAvgClassClass(C2.getObjectSet(),C3.getObjectSet());
          avg = (d1+d2)/(((double) C1.getObjectSet().size())+((double) C2.getObjectSet().size()));
          return avg;

    }



}
