package api;

import java.util.Map;

import db.DataBase;
import model.User;
import webserver.handler.Handler;
import webserver.parser.HttpRequestBodyParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

public class CreateUserHandler implements Handler {

    private final HttpRequestBodyParser parser;

    public CreateUserHandler(HttpRequestBodyParser parser) {
        this.parser = parser;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        Map<String, String> params = parser.parseToMap(request);

        DataBase.addUser(new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email")));

        return HttpResponse.redirect("index.html");
    }
}
