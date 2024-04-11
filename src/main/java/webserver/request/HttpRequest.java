package webserver.request;

import java.io.BufferedReader;
import java.util.List;

public class HttpRequest {

    private final RequestLine requestLine;
    private final HttpHeaders headers;
    private final BufferedReader reader;

    public HttpRequest(String requestLine, List<String> headers, BufferedReader reader) {
        this.requestLine = new RequestLine(requestLine);
        this.headers = new HttpHeaders(headers);
        this.reader = reader;
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
        return headers.getHeader(key);
    }

    public String getParameter(String key) {
        return requestLine.getParameter(key);
    }

    public BufferedReader getReader() {
        return reader;
    }
}
