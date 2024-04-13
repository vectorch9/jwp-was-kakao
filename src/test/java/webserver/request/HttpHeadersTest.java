package webserver.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;

public class HttpHeadersTest {

    @Test
    void 헤더를_읽는다() {
        // given
        List<String> headerLines = List.of("Host: localhost:8080", "Connection: keep-alive", "Accept: */*");

        // when
        HttpHeaders headers = new HttpHeaders(headerLines);

        // then
        assertAll(
                () -> assertThat(headers.getHeader("Host")).isEqualTo("localhost:8080"),
                () -> assertThat(headers.getHeader("Connection")).isEqualTo("keep-alive"),
                () -> assertThat(headers.getHeader("Accept")).isEqualTo("*/*")
        );
    }
}
