package webserver.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class QueryParametersTest {

    @Test
    void 파라매터를_읽는다() {
        // given
        String queryString = "userId=vector&password=1234";

        // when
        QueryParameters queryParameters = new QueryParameters(queryString);

        // then
        assertAll(
                () -> assertThat(queryParameters.getParameter("userId")).isEqualTo("vector"),
                () -> assertThat(queryParameters.getParameter("password")).isEqualTo("1234")
        );
    }

    @Test
    void 쿼리_파라매터에_등호가_포함될_수_있다() {
        // given
        String queryString = "user=vector=1234&password=1234";

        // when
        QueryParameters queryParameters = new QueryParameters(queryString);

        // then
        assertAll(
                () -> assertThat(queryParameters.getParameter("user")).isEqualTo("vector=1234"),
                () -> assertThat(queryParameters.getParameter("password")).isEqualTo("1234")
        );
    }
}
