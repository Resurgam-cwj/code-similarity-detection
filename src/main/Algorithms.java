package main;

import datacleaning.Datapersistence;
import datacleaning.Dataprocessing;
import similarity.Similarity;
import test.Verify;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class Algorithms {

    public static void main(String[] args) throws IOException {
        String path = "/Users/resurgam/IdeaProjects/thesisdesign/src/dataSource/DS01";
        Map<String,String> map = Dataprocessing.getFilesData(path);
        String str;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str = Dataprocessing.convertToWord(entry.getKey(), entry.getValue());
            entry.setValue(str);
        }
//        Datapersistence dp = new Datapersistence(3,7,2,path);
//        dp.dataPerWithWord();
        path="/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithword";
        Similarity sm = new Similarity(map,path);
        Verify ver;
        double w1,w2;
        for (int i = 1; i < 10; i++) {
            w1=(double)i/10.0;
            w2=(double) (10-i)/10.0;
            ver = new Verify(sm,w1,w2,8);
            System.out.print("w1="+w1+","+"w2="+w2+":  ");
            System.out.println("精确度： "+ver.getPrecision()+"   "+"误报率："+ver.getFAR()+"   "+"漏报率： "+ver.getFMR()
                    +"  "+"加权："+(ver.getPrecision()-ver.getFAR()-ver.getFMR()) );
        }





//        String path = "/Users/resurgam/IdeaProjects/thesisdesign/src/dataSource/DS01";
//        Datapersistence dp;
//        Similarity sm;
//        Verify ver;
//        for (int i = 1; i < 4; i++) {
//            for (int j = i+1; j < i+7; j++) {
//                dp = new Datapersistence(i,j,2,path);
//                dp.dataPerWithWord();
//                sm = new Similarity(
//                "/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithword"
//                );
//                ver = new Verify(sm,0);
//                System.out.print("k="+i+","+"t="+j+":  ");
//                System.out.println("x = "+i+": "+"精确度： "+ver.getPrecision()+"   "+"误报率："+ver.getFAR()+"   "+"漏报率： "+ver.getFMR()
//                        +"  "+"加权："+(ver.getPrecision()-ver.getFAR()-ver.getFMR()) );
//            }
//        }



//        Datapersistence dp = new Datapersistence(2,6,2,path);
//        dp.dataPerWithWord();
//        Similarity sm = new Similarity(
//                "/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithword"
//                );
//        Verify ver = new Verify(sm);
//        System.out.println("精确度： "+ver.getPrecision()+"   "+"误报率："+ver.getFAR()+"   "+"漏报率： "+ver.getFMR());





//        dp = new Datapersistence(5,7,2,path);
//        dp.dataPerWithChar();
//        sm = new Similarity(
//                "/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithchar"
//                );
//        ver = new Verify(sm,0);
//        System.out.print("k=5,t=7:  ");
//        System.out.println("精确度： "+ver.getPrecision()+"   "+"误报率："+ver.getFAR()+"   "+"漏报率： "+ver.getFMR());



//        for (int i = 3; i < 7; i++) {
//            for (int j = i; j < i+4&&j<8; j++) {
//                dp = new Datapersistence(i,j,2,path);
//                dp.dataPerWithChar();
//                sm = new Similarity(
//                "/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithchar"
//                );
//                ver = new Verify(sm,0);
//                System.out.print("k="+i+","+"t="+j+":  ");
//                System.out.println("x = "+i+": "+"精确度： "+ver.getPrecision()+"   "+"误报率："+ver.getFAR()+"   "+"漏报率： "+ver.getFMR()
//                        +"  "+"加权："+(ver.getPrecision()-ver.getFAR()-ver.getFMR()) );
//            }
//        }




//        Datapersistence dp = new Datapersistence(5,8,3,path);
//        dp.dataPerWithChar();
//        Similarity sm = new Similarity(
//                "/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithword"
//        );
//        Similarity sm = new Similarity(
//                "/Users/resurgam/IdeaProjects/thesisdesign/src/metadata"
//        );
//        Verify ver = new Verify(sm);
//        System.out.println("精确度： "+ver.getPrecision()+"   "+"误报率："+ver.getFAR()+"   "+"漏报率： "+ver.getFMR());
//        sm = new Similarity(
//                "/Users/resurgam/IdeaProjects/thesisdesign/src/metadatawithword"
//        );
//        ver = new Verify(sm);
//        System.out.println("精确度： "+ver.getPrecision()+"   "+"误报率："+ver.getFAR()+"   "+"漏报率： "+ver.getFMR());
//       sm.highCorrelationFiles("18130500165-1-173.cpp",10);



    }

}