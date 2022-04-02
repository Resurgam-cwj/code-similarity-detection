package similarity;

import datacleaning.Dataprocessing;
import simhash.Simhash;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Similarity {

    private String path;
    private Map<String, Map<String, Double>> winnowindex;
    private Map<String,Map<String,Integer>> simindex;
    private Map<String, int[]> winnowhashvalue;
    private Map<String, Long> simhashvalue;
    public Similarity(Map<String,String> map){
        Simhash simhash = new Simhash(null);
        simhashvalue = new HashMap<>();
        simindex = new HashMap<>();
        String filename;
        String[] strings;
        long hash;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            filename=entry.getKey();
            strings = entry.getValue().split("\\s+");
            simhash.setStrings(strings);
            hash = simhash.simhash64();
            simhashvalue.put(filename,hash);
        }
        simcaculate();
    }

    public Similarity(String path) throws IOException {
        this.path = path;
        Map<String, String> map = Dataprocessing.getFilesData(path);
        winnowhashvalue = new HashMap<>();
        winnowindex = new HashMap<>();
        String filename, content;
        String[] strings;
        int[] arr;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            filename = entry.getKey();
            content = entry.getValue();
            content = content.substring(1, content.length() - 1);
            strings = content.split(", ");
            arr = Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
            winnowhashvalue.put(filename, arr);
        }
        wincaculate();
    }

    public Map<String, Map<String, Double>> getWinnowindex() {
        return winnowindex;
    }

    public Map<String, Map<String, Integer>> getSimindex() {
        return simindex;
    }

    public void simcaculate(){
        int var ;
        for (Map.Entry<String,Long> entry0 : simhashvalue.entrySet()){
            Map<String,Integer> map = new HashMap<>();
            for (Map.Entry<String,Long> entry1 : simhashvalue.entrySet()){
                var = Simhash.hammingDistance(entry0.getValue(),entry1.getValue());
                map.put(entry1.getKey(), var);
            }
            simindex.put(entry0.getKey(),map);
        }

    }

    public void wincaculate() {
        int[] arr0, arr1;
        int count;
        double similarity;
        for (Map.Entry<String, int[]> entry0 : winnowhashvalue.entrySet()) {
            arr0 = entry0.getValue();
            Map<String, Double> map = new HashMap<>();
            for (Map.Entry<String, int[]> entry : winnowhashvalue.entrySet()) {
                arr1 = entry.getValue();
                count = sameCount(arr0, arr1);
                similarity = count / Double.valueOf(arr0.length);
                map.put(entry.getKey(), similarity);
            }
            winnowindex.put(entry0.getKey(), map);
        }
    }

    private int sameCount(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        int count = 0;
        if (arr1.length == 0 || arr2.length == 0) {
            return 0;
        }
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                count++;
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return count;
    }
    public double getResm(String str1,String str2){
        if (winnowindex.containsKey(str1)) {
            Map<String, Double> map = winnowindex.get(str1);
            if (map.containsKey(str2)) {
                return map.get(str2);
            } else {
                throw new IllegalArgumentException(
                        str2 + " NOT EXISTS.\n"
                );
            }
        } else {
            throw new IllegalArgumentException(
                    str1 + " NOT EXISTS.\n"
            );
        }
    }
//    public void resemblance(String str1, String str2) {
//        if (index.containsKey(str1)) {
//            Map<String, Double> map = index.get(str1);
//            if (map.containsKey(str2)) {
//                System.out.println(map.get(str2));
//            } else {
//                System.out.println(str2 + " NOT EXISTS.");
//            }
//        } else {
//            System.out.println(str1 + " NOT EXISTS.");
//        }
//    }

    public void highCorrelationFiles(String str, int k) {
        if (winnowindex.containsKey(str)) {
            Map<String, Double> map = winnowindex.get(str);
            List<Map.Entry<String, Double>> list = sortByValueFloatDesc(map);
            for (int i = 0; i < Math.min(k, list.size()); i++) {
                System.out.println(
                        list.get(i).getKey() + ": " + list.get(i).getValue()
                );
            }
        } else {
            System.out.println(str + " NOT EXISTS.");
        }
    }

    private static List<Map.Entry<String, Double>> sortByValueFloatDesc(
            Map<String, Double> nowPartTwoData
    ) {
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(
                nowPartTwoData.entrySet()
        );
        //然后通过比较器来实现排序
        Collections.sort(
                list,
                new Comparator<Map.Entry<String, Double>>() {
                    //降序排序
                    @Override
                    public int compare(
                            Map.Entry<String, Double> o1,
                            Map.Entry<String, Double> o2
                    ) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                }
        );

        return list;
    }
}

