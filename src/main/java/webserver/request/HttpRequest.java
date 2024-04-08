package webserver.request;

import java.util.Map;

public class HttpRequest {

    private final HttpMethod method;
    private final String path;
    private final String version;
    private final Map<String, String> headers;
    private final Map<String, String> parameters;

    public HttpRequest(HttpMethod method, String path, String version, Map<String, String> headers, Map<String, String> parameters) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.headers = headers;
        this.parameters = parameters;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getVersion() {
        return version;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }
}
