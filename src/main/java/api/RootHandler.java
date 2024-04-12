package api;

import webserver.handler.Handler;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

public class RootHandler implements Handler {

    @Override
    public HttpResponse handle(HttpRequest request) {
        return HttpResponse.redirect("index.html");
    }
}
