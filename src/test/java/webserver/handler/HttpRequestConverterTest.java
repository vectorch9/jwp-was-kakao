package webserver.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import utils.IOUtils;
import webserver.request.HttpMethod;
import webserver.request.HttpRequest;
import webserver.request.HttpVersion;

public class HttpRequestConverterTest {

    @Test
    void RequestLine을_파싱한다() throws IOException {
        // given
        String requestMessage = "GET / HTTP/1.1\n";
        InputStream in = new ByteArrayInputStream(requestMessage.getBytes());

        // when
        HttpRequestConverter converter = new HttpRequestConverter();
        HttpRequest request = converter.parseRequest(in);

        // then
        assertAll(
                () -> assertThat(request.getMethod()).isEqualTo(HttpMethod.GET),
                () -> assertThat(request.getPath()).isEqualTo("/"),
                () -> assertThat(request.getVersion()).isEqualTo(HttpVersion.HTTP11)
        );
    }

    @Test
    void 헤더를_파싱한다() throws IOException {
        // given
        String requestMessage = "GET / HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Connection: keep-alive\n" +
                "Accept: */*\n" +
                "\n";
        InputStream in = new ByteArrayInputStream(requestMessage.getBytes());

        // when
        HttpRequestConverter converter = new HttpRequestConverter();
        HttpRequest request = converter.parseRequest(in);

        // then
        assertAll(
                () -> assertThat(request.getHeader("Host")).isEqualTo("localhost:8080"),
                () -> assertThat(request.getHeader("Connection")).isEqualTo("keep-alive"),
                () -> assertThat(request.getHeader("Accept")).isEqualTo("*/*")
        );
    }

    @Test
    void 쿼리_파라매터를_파싱한다() throws IOException {
        // given
        String requestMessage = "GET /?a=b&c=d HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Connection: keep-alive\n" +
                "Accept: */*\n" +
                "\n";
        InputStream in = new ByteArrayInputStream(requestMessage.getBytes());

        // when
        HttpRequestConverter converter = new HttpRequestConverter();
        HttpRequest request = converter.parseRequest(in);

        // then
        assertAll(
                () -> assertThat(request.getPath()).isEqualTo("/"),
                () -> assertThat(request.getParameter("a")).isEqualTo("b"),
                () -> assertThat(request.getParameter("c")).isEqualTo("d")
        );
    }

    @Test
    void 리더를_주입한다() throws IOException {
        // given
        String expected = "body\nbodyb";
        String requestMessage = "POST /?a=b&c=d HTTP/1.1\n" +
                "Content-Length: " + expected.getBytes().length + "\n" +
                "\n"
                + expected;
        InputStream in = new ByteArrayInputStream(requestMessage.getBytes());

        // when
        HttpRequestConverter converter = new HttpRequestConverter();
        HttpRequest request = converter.parseRequest(in);
        BufferedReader reader = request.getReader();
        String actual = IOUtils.readData(reader, Integer.parseInt(request.getHeader("Content-Length")));

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
