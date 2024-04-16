package webserver.cookie;

public enum HttpCookieNames {

    JSESSIONID("JSESSIONID");

    public final String name;

    HttpCookieNames(String name) {
        this.name = name;
    }
}
