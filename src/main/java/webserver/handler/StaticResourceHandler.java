package webserver.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import webserver.request.HttpRequest;
import webserver.response.FileView;
import webserver.response.HttpResponse;
import webserver.response.MediaType;

public class StaticResourceHandler implements Handler {

    private final List<FileView> views;

    public StaticResourceHandler(String... bases) {
        this.views = Arrays.stream(bases)
                           .map(FileView::new)
                           .collect(Collectors.toList());
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        String path = request.getPath();

        return views.stream()
                    .map(view -> view.readFromPath(path))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(content -> HttpResponse.ok(MediaType.fromPath(path), content))
                    .findFirst()
                    .orElseGet(HttpResponse::notFound);
    }
}
