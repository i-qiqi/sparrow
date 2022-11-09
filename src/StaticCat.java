public class StaticCat extends BaseCat {
    static int x = 1;
    static {
        System.out.println("StaticCat, load times : " + x);
        x++;
    }
    public static void testStaticCodeBlock(){
        System.out.println("---- StaticCat do nothing ---, x = " + x);
    }
}
