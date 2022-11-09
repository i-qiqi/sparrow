public class BaseCat {
    static String color = "0";
    static {
        System.out.println("BaseCat, load times : " + color);
        color += "c";
    }

}
