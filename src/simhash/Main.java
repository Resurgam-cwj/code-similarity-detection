package simhash;

import datacleaning.Dataprocessing;
import similarity.Similarity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "/Users/resurgam/IdeaProjects/thesisdesign/src/dataSource/DS01";
        Map<String,String> map = Dataprocessing.getFilesData(path);
        String str;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str = Dataprocessing.convertToWord(entry.getKey(), entry.getValue());
            entry.setValue(str);
        }
        String[] strings1 =map.get("18130500165-1-545.cpp").split("\\s+");
        String[] strings2 =map.get("18130500165-3-862.cpp").split("\\s+");
        Simhash sm = new Simhash(strings1);
        long hash1 = sm.simhash64();
        sm.setStrings(strings2);
        long hash2 = sm.simhash64();
        System.out.println(sm.hammingDistance(hash1,hash2));
//        String[] strings = str.split("\\s+");
    }
}
