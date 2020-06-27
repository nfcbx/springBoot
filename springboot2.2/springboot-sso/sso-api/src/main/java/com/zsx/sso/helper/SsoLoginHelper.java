package com.zsx.sso.helper;

import com.zsx.sso.Config;
import com.zsx.sso.SsoUser;
import com.zsx.sso.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * client logout
     *
     * @param request
     * @param response
     */
    public static void logout(HttpServletRequest request,
                              HttpServletResponse response) {

        String cookieSessionId = CookieUtil.getValue(request, Config.SSO_SESSIONID);
        if (cookieSessionId==null) {
            return;
        }

//        String storeKey = SsoSessionIdHelper.parseStoreKey(cookieSessionId);
//        if (storeKey != null) {
//            SsoLoginStore.remove(storeKey);
//        }

        CookieUtil.remove(request, response, Config.SSO_SESSIONID);
    }



    /**
     * login check
     *
     * @param request
     * @param response
     * @return
     */
    public static SsoUser loginCheck(HttpServletRequest request, HttpServletResponse response){

        String cookieSessionId = CookieUtil.getValue(request, Config.SSO_SESSIONID);

//        // cookie user
//        SsoUser xxlUser = SsoTokenLoginHelper.loginCheck(cookieSessionId);
//        if (xxlUser != null) {
//            return xxlUser;
//        }
//
//        // redirect user
//
//        // remove old cookie
//        SsoLoginHelper.removeSessionIdByCookie(request, response);
//
//        // set new cookie
//        String paramSessionId = request.getParameter(Config.SSO_SESSIONID);
//        xxlUser = SsoTokenLoginHelper.loginCheck(paramSessionId);
//        if (xxlUser != null) {
//            CookieUtil.set(response, Config.SSO_SESSIONID, paramSessionId, false);    // expire when browser close （client cookie）
//            return xxlUser;
//        }

        return null;
    }


    /**
     * client logout, cookie only
     *
     * @param request
     * @param response
     */
    public static void removeSessionIdByCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.remove(request, response, Config.SSO_SESSIONID);
    }

    /**
     * get sessionid by cookie
     *
     * @param request
     * @return
     */
    public static String getSessionIdByCookie(HttpServletRequest request) {
        String cookieSessionId = CookieUtil.getValue(request, Config.SSO_SESSIONID);
        return cookieSessionId;
    }
}



