package api;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import db.DataBase;
import model.User;
import utils.IOUtils;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatus;

public class UserController {

    // TODO: 리펙토링
    public HttpResponse createUser(HttpRequest request) {
        // TODO content-Length에 대한 검증 추가
        int contentLength = Integer.parseInt(request.getHeader("Content-Length"));
        byte[] bodyContent = IOUtils.readData(request.getReader(), contentLength).getBytes();

        String body = new String(bodyContent, StandardCharsets.UTF_8);
        Map<String, String> params = Arrays.stream(body.split("&"))
                                           .map(elem -> elem.split("="))
                                           .collect(Collectors.toMap(tokens -> tokens[0], tokens -> tokens[1]));
        DataBase.addUser(new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email")));

        HttpResponse response = new HttpResponse();
        response.responseStatus(HttpStatus.FOUND);
        response.addHeader("Location", "/index.html");
        return response;
    }
}
