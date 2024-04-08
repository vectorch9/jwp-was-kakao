package webserver.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class FileViewTest {

    @Test
    void HttpResponse를_생성한다() {
        // given
        FileView view = new FileView();

        // when
        HttpResponse response = view.readFromPath("./test.html");

        // then
        assertAll(
                () -> assertThat(response.getStatus()).isEqualTo(HttpStatus.OK),
                () -> assertThat(response.getHeaders())
                        .containsEntry("Content-Type", "text/html;charset=utf-8")
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
