package webserver.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpResponseRenderer {

    public static final String CRLF = "\r\n";
    public static final String HEADER_DELIMITER = ":";
    public static final String RESPONSE_LINE = "HTTP/1.1 %d %s \r\n";

    public void render(DataOutputStream dos, HttpResponse response) throws IOException {
        renderResponseLine(dos, response);
        renderHeader(dos, response);
        renderBody(dos, response);

        dos.flush();
    }

    private void renderResponseLine(DataOutputStream dos, HttpResponse response) throws IOException {
        HttpStatus status = response.getStatus();
        dos.writeBytes(String.format(RESPONSE_LINE, status.getCode(), status.getReasonPhrase()));
    }


    private void renderHeader(DataOutputStream dos, HttpResponse response) throws IOException {
        Map<String, String> headers = response.getHeaders();
        String headerLines = headers.entrySet().stream()
                                    .map(header -> header.getKey() + HEADER_DELIMITER + header.getValue() + CRLF)
                                    .collect(Collectors.joining());
        dos.writeBytes(headerLines + CRLF);
    }

    private void renderBody(DataOutputStream dos, HttpResponse response) throws IOException {
        byte[] body = response.getContent();
        if (body != null && body.length != 0) {
            dos.write(body, 0, body.length);
        }
    }
}
