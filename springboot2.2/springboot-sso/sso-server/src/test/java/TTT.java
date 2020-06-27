import jdk.nashorn.api.scripting.ScriptUtils;

import java.math.BigInteger;

public class TTT {

    public static void main(String[] args) {
        String s = Integer.toBinaryString(Integer.MAX_VALUE);
        System.out.println(s);
        System.out.println(s.length());

//        1111111111111111111111111111111

        BigInteger bigInteger = new BigInteger("0000000000000000000000011111111", 2);
        System.out.println(bigInteger);


        Integer integer = Integer.valueOf(bigInteger.toString());
        System.out.println(integer);


        String s1 = Integer.toBinaryString(Integer.parseInt("255"));

        System.out.println(s1);


        if (s1.length() < 31) {
            int i = 31 - s1.length();
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < i; j++) {
                stringBuilder.append("0");
            }

            stringBuilder.append(s1);

            System.out.println(stringBuilder.toString());


        }

    }
}
