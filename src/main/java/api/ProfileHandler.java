package api;

import static webserver.cookie.HttpCookieNames.JSESSIONID;

import java.util.Optional;

import model.User;
import webserver.handler.TemplateHandler;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.session.Session;
import webserver.session.SessionStore;

public class ProfileHandler extends TemplateHandler {

    @Override
    public HttpResponse handle(HttpRequest request) {
        Optional<Session> sessionOptional = SessionStore.getSession(request.getCookie(JSESSIONID.name));

        if (sessionOptional.isEmpty()) {
            return HttpResponse.redirect("/user/login.html");
        }
        User user = (User) sessionOptional.get().getAttribute("user");

        return applyTemplate(request.getPath(), user);
    }
}
