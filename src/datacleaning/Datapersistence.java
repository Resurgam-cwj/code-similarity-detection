package datacleaning;

import datacleaning.Dataprocessing;
import datacleaning.Fingerprint;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Datapersistence {
    private String path;
    private Map<String,String> map;
    private int k;
    private int t;
    private int base;
    public Datapersistence(int k, int t, int base, String path) {
        this.path = path;
        this.k=k;
        this.t=t;
        this.base=base;
    }
    public void dataPer(){
        try {
            this.map = Dataprocessing.getFilesData(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str;
        List<Integer> hashes = new ArrayList<>();
        Set<Integer> set;
        File file;
        FileWriter fw = null;
        BufferedWriter bw;
        Fingerprint fp = new Fingerprint(k,t,base);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str= Dataprocessing.replaceAllBlank(entry.getValue());
            entry.setValue(str);
//           if (entry.getKey().equals("test.cpp"))System.out.println(str);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(str);
            hashes=fp.getHashesWithChar(map.get(entry.getKey()));
//            if (entry.getKey().equals("test.cpp"))System.out.println(hashes);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(hashes);
            set=fp.winnowing(hashes);
//            if (entry.getKey().equals("test.cpp"))System.out.println(set);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(set);
            file=new File("/Users/resurgam/IdeaProjects/thesisdesign/src/metadata"+"/"+entry.getKey());
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fw = new FileWriter(file.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            bw = new BufferedWriter(fw);
            try {
                bw.write(set.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void dataPerWithChar(){
        try {
            this.map = Dataprocessing.getFilesData(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str;
        List<Integer> hashes = new ArrayList<>();
        Set<Integer> set;
        File file;
        FileWriter fw = null;
        BufferedWriter bw;
        Fingerprint fp = new Fingerprint(k,t,base);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str= Dataprocessing.convertToChar(entry.getKey(),entry.getValue());
            entry.setValue(str);
//            if (entry.getKey().equals("test.cpp"))System.out.println(str);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(str);
            hashes=fp.getHashesWithChar(map.get(entry.getKey()));
//            if (entry.getKey().equals("test.cpp"))System.out.println(hashes);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(hashes);
            set=fp.winnowing(hashes);
//            if (entry.getKey().equals("test.cpp"))System.out.println(set);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(set);
            file=new File("/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithchar"+"/"+entry.getKey());
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fw = new FileWriter(file.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            bw = new BufferedWriter(fw);
            try {
                bw.write(set.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void dataPerWithWord(){
        try {
            this.map = Dataprocessing.getFilesData(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str;
        List<Integer> hashes = new ArrayList<>();
        Set<Integer> set;
        File file;
        FileWriter fw = null;
        BufferedWriter bw;
        Fingerprint fp = new Fingerprint(k,t,base);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str= Dataprocessing.convertToWord(entry.getKey(),entry.getValue());
            entry.setValue(str);
//            if (entry.getKey().equals("test.cpp"))System.out.println(str);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(str);
            hashes=fp.getHashesWithWords(map.get(entry.getKey()));
//            if (entry.getKey().equals("test.cpp"))System.out.println(hashes);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(hashes);
            set=fp.winnowing(hashes);
//            if (entry.getKey().equals("test.cpp"))System.out.println(set);
//            if (entry.getKey().equals("18130500165-1-173.cpp"))System.out.println(set);
            file=new File("/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithword"+"/"+entry.getKey());
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fw = new FileWriter(file.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            bw = new BufferedWriter(fw);
            try {
                bw.write(set.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
