package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import utils.IOUtils;

public class HttpRequestConverter {

    // TODO: 리펙토링
    public HttpRequest parseRequest(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String requestLine = reader.readLine();

        Map<String, String> headers = parseHeaders(reader);

        byte[] bodyContent = null;
        if (requireReadBody(headers)) {
            int contentLength = Integer.parseInt(headers.get("Content-Length"));
            bodyContent = IOUtils.readData(reader, contentLength).getBytes();
        }

        return new HttpRequest(requestLine, headers, bodyContent);
    }

    private static boolean requireReadBody(Map<String, String> headers) {
        return headers.containsKey("Content-Length");
    }

    private Map<String, String> parseHeaders(BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String header;
        while ((header = reader.readLine()) != null) {
            if (header.isEmpty()) {
                break;
            }
            String[] headerTokens = header.split(":", 2);
            headers.put(headerTokens[0].trim(), headerTokens[1].trim());
        }
        return headers;
    }
}
