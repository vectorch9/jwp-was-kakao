package webserver.request;

import java.util.Map;

public class HttpRequest {

    private final RequestLine requestLine;
    private final Map<String, String> headers;
    private final byte[] bodyContent;

    // TODO: 리펙토링
    public HttpRequest(String requestLine, Map<String, String> headers, byte[] bodyContent) {
        this.requestLine = new RequestLine(requestLine);
        this.headers = headers;
        this.bodyContent = bodyContent;
    }

    public HttpMethod getMethod() {
        return requestLine.getMethod();
    }

    public String getPath() {
        return requestLine.getPath();
    }

    public HttpVersion getVersion() {
        return requestLine.getVersion();
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public String getParameter(String key) {
        return requestLine.getParameter(key);
    }

    public byte[] getBodyContent() {
        return bodyContent;
    }
}
