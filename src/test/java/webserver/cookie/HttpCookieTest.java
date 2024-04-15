package webserver.cookie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class HttpCookieTest {

    @Test
    void 쿠키를_파싱한다() {
        // given
        String cookie = "JSESSIONID=1234, logined=true";

        // when
        HttpCookies cookies = new HttpCookies(cookie);

        // then
        assertAll(
                () -> assertThat(cookies.getCookie("JSESSIONID")).isEqualTo("1234"),
                () -> assertThat(cookies.getCookie("logined")).isEqualTo("true")
        );
    }
}
