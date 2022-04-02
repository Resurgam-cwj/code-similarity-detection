package simhash;

import java.util.List;

public class Simhash {
    private String[] strings;

    public Simhash(String[] strings) {
        this.strings = strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public static int hammingDistance(int hash1, int hash2) {
        int i = hash1 ^ hash2;
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }

    public static int hammingDistance(long hash1, long hash2) {
        long i = hash1 ^ hash2;
        i = i - ((i >>> 1) & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        i = i + (i >>> 32);
        return (int) i & 0x7f;
    }


    public long simhash64() {
        int bitLen = 64;
        int[] bits = new int[bitLen];
        String[] tokens = strings;
        //若第i位为1，则记为1，否则记为-1
        for (String t : tokens) {
            long v = MurmurHash.hash64(t);
            for (int i = bitLen; i >= 1; --i) {
                if (((v >> (bitLen - i)) & 1) == 1)
                    ++bits[i - 1];
                else
                    --bits[i - 1];
            }
        }
        //将bits[]转化为long值存储
        long hash = 0x0000000000000000;
        long one = 0x0000000000000001;
        for (int i = bitLen; i >= 1; --i) {
            if (bits[i - 1] > 0) {
                hash |= one;
            }
            one = one << 1;
        }
        return hash;
    }

    public long simhash32() {
        int bitLen = 32;
        int[] bits = new int[bitLen];
        String[] tokens = strings;
        for (String t : tokens) {
            int v = MurmurHash.hash32(t);
            for (int i = bitLen; i >= 1; --i) {
                if (((v >> (bitLen - i)) & 1) == 1)
                    ++bits[i - 1];
                else
                    --bits[i - 1];
            }
        }
        int hash = 0x00000000;
        int one = 0x00000001;
        for (int i = bitLen; i >= 1; --i) {
            if (bits[i - 1] > 1) {
                hash |= one;
            }
            one = one << 1;
        }
        return hash;
    }
}
