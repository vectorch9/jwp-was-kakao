package api;

import static webserver.cookie.HttpCookieNames.JSESSIONID;

import java.util.Optional;

import webserver.handler.TemplateHandler;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.session.Session;
import webserver.session.SessionStore;

public class LoginFormHandler extends TemplateHandler {

    @Override
    public HttpResponse handle(HttpRequest request) {
        Optional<Session> sessionOptional = SessionStore.getSession(request.getCookie(JSESSIONID.name));

        if (sessionOptional.isEmpty()) {
            return applyTemplate(request.getPath());
        }

        return HttpResponse.redirect("/index.html");
    }
}
