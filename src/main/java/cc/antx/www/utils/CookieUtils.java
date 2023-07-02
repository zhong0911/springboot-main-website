package cc.antx.www.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
    public static String getCookieValue(Cookie[] cookies, String name) {
        String result = null;
        {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    result = cookie.getValue();
                    break;
                }
            }
        }
        return (result == null) ? "" : result;
    }
}
