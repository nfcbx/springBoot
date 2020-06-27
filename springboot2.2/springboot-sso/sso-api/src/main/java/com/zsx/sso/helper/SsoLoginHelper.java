package com.zsx.sso.helper;

import com.zsx.sso.Config;
import com.zsx.sso.SsoUser;
import com.zsx.sso.util.CookieUtil;

import javax.servlet.http.HttpServletResponse;

public class SsoLoginHelper {

    /**
     * client login
     *
     * @param response
     * @param sessionId
     * @param ssoUser
     * @param ifRemember true: cookie not expire, false: expire when browser close （server cookie）
     */
    public static void login(HttpServletResponse response,
                             String sessionId,
                             SsoUser ssoUser,
                             boolean ifRemember) {
        if (ssoUser == null) {
            throw new RuntimeException("ssoUser is null, sessionId:" + sessionId);
        }

//        ssoUser

        CookieUtil.set(response, Config.SSO_SESSIONID, sessionId, ifRemember);
    }

}



