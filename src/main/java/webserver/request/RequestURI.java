package webserver.request;

public class RequestURI {

    public static final String QUERY_STRING_DELIMITER = "\\?";
    public static final String BLANK = "";
    private final String path;
    private final QueryParameters queryParameters;

    public RequestURI(String uri) {
        String[] tokens = uri.split(QUERY_STRING_DELIMITER);
        this.path = tokens[0];
        this.queryParameters = new QueryParameters(tokens.length > 1 ? tokens[1] : BLANK);
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String key) {
        return queryParameters.getParameter(key);
    }
}
