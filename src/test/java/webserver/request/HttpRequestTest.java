package webserver.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;

public class HttpRequestTest {

    @Test
    void method를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        HttpRequest request = new HttpRequest(requestLine, null, null);

        // then
        assertThat(request.getMethod()).isEqualTo(HttpMethod.GET);
    }

    @Test
    void path를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        HttpRequest request = new HttpRequest(requestLine, null, null);

        // then
        assertThat(request.getPath()).isEqualTo("/index.html");
    }

    @Test
    void 파라매터를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        HttpRequest request = new HttpRequest(requestLine, null, null);

        // then
        assertAll(
                () -> assertThat(request.getParameter("userId")).isEqualTo("vector"),
                () -> assertThat(request.getParameter("password")).isEqualTo("1234")
        );
    }

    @Test
    void version을_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";

        // when
        HttpRequest request = new HttpRequest(requestLine, null, null);

        // then
        assertThat(request.getVersion()).isEqualTo(HttpVersion.HTTP11);
    }

    @Test
    void 헤더를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";
        List<String> headerLines = List.of("Host: localhost:8080", "Connection: keep-alive", "Accept: */*");

        // when
        HttpRequest request = new HttpRequest(requestLine, headerLines, null);

        // then
        assertAll(
                () -> assertThat(request.getHeader("Host")).isEqualTo("localhost:8080"),
                () -> assertThat(request.getHeader("Connection")).isEqualTo("keep-alive"),
                () -> assertThat(request.getHeader("Accept")).isEqualTo("*/*")
        );
    }

    @Test
    void 쿠키를_읽는다() {
        // given
        String requestLine = "GET /index.html?userId=vector&password=1234 HTTP/1.1";
        List<String> headerLines = List.of("Host: localhost:8080", "Connection: keep-alive", "Accept: */*",
                                           "Cookie: JESSIONID=1234, logined=true");

        // when
        HttpRequest request = new HttpRequest(requestLine, headerLines, null);

        // then
        assertAll(
                () -> assertThat(request.getCookie("JESSIONID")).isEqualTo("1234"),
                () -> assertThat(request.getCookie("logined")).isEqualTo("true")
        );
    }
}
