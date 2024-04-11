package api;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatus;

public class RootController {

    public HttpResponse redirect(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        response.responseStatus(HttpStatus.FOUND);
        response.addHeader("Location", "/index.html");
        return response;
    }
}
