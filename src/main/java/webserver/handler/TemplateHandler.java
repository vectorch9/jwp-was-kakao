package webserver.handler;

import java.io.IOException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import webserver.response.HttpResponse;
import webserver.response.MediaType;

public abstract class TemplateHandler implements Handler {

    private static final String PREFIX = "/templates";
    private static final String SUFFIX = "";
    private final Handlebars handlebars;

    protected TemplateHandler() {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix(PREFIX);
        loader.setSuffix(SUFFIX);
        handlebars = new Handlebars(loader);
    }

    public HttpResponse applyTemplate(String path, Object model) {
        try {
            byte[] content = handlebars.compile(path)
                                       .apply(model)
                                       .getBytes();
            return HttpResponse.ok(MediaType.HTML, content);
        } catch (IOException e) {
            return HttpResponse.notFound();
        }
    }
}
