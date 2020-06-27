package com.zsx.sso.util;

import com.zsx.sso.SsoUser;

import java.util.UUID;

public class SessionIdUtil {

    /**
     * make client sessionId
     *
     * @param ssoUser
     * @return
     */
    public static String makeSessionId(SsoUser ssoUser) {

        String uuid = ssoUser.getUuid();
        Integer userId = ssoUser.getUserId();
        String binaryStr = Integer.toBinaryString(userId);
        if (binaryStr.length() < 31) {
            int num = 31 - binaryStr.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < num; i++) {
                sb.append("0");
            }
            sb.append(binaryStr);
            binaryStr = sb.toString();
        }
        binaryStr = binaryStr.replace("", "_");
        String sessionId = binaryStr.concat(uuid);
        return sessionId;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            SsoUser ssoUser = new SsoUser();
            ssoUser.setUserId(1234);
            ssoUser.setUuid(UUID.randomUUID().toString());
            long start = System.nanoTime();
            String s = makeSessionId(ssoUser);
            long end = System.nanoTime();
            System.out.println((end - start) + "ns");
            System.out.println(s);
        }

    }
}
