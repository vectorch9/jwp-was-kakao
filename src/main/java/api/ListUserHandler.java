package api;

import java.util.Collection;
import java.util.Optional;

import db.DataBase;
import model.User;
import webserver.handler.TemplateHandler;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.session.Session;
import webserver.session.SessionStore;

public class ListUserHandler extends TemplateHandler {

    @Override
    public HttpResponse handle(HttpRequest request) {
        Optional<Session> sessionOptional = SessionStore.getSession(request.getCookie("JSESSIONID"));

        if (sessionOptional.isEmpty()) {
            return HttpResponse.redirect("/user/login.html");
        }

        Collection<User> users = DataBase.findAll();
        return applyTemplate(request.getPath(), users);
    }
}
