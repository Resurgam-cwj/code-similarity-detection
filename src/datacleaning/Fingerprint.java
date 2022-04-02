package datacleaning;

import java.util.*;

public class Fingerprint {
    public static int seed = 131;
    private int windowSize;
    private int k;
    private int noiseThreshold;
    private int base;
    private int temp;

    public Fingerprint(int k, int noiseThreshold, int base) {
        this.k = k;
        if (noiseThreshold < k) {
            throw new IllegalArgumentException(
                    "Noise threshold " + "should not be smaller than minimum match.\n "
            );
        }
        this.windowSize = noiseThreshold - k + 1;
        this.base = base;
        this.temp = (int) Math.pow(base, k - 1);
    }

    public Fingerprint() {
        this(6, 9, 7);
    }

    public static int getBkdrHash(String str){
        int hash = 0;
        for(int i = 0;i < str.length();i++)
        {
            hash =  seed * hash + str.charAt(i);
        }
        return (hash & 0x7FFFFFFF);
    }

    public int getHashWithChar(String str, int pre, int start) {
        int hash = 0;
        int pow = 1;
        if (pre == 0) {
            for (int i = Math.min(k, str.length())-1; i >=0;  i--) {
                hash += (str.charAt(i)-32 ) * pow;
                pow = pow * base;
            }

        } else {
            hash =
                    (pre - (str.charAt(start - 1)-32) * temp) *
                            base +
                            str.charAt(start + k - 1)-32;
        }
        return hash;
    }


    public List<Integer> getHashesWithChar(String str) {
        List<Integer> hashlist = new ArrayList<>();
        int pre = 0;
        if (str.length() < this.k) {
            hashlist.add(getHashWithChar(str, 0, 0));
        } else {
            for (int i = 0; i < str.length() - this.k + 1; i++) {
                pre = getHashWithChar(str, pre, i);
                hashlist.add(pre);
            }
        }
        return hashlist;
    }

    public int getHashWithWords(int[] data, int pre, int start) {
        int hash = 0;
        int pow = 1;
        if (pre == 0) {
            for (int i = Math.min(k, data.length)-1; i >=0;  i--) {
                hash += data[i] * pow;
                pow = pow * base;
                hash = (hash) & 0x7FFFFFFF;
            }

        } else {
            hash =
                    ((pre - data[start - 1] * temp) *
                            base +
                            data[start + k - 1])& 0x7FFFFFFF;
        }


        return hash;
    }


    public List<Integer> getHashesWithWords(String str) {
        List<Integer> hashlist = new ArrayList<>();
        String[] strings = str.split("\\s+");
        int[] data = new int[strings.length];
        int pre = 0;
        // BKDR Hash Function
        for (int i = 0; i < data.length; i++) {
            data[i] = getBkdrHash(strings[i]);
            hashlist.add(data[i]);
        }
//        if (data.length<=k){
//            hashlist.add(getHashWithWords(data,0,0));
//        }
//        else {
//            for (int i = 0; i < data.length - this.k + 1; i++) {
//                pre = getHashWithWords(data, pre, i);
//                hashlist.add(pre);
//            }
//        }
        return hashlist;
    }

    public Set<Integer> winnowing(List<Integer> list) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < list.size() - this.windowSize + 1; i++) {
            List<Integer> s = new ArrayList<>(list.subList(i, i + this.windowSize));
            set.add(Collections.min(s));
        }
        return set;
    }
}

