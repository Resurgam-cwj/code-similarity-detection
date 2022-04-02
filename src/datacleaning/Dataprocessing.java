package datacleaning;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dataprocessing {

    public static Map<String, String> getFilesData(String filePath)
            throws IOException {
        Map<String, String> filemaps = new HashMap<>();
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                filemaps.put(files[i].getName(), readFile(files[i]));
            }
        }
        return filemaps;
    }

    private static String readFile(File path) throws IOException {
        InputStream is = new FileInputStream(path);
        byte[] bytes = new byte[8192];
        int len = is.read(bytes);
        String str = null;
        while (len != -1) {
            str = new String(bytes, 0, len);
            len = is.read(bytes);
        }
        is.close();
        return str;
    }

    public static String preProcessing(String name,String str) {
        if (name.endsWith(".cpp")||name.endsWith(".c")) {
            str = headerfileProC(str); //处理头文件
        }
        else if (name.endsWith(".py")){
            str = headerfilePython(str);
        }
        else if (name.endsWith(".java")){
            str = headerfileJava(str);
        }
        str = str.trim().replaceAll("(\\/\\/.*)|(/\\*{1,2}[\\s\\S]*?\\*/)", ""); //删除头尾空格,去注释
        str = str.replaceAll("\\n[\\s| ]*\\r", "").toLowerCase(); //删除空行，大写转小写
        str = relapceNums(str); //数字常量替换为NUMS
        str = replaceFunc(str); //函数方法名替换为FUNC
        str = replaceVaries(str); //变量名替换为VARY
        str = replaceClass(str); //类名替换为CLS
        return str;
    }

    public static String convertToChar(String name, String str) {
        str = preProcessing(name,str);
        str = replaceAllBlank(str);
        return str;
    }
    public static String convertToWord(String name,String str) {
        str = preProcessing(name,str);
        str= replaceBlankLeaveOne(str);
        return str;
    }

    public static String replaceAllBlank(String str) {
        String s = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            s = m.replaceAll("");
        }
        return s;
    }

    public static String replaceBlankLeaveOne(String str) {
        String s = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");
            Matcher m = p.matcher(str);
            s = m.replaceAll(" ");
        }
        return s;
    }

    //去c\c++头文件相关字符
    public static String headerfileProC(String str) {
        String regx =
                "(#include<stdio.h>)|(#include<stdlib.h>)|(#include<iostream>)|(using namespace std;)|(#include<cstdio>)";
        str = str.replaceAll(regx, "");
        return str.replaceAll("(#include<)([A-Za-z]+)(.h)?>", "$2");
    }

    //去java相关
    public static String headerfileJava(String str){
        return str.replaceAll("import(\\/s)+([A-Za-z.*]+)","");
    }

    //去python相关
    public static String headerfilePython(String str){
        str = str.replaceAll("from(\\/s)+[A-Za-z\\/s]+import(\\/s)+([A-Za-z.*]+)","");
        return str.replaceAll("import(\\/s)+([A-Za-z.*]+)","");
    }

    //数字常量替换为NUMS
    public static String relapceNums(String str) {
        str = str.replaceAll("=(\\s)*\\d+(\\.\\d+)?", "= NUMS");
        str = str.replaceAll(">(\\s)*\\d+(\\.\\d+)?", "> NUMS");
        str = str.replaceAll("<(\\s)*\\d+(\\.\\d+)?", "< NUMS");
        return str;
    }

    //函数方法名替换为FUNC
    public static String replaceFunc(String str) {
        String[] regx = {
                "int",
                "unsigned int",
                "void",
                "short",
                "unsigned short",
                "char",
                "unsigned char",
                "float",
                "double",
                "bool",
                "boolean",
                "void *",
                "int *",
                "unsigned int *",
                "short *",
                "unsigned short *",
                "char *",
                "unsigned char *",
        };
        for (int i = 0; i < regx.length; i++) {
            str =
                    str.replaceAll(
                            "(" + regx[i] + "(\\s))+([A-Za-z0-9]+)(\\((.)*\\))",
                            "$1" + "FUNC" + "$4"
                    );
        }
        str = str.replaceAll("([A-Za-z0-9]+)(\\((.)*\\));", "FUNC" + "$2" + ";");
        return str;
    }

    //变量名替换为VAR
    public static String replaceVaries(String str) {
        String[] regx = {
                "int",
                "unsigned int",
                "void",
                "short",
                "unsigned short",
                "char",
                "unsigned char",
                "float",
                "double",
                "bool",
                "boolean",
                "void *",
                "int *",
                "unsigned int *",
                "short *",
                "unsigned short *",
                "char *",
                "unsigned char *",
        };
        for (int i = 0; i < regx.length; i++) {
            str = str.replaceAll("[A-Za-z0-9]+\\[[A-Za-z0-9+\\-*/]+\\]", "VAR[]");
            str =
                    str.replaceAll(
                            "(" + regx[i] + "(\\s))+([A-Za-z0-9]+)(\\s)*=",
                            "$1" + "VAR" + "="
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "(\\s))+([A-Za-z0-9]+)(\\s)*,",
                            "$1" + "VAR" + ","
                    );
            str =
                    str.replaceAll(
                            "(" + "VAR," + "(\\s)*)([A-Za-z0-9]+)(\\s)*",
                            "$1" + "VAR"
                    );
            str =
                    str.replaceAll(
                            "(" + "(VAR,)*" + "(\\s)*)([A-Za-z0-9]+)(\\s)*,",
                            "$1" + "VAR,"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "(\\s))+([A-Za-z0-9]+)(\\s)*\\)",
                            "$1" + "VAR" + ")"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "(\\s))+([A-Za-z0-9]+)(\\s)*;",
                            "$1" + "VAR" + ";"
                    );
        }
        //#define varies nums
        str = str.replaceAll("#define(\\s)+[A-Za-z_]+", "#define VAR");
        regx = new String[] { "while", "if" };
        for (int i = 0; i < regx.length; i++) {
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*=",
                            "$1" + "VAR" + "="
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*>",
                            "$1" + "VAR" + ">"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*<",
                            "$1" + "VAR" + "<"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*-",
                            "$1" + "VAR" + "-"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*\\+",
                            "$1" + "VAR" + "+"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*\\*",
                            "$1" + "VAR" + "*"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*\\/",
                            "$1" + "VAR" + "/"
                    );
            str =
                    str.replaceAll(
                            "(" + regx[i] + "\\(" + "(\\s)*)([A-Za-z0-9]+)(\\s)*%",
                            "$1" + "VAR" + "%"
                    );
            str = str.replaceAll("VAR<" + "[A-Za-z]+", "VAR<VAR");
            str = str.replaceAll("VAR<=" + "[A-Za-z]+", "VAR<=VAR");
            str = str.replaceAll("VAR==" + "[A-Za-z]+", "VAR==VAR");
            str = str.replaceAll("VAR>" + "[A-Za-z]+", "VAR>VAR");
            str = str.replaceAll("VAR>=" + "[A-Za-z]+", "VAR>=VAR");
            str = str.replaceAll("VAR\\[]<" + "[A-Za-z]+", "VAR[]<VAR");
            str = str.replaceAll("VAR\\[]<=" + "[A-Za-z]+", "VAR[]<=VAR");
            str = str.replaceAll("VAR\\[]==" + "[A-Za-z]+", "VAR[]==VAR");
            str = str.replaceAll("VAR>\\[]" + "[A-Za-z]+", "VAR[]>VAR");
            str = str.replaceAll("VAR>=\\[]" + "[A-Za-z]+", "VAR[]>=VAR");
        }

        //=+-*/%<>
        str = str.replaceAll("[A-Za-z]+(\\s)*=", "VAR=");
        str = str.replaceAll("=(\\s)*[A-Za-z]+", "=VAR");
        str = str.replaceAll("\\-[A-Za-z]+", "-VAR");
        str = str.replaceAll("[A-Za-z]+-", "VAR-");
        str = str.replaceAll("[A-Za-z]+\\+", "VAR+");
        str = str.replaceAll("\\+[A-Za-z]+", "+VAR");
        str = str.replaceAll("[A-Za-z]+\\*", "VAR*");
        str = str.replaceAll("\\*[A-Za-z]+", "*VAR");
        str = str.replaceAll("[A-Za-z]+\\/", "VAR/");
        str = str.replaceAll("\\/[A-Za-z]+", "/VAR");
        str = str.replaceAll("[A-Za-z]+\\%", "VAR%");
        str = str.replaceAll("\\%[A-Za-z]+", "%VAR");
        str = str.replaceAll("[A-Za-z]+(\\s)*<", "VAR<");
        str = str.replaceAll("<(\\s)*[A-Za-z]+", "<VAR");
        str = str.replaceAll("[A-Za-z]+(\\s)*>", "VAR>");
        str = str.replaceAll(">(\\s)*[A-Za-z]+", ">VAR");

        //return
        str = str.replaceAll("return(\\s)+[A-Za-z]+", "return VAR");

        return str;
    }

    public static String replaceClass(String str) {
        str = str.replaceAll("([A-Za-z]+)\\.([A-Za-z]+)", "CLS." + "$2");
        return str.replaceAll("[A-Za-z]+(\\s)+&[A-Za-z0-9_]+", "CLS &cls");
    }
}

