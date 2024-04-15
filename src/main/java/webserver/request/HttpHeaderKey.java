package webserver.request;

public enum HttpHeaderKey {
    CONTENT_TYPE("Content-Type"),
    CONTENT_LENGTH("Content-Length"),
    CONNECTION("Connection"),
    HOST("Host"),
    ACCEPT("Accept"),
    LOCATION("Location"),
    COOKIE("Cookie");

    public final String key;

    HttpHeaderKey(String key) {
        this.key = key;
    }
}
