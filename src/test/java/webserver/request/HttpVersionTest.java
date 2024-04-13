package webserver.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class HttpVersionTest {

    @Test
    void HTTP10() {
        // when
        HttpVersion httpVersion = HttpVersion.from("HTTP/1.0");

        // then
        assertThat(httpVersion).isEqualTo(HttpVersion.HTTP10);
    }

    @Test
    void HTTP11() {
        // when
        HttpVersion httpVersion = HttpVersion.from("HTTP/1.1");

        // then
        assertThat(httpVersion).isEqualTo(HttpVersion.HTTP11);
    }

    @Test
    void HTTP20() {
        // when
        HttpVersion httpVersion = HttpVersion.from("HTTP/2.0");

        // then
        assertThat(httpVersion).isEqualTo(HttpVersion.HTTP20);
    }
}
