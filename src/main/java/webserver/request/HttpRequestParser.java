package webserver.request;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParser {

    public HttpRequest parseRequest(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String requestLine = reader.readLine();
        String[] tokens = requestLine.split(" ");
        HttpMethod method = HttpMethod.valueOf(tokens[0]);

        String pathToken = tokens[1];
        String path = pathToken.split("\\?")[0];

        String version = tokens[2];

        Map<String, String> headers = parseHeaders(reader);

        return new HttpRequest(method, path, version, headers, parseQueryParameters(pathToken));
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

    private Map<String, String> parseQueryParameters(String pathToken) {
        String[] token = pathToken.split("\\?");
        if (token.length < 2) {
            return Collections.emptyMap();
        }

        Map<String, String> queryParameters = new HashMap<>();
        String[] queries =  token[1].split("&");

        for (String query: queries) {
            String[] queryTokens = query.split("=");
            String key = queryTokens[0];
            String value = queryTokens[1];
            queryParameters.put(key, value);
        }
        return queryParameters;
    }
}
