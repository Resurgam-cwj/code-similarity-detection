package test;

import similarity.Similarity;

import java.util.HashMap;
import java.util.Map;

public class Verify {
    private  Map<String, Map<String,Double>> winnowmap;
    private  Map<String, Map<String,Integer>> simmap;
    private  static Map<String,Map<String,Integer>> testMap = new HashMap<>();
    private Similarity sm;
    public double weight_sm;
    public double weight_win;
    private double TP;//真正例
    private double TN;//真负例
    private double FP;//假正例
    private double FN;//假负例
    private int simdistance;
    private double p;//参数
    static  {
        //test.cpp
        Map<String, Integer> map = new HashMap<>();
        map.put("18130500010-2-670.cpp",1);
        //18130500010-2-670.cpp
        map = new HashMap<>();
        map.put("test.cpp",1);
        map.put("18130500010-3-463.cpp",0);
        map.put("18130500165-1-173.cpp",0);
        map.put("18130500165-1-545.cpp",0);
        map.put("18130500165-1-1181.cpp",0);
        map.put("18130500165-1-1183.cpp",0);
        map.put("18130500165-1-1214.cpp",0);
        map.put("18130500165-1-1219.cpp",0);
        map.put("18130500165-1-1226.cpp",0);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500010-2-670.cpp",map);
        //18130500010-3-463.cpp
        map=new HashMap<>();
        map.put("18130500165-1-173.cpp",0);
        map.put("18130500165-1-545.cpp",0);
        map.put("18130500165-1-1181.cpp",0);
        map.put("18130500165-1-1183.cpp",0);
        map.put("18130500165-1-1214.cpp",0);
        map.put("18130500165-1-1219.cpp",0);
        map.put("18130500165-1-1226.cpp",0);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500010-3-463.cpp",map);
        //18130500165-1-173.cpp
        map=new HashMap<>();
        map.put("18130500165-1-545.cpp",0);
        map.put("18130500165-1-1181.cpp",1);
        map.put("18130500165-1-1183.cpp",1);
        map.put("18130500165-1-1214.cpp",1);
        map.put("18130500165-1-1219.cpp",1);
        map.put("18130500165-1-1226.cpp",1);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-1-173.cpp",map);
        //18130500165-1-545.cpp
        map=new HashMap<>();
        map.put("18130500165-1-1181.cpp",0);
        map.put("18130500165-1-1183.cpp",0);
        map.put("18130500165-1-1214.cpp",0);
        map.put("18130500165-1-1219.cpp",0);
        map.put("18130500165-1-1226.cpp",0);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-1-545.cpp",map);
        //18130500165-1-1181.cpp
        map=new HashMap<>();
        map.put("18130500165-1-1183.cpp",1);
        map.put("18130500165-1-1214.cpp",1);
        map.put("18130500165-1-1219.cpp",1);
        map.put("18130500165-1-1226.cpp",1);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-1-1181.cpp",map);
        //18130500165-1-1183.cpp
        map=new HashMap<>();
        map.put("18130500165-1-1214.cpp",1);
        map.put("18130500165-1-1219.cpp",1);
        map.put("18130500165-1-1226.cpp",1);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-1-1183.cpp",map);
        //18130500165-1-1214.cpp
        map=new HashMap<>();
        map.put("18130500165-1-1219.cpp",1);
        map.put("18130500165-1-1226.cpp",1);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-1-1214.cpp",map);
        //18130500165-1-1219.cpp
        map=new HashMap<>();
        map.put("18130500165-1-1226.cpp",1);
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-1-1219.cpp",map);
        //18130500165-1-1226.cpp
        map=new HashMap<>();
        map.put("18130500165-2-493.cpp",0);
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-1-1226.cpp",map);
        //18130500165-2-493.cpp
        map=new HashMap<>();
        map.put("18130500165-3-801.cpp",0);
        map.put("18130500165-3-862.cpp",0);
        map.put("18130500165-3-969.cpp",0);
        map.put("18130500165-3-998.cpp",0);
        map.put("18130500165-3-1034.cpp",0);
        map.put("18130500165-3-1076.cpp",0);
        map.put("18130500165-3-1087.cpp",0);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-2-493.cpp",map);
        //18130500165-3-801.cpp
        map=new HashMap<>();
        map.put("18130500165-3-862.cpp",1);
        map.put("18130500165-3-969.cpp",1);
        map.put("18130500165-3-998.cpp",1);
        map.put("18130500165-3-1034.cpp",1);
        map.put("18130500165-3-1076.cpp",1);
        map.put("18130500165-3-1087.cpp",1);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-3-801.cpp",map);
        //18130500165-3-862.cpp
        map=new HashMap<>();
        map.put("18130500165-3-969.cpp",1);
        map.put("18130500165-3-998.cpp",1);
        map.put("18130500165-3-1034.cpp",1);
        map.put("18130500165-3-1076.cpp",1);
        map.put("18130500165-3-1087.cpp",1);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-3-862.cpp",map);
        //18130500165-3-969.cpp
        map=new HashMap<>();
        map.put("18130500165-3-998.cpp",1);
        map.put("18130500165-3-1034.cpp",1);
        map.put("18130500165-3-1076.cpp",1);
        map.put("18130500165-3-1087.cpp",1);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-3-969.cpp",map);
        //18130500165-3-998.cpp
        map=new HashMap<>();
        map.put("18130500165-3-1034.cpp",1);
        map.put("18130500165-3-1076.cpp",1);
        map.put("18130500165-3-1087.cpp",1);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-3-1034.cpp",map);
        //18130500165-3-1034.cpp
        map=new HashMap<>();
        map.put("18130500165-3-1076.cpp",1);
        map.put("18130500165-3-1087.cpp",1);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-3-1034.cpp",map);
        //18130500165-3-1076.cpp
        map=new HashMap<>();
        map.put("18130500165-3-1087.cpp",1);
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-3-1076.cpp",map);
        //18130500165-3-1087.cpp
        map=new HashMap<>();
        map.put("18150200015-1-1068.cpp",0);
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18130500165-3-1087.cpp",map);
        //18150200015-1-1068.cpp
        map=new HashMap<>();
        map.put("18150200015-3-500.cpp",0);
        map.put("18150200015-3-586.cpp",0);
        map.put("18150200015-3-635.cpp",0);
        map.put("18150200015-4-625.cpp",0);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18150200015-1-1068.cpp",map);
        //18150200015-3-500.cpp
        map=new HashMap<>();
        map.put("18150200015-3-586.cpp",1);
        map.put("18150200015-3-635.cpp",1);
        map.put("18150200015-4-625.cpp",1);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18150200015-3-500.cpp",map);
        //18150200015-3-586.cpp
        map=new HashMap<>();
        map.put("18150200015-3-635.cpp",1);
        map.put("18150200015-4-625.cpp",1);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18150200015-3-586.cpp",map);
        //18150200015-3-635.cpp
        map=new HashMap<>();
        map.put("18150200015-4-625.cpp",1);
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18150200015-3-635.cpp",map);
        //18150200015-4-625.cpp
        map=new HashMap<>();
        map.put("19020700012-1-6.cpp",0);
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("18150200015-4-625.cpp",map);
        //19020700012-1-6.cpp
        map=new HashMap<>();
        map.put("19020700012-2-105.cpp",0);
        map.put("19020700012-3-695.cpp",0);
        testMap.put("19020700012-1-6.cpp",map);
        //19020700012-2-105.cpp
        map=new HashMap<>();
        map.put("19020700012-3-695.cpp",0);
        testMap.put("19020700012-2-105.cpp",map);
    }

