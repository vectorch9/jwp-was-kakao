package webserver.request;

public enum HttpHeaderKey {
    CONTENT_TYPE("Content-Type"),
    CONTENT_LENGTH("Content-Length"),
    CONNECTION("Connection"),
    HOST("Host"),
    ACCEPT("Accept"),
    LOCATION("Location");

    public final String key;

    HttpHeaderKey(String key) {
        this.key = key;
    }
}
