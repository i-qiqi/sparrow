package leetcode.trie;

public class Trie {

    static final int MAXIMUM_CAPACITY = (1 << 30);

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main1(String[] args) {
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(17));
        System.out.println(tableSizeFor(32));
        System.out.println(tableSizeFor(33));

       
    }

    //求前导0的个数 numberOfLeadingZeros
    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }

    /**
     *  t = tail 和 == 谁先执行？
     * @param args
     */
    public static void main(String[] args) {
        String tail = "b";
        String t = "a";
        boolean check = (t == (t = tail));
        System.out.println("(t != (t = tail)) = " + check);
        System.out.println(t);
    }

}
