package webserver.parser;

import static webserver.request.HttpHeaderKey.CONTENT_LENGTH;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import utils.IOUtils;

import webserver.request.HttpRequest;

public class HttpRequestBodyParser {

    public static final String PARAMETER_DELIMITER = "&";
    public static final String KEY_VALUE_DELIMITER = "=";

    public Map<String, String> parseToMap(HttpRequest request) {
        if (!request.hasBody()) {
            throw new IllegalArgumentException("Http body가 존재하지 않습니다");
        }

        int contentLength = Integer.parseInt(request.getHeader(CONTENT_LENGTH.key));
        byte[] bodyContent = IOUtils.readData(request.getReader(), contentLength).getBytes();
        String body = new String(bodyContent, StandardCharsets.UTF_8);

        return Arrays.stream(body.split(PARAMETER_DELIMITER))
                     .map(elem -> elem.split(KEY_VALUE_DELIMITER))
                     .collect(Collectors.toMap(tokens -> tokens[0], tokens -> tokens[1]));
    }
}
