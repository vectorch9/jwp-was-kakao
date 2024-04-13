package webserver.request;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryParameters {

    public static final String PARMETER_DELIMITER = "&";
    public static final String KEY_VALUE_DELIMITER = "=";
    private final Map<String, String> parameters;

    public QueryParameters(String queryString) {
        if (queryString.isBlank()) {
            this.parameters = Collections.emptyMap();
            return;
        }

        this.parameters = Arrays.stream(queryString.split(PARMETER_DELIMITER))
                                .map(query -> query.split(KEY_VALUE_DELIMITER, 2))
                                .collect(Collectors.toMap(query -> query[0], query -> query[1]));
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }
}