    public Verify(Similarity sm,int x) {
        if (x==0){
            this.winnowmap=sm.getWinnowindex();
            this.sm=sm;
            this.TP=0;
            this.TN=0;
            this.FP=0;
            this.FN=0;
            winnowexe();
        }
        else {
            this.simmap=sm.getSimindex();
            this.sm=sm;
            this.TP=0;
            this.TN=0;
            this.FP=0;
            this.FN=0;
            this.simdistance=x;
            simexe();
        }

    }

    public Verify(Similarity sm , double w1, double w2,int simdistance){
        this.weight_sm=w1;
        this.weight_win=w2;
        this.simmap=sm.getSimindex();
        this.winnowmap=sm.getWinnowindex();
        this.sm=sm;
        this.TP=0;
        this.TN=0;
        this.FP=0;
        this.FN=0;
        this.simdistance=simdistance;
        this.p=1/(4*simdistance);
        sim_winnowexe();
    }

    private void simexe() {
        String str1 , str2;
        Map<String,Integer> map;
        for (Map.Entry<String, Map<String, Integer>> entry0 : testMap.entrySet()){
            str1 = entry0.getKey();
            map = simmap.get(str1);
            for (Map.Entry<String , Integer> entry1 : entry0.getValue().entrySet()){
                str2 = entry1.getKey();
                if (entry1.getValue()==1){
                    if (map.get(str2)<simdistance){
                        TP++;
                    }
                    else {
                        FN++;
                    }
                }
                else {
                    if (map.get(str2)<simdistance){
                        FP++;
                    }
                    else {
                        TN++;
                    }
                }
            }
        }
    }

    private void winnowexe(){
        double x,y;
        String str1 , str2;
        for (Map.Entry<String, Map<String, Integer>> entry0 : testMap.entrySet()){
            str1=entry0.getKey();
            for (Map.Entry<String , Integer> entry1 : entry0.getValue().entrySet()) {
                str2=entry1.getKey();
                x=sm.getResm(str1,str2);
                y=sm.getResm(str2,str1);
                if (entry1.getValue()==1){
                    if ((x>0.5&&y>0.5)||x>0.8||y>0.8){
                        TP++;
                    }
                    else {
                        FN++;
                    }
                }
                else {
                    if ((x>0.5&&y>0.5)||x>0.8||y>0.8){
                        FP++;
//                        System.out.println(str1+"与"+str2+"检测出相似但实际不相似");
//                        System.out.println(str1+"与"+str2+"的相似度为"+x);
//                        System.out.println(str2+"与"+str1+"的相似度为"+y);
                    }
                    else {
                        TN++;
                    }
                }

            }
        }
    }

    public void sim_winnowexe(){
        double x,y;
        Map<String,Integer> map;
        String str1 , str2;
        for (Map.Entry<String, Map<String, Integer>> entry0 : testMap.entrySet()){
            str1=entry0.getKey();
            map = simmap.get(str1);
            for (Map.Entry<String , Integer> entry1 : entry0.getValue().entrySet()) {
                str2=entry1.getKey();
                x=sm.getResm(str1,str2)*weight_win+weight_sm/(double)(1+p* map.get(str2));
                if (entry1.getValue()==1){
                    if (x>0.8){
                        TP++;
                    }
                    else {
                        FN++;
                    }
                }
                else {
                    if (x>0.8){
                        FP++;
//                        System.out.println(str1+"与"+str2+"检测出相似但实际不相似");
//                        System.out.println(str1+"与"+str2+"的相似度为"+x);
//                        System.out.println(str2+"与"+str1+"的相似度为"+y);
                    }
                    else {
                        TN++;
                    }
                }

            }
        }
    }

    public double getPrecision(){
        return TP/(TP+FP);
    }
    public double getFAR(){
        return FP/(FP+TN);
    }
    public double getFMR(){
        return FN/(FN+TP);
    }
}
