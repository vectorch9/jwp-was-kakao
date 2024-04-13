package webserver.request;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpHeaders {

    private final Map<String, String> headers;

    public HttpHeaders(List<String> headers) {
        if (headers == null || headers.isEmpty()) {
            this.headers = Collections.emptyMap();
            return;
        }
        this.headers = headers.stream()
                              .map(header -> header.split(": ", 2))
                              .collect(Collectors.toMap(header -> header[0].trim(), header -> header[1].trim()));
    }

    public String getHeader(String key) {
        return headers.get(key);
    }
}
