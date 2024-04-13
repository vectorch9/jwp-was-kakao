package webserver.request;

public class RequestLine {

    public static final String DELIMITER = " ";
    private final HttpMethod method;
    private final RequestURI uri;
    private final HttpVersion version;

    public RequestLine(String line) {
        String[] tokens = line.split(DELIMITER);
        this.method = HttpMethod.valueOf(tokens[0]);
        this.uri = new RequestURI(tokens[1]);
        this.version = HttpVersion.from(tokens[2]);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return uri.getPath();
    }

    public String getParameter(String key) {
        return uri.getParameter(key);
    }

    public HttpVersion getVersion() {
        return version;
    }
}
