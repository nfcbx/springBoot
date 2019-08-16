package 图片处理;

/**
 * @author zhaoshuxue3
 * @Date 2019/8/15 11:53
 **/
public class ColorUtil {


    public static String toHex(int r, int g, int b) {
        return "#" + toBrowserHexValue(r)
                + toBrowserHexValue(g)
                + toBrowserHexValue(b);
    }

    private static String toBrowserHexValue(int num) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(num & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }
}
