package api;

import static webserver.cookie.HttpCookieNames.JSESSIONID;

import java.util.Map;

import db.DataBase;
import model.User;
import webserver.handler.Handler;
import webserver.parser.HttpRequestBodyParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.session.Session;
import webserver.session.SessionStore;

public class LoginHandler implements Handler {

    private final HttpRequestBodyParser parser;

    public LoginHandler(HttpRequestBodyParser parser) {
        this.parser = parser;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        Map<String, String> params = parser.parseToMap(request);

        User user = DataBase.findUserById(params.get("userId"));
        if (user.matchPassword(params.get("password"))) {
            Session session = SessionStore.createSession();
            session.setAttribute("user", user);

            HttpResponse response = HttpResponse.redirect("/index.html");
            response.addCookie(JSESSIONID.name, session.getId());
            return response;
        }

        return HttpResponse.redirect("/login_failed.html");
    }
}
