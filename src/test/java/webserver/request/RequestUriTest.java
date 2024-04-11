package webserver.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class RequestUriTest {

    @Test
    void path를_읽는다() {
        // given
        String uriString = "/index.html?userId=vector&password=1234";

        // when
        RequestURI uri = new RequestURI(uriString);

        // then
        assertThat(uri.getPath()).isEqualTo("/index.html");
    }

    @Test
    void 파라매터를_읽는다() {
        // given
        String uriString = "/index.html?userId=vector&password=1234";

        // when
        RequestURI uri = new RequestURI(uriString);

        // then
        assertAll(
                () -> assertThat(uri.getParameter("userId")).isEqualTo("vector"),
                () -> assertThat(uri.getParameter("password")).isEqualTo("1234")
        );
    }
}
