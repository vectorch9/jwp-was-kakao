package webserver.handler;

import webserver.request.HttpRequest;
import webserver.response.FileView;
import webserver.response.HttpResponse;
import webserver.response.HttpStatus;

public class StaticResourceHandler implements Handler {

    private final FileView templateView;
    private final FileView staticView;

    public StaticResourceHandler() {
        this.staticView = new FileView("./static");
        this.templateView = new FileView("./templates");
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        String path = request.getPath();
        // TODO 객체지향적으로, View에 exists를 먼저?
        HttpResponse response = templateView.readFromPath(path);
        if (response.getStatus() == HttpStatus.NOT_FOUND) {
            response = staticView.readFromPath(path);
        }
        return response;
    }
}
