package webserver.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import utils.FileIoUtils;
import webserver.request.HttpMethod;
import webserver.request.HttpRequest;
import webserver.response.FileView;
import webserver.response.HttpResponse;
import webserver.response.HttpStatus;

public class StaticResourceHandlerTest {

    @Test
    void static_파일을_응답한다() throws IOException, URISyntaxException {
        // given
        StaticResourceHandler handler = new StaticResourceHandler();
        HttpRequest request = new HttpRequest(HttpMethod.GET, "/static-test.css", "HTTP/1.1",
                                              null, null, null);
        byte[] actual = FileIoUtils.loadFileFromClasspath("./static/static-test.css");

        // when
        HttpResponse response = handler.handle(request);

        // then
        assertAll(
                () -> assertThat(response.getStatus()).isEqualTo(HttpStatus.OK),
                () -> assertThat(response.getContent()).isEqualTo(actual)
        );
    }

    @Test
    void templates_파일을_응답한다() throws IOException, URISyntaxException {
        // given
        StaticResourceHandler handler = new StaticResourceHandler();
        HttpRequest request = new HttpRequest(HttpMethod.GET, "/templates-test.css", "HTTP/1.1",
                                              null, null, null);
        byte[] actual = FileIoUtils.loadFileFromClasspath("./templates/templates-test.css");

        // when
        HttpResponse response = handler.handle(request);

        // then
        assertAll(
                () -> assertThat(response.getStatus()).isEqualTo(HttpStatus.OK),
                () -> assertThat(response.getContent()).isEqualTo(actual)
        );
    }

    @Test
    void 둘다_있으면_templates_파일을_응답한다() throws IOException, URISyntaxException {
        // given
        StaticResourceHandler handler = new StaticResourceHandler();
        HttpRequest request = new HttpRequest(HttpMethod.GET, "/test.html", "HTTP/1.1",
                                              null, null, null);
        byte[] actual = FileIoUtils.loadFileFromClasspath("./templates/test.html");

        // when
        HttpResponse response = handler.handle(request);

        // then
        assertAll(
                () -> assertThat(response.getStatus()).isEqualTo(HttpStatus.OK),
                () -> assertThat(response.getContent()).isEqualTo(actual)
        );
    }

    @Test
    void 파일이_존재하지_않으면_404_응답을_반환한다() {
        // given
        FileView view = new FileView();

        // when
        HttpResponse response = view.readFromPath("./invalid.html");

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
