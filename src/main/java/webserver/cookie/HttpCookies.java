package webserver.cookie;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpCookies {

    private final Map<String, String> cookies;

    public HttpCookies() {
        this.cookies = new HashMap<>();
    }

    public HttpCookies(String cookieString) {
        if (cookieString == null || cookieString.isBlank()) {
            this.cookies = Collections.emptyMap();
            return;
        }

        this.cookies = Arrays.stream(cookieString.split(","))
                             .map(String::trim)
                             .map(cookie -> cookie.split("=", 2))
                             .collect(Collectors.toMap(token -> token[0], token -> token[1]));
    }

    public void addCookie(String name, String value) {
        cookies.put(name, value);
    }

    public String getCookie(String name) {
        return cookies.get(name);
    }
}
