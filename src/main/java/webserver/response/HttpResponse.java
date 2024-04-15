package webserver.response;

import static webserver.request.HttpHeaderKey.CONTENT_LENGTH;
import static webserver.request.HttpHeaderKey.CONTENT_TYPE;
import static webserver.request.HttpHeaderKey.LOCATION;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import webserver.cookie.HttpCookies;

public class HttpResponse {

    private HttpStatus status;
    private final Map<String, String> headers;
    private final HttpCookies cookies;
    private byte[] content;

    public HttpResponse() {
        this.headers = new HashMap<>();
        this.cookies = new HttpCookies();
    }

    public static HttpResponse ok(MediaType contentType, byte[] content) {
        HttpResponse response = new HttpResponse();
        response.responseStatus(HttpStatus.OK);
        response.responseBody(content);
        response.contentType(contentType);
        return response;
    }

    public static HttpResponse redirect(String path) {
        HttpResponse response = new HttpResponse();
        response.responseStatus(HttpStatus.FOUND);
        response.addHeader(LOCATION.key, path);
        return response;
    }

    public static HttpResponse notFound() {
        HttpResponse response = new HttpResponse();
        response.responseStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public void contentType(MediaType mediaType) {
        addHeader(CONTENT_TYPE.key, mediaType.getMediaType());
    }


    public void responseStatus(HttpStatus status) {
        this.status = status;
    }

    public void responseBody(byte[] content) {
        this.content = content;
        addHeader(CONTENT_LENGTH.key, String.valueOf(content.length));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public byte[] getContent() {
        return content;
    }

    public void addCookie(String name, String value) {
        cookies.addCookie(name, value);
    }
}
