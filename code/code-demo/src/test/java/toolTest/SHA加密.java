package toolTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA加密 {

    public static void main(String[] args) {

        String token = "token";
        String timestamp = "1234567890";
//        timestamp = String.valueOf(System.currentTimeMillis());
        String nonce = "nonce";
        String encrypt = "encrypt";

        String[] array = {token, timestamp, nonce, encrypt};
        StringBuffer sb = new StringBuffer();
        Arrays.sort(array);
        for (String str : array) {
            sb.append(str);
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

            messageDigest.update(sb.toString().getBytes());

            byte[] bytes = messageDigest.digest();

            StringBuffer stringBuffer = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < bytes.length; i++) {
                shaHex = Integer.toHexString(bytes[i] & 0xFF);

                if (shaHex.length() < 2) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(shaHex);
            }

            System.out.println(stringBuffer.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
