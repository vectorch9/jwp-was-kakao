package webserver.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import webserver.request.HttpRequest;

public class HttpRequestConverter {

    public HttpRequest parseRequest(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String requestLine = reader.readLine();

        List<String> headers = readHeaders(reader);

        return new HttpRequest(requestLine, headers, reader);
    }

    private List<String> readHeaders(BufferedReader reader) throws IOException {
        List<String> headerLines = new ArrayList<>();
        String header;
        while ((header = reader.readLine()) != null) {
            if (header.isEmpty()) {
                break;
            }
            headerLines.add(header);
        }
        return headerLines;
    }
}
