package webserver.cookie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class HttpCookiesTest {

    @Test
    void 쿠키를_파싱한다() {
        // given
        String cookie = "JSESSIONID=1234; logined=true";

        // when
        HttpCookies cookies = new HttpCookies(cookie);

        // then
        assertAll(
                () -> assertThat(cookies.getCookie("JSESSIONID")).isEqualTo("1234"),
                () -> assertThat(cookies.getCookie("logined")).isEqualTo("true")
        );
    }

    @Test
    void 쿠키를_읽어온다() {
        // given
        String cookie = "JSESSIONID=1234; logined=true";

        // when
        HttpCookies cookies = new HttpCookies(cookie);

        // then
        assertThat(cookies.getCookies())
                .hasSize(2)
                .contains("JSESSIONID=1234")
                .contains("logined=true");
    }
}
