import org.apache.commons.lang3.StringUtils;

/**
 * @author zhaoshuxue3
 * @Date 2019/4/15 10:56
 **/
public class Test001 {


    public static void main(String[] args) {

        String str = "你好世界";
        str = "asdf_qwer123";
        System.out.println(getPinYinHeadChar(str));

    }

    public static String getPinYinHeadChar(String str) {
        String convert = "";
        if (StringUtils.isBlank(str)) {
            return convert;
        }
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
//            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
//            if (pinyinArray != null) {
//                convert += pinyinArray[0].charAt(0);
//                convert += pinyinArray[0].charAt(1);
//            } else {
//                convert += word;
//            }
        }
        return convert.toUpperCase();
    }

}
