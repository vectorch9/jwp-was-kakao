package webserver.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class RequestLineTest {

    @Test
    void method를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        RequestLine line = new RequestLine(requestLine);

        // then
        assertThat(line.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    void path를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        RequestLine line = new RequestLine(requestLine);

        // then
        assertThat(line.getPath()).isEqualTo("/index.html");
    }

    @Test
    void 파라매터를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        RequestLine line = new RequestLine(requestLine);

        // then
        assertAll(
                () -> assertThat(line.getParameter("userId")).isEqualTo("vector"),
                () -> assertThat(line.getParameter("password")).isEqualTo("1234")
        );
    }

    @Test
    void version을_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        RequestLine line = new RequestLine(requestLine);

        // then
        assertThat(line.getVersion()).isEqualTo(HttpVersion.HTTP11);
    }
}
