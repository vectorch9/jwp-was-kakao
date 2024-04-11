package webserver.request;

public enum HttpVersion {

    HTTP10("HTTP/1.0"),
    HTTP11("HTTP/1.1"),
    HTTP20("HTTP/2.0");

    private final String name;

    HttpVersion(String name) {
        this.name = name;
    }

    public static HttpVersion from(String version) {
        for (HttpVersion value : values()) {
            if (value.name.equals(version)) {
                return value;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 HTTP 버전입니다.");
    }
}
