package webserver.request;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestParser {

    public HttpRequest parseRequest(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String requestLine = reader.readLine();
        String[] tokens = requestLine.split(" ");
        HttpMethod method = HttpMethod.valueOf(tokens[0]);
        String path = tokens[1];
        String version = tokens[2];

        Map<String, String> headers = new HashMap<>();
        String header;
        while((header = reader.readLine()) != null) {
            if(header.isEmpty()) {
                break;
            }
            String[] headerTokens = header.split(":", 2);
            headers.put(headerTokens[0].trim(), headerTokens[1].trim());
        }

        return new HttpRequest(method, path, version, headers, null);
    }
}
