package webserver.response;

public enum MediaType {
    HTML("text/html", "html"),
    CSS("text/css", "css"),
    JAVASCRIPT("text/javascript", "js"),
    JSON("application/json", "json"),
    OCTET_STREAM("application/octet-stream", "");

    public static final String EXTENSION_DELIMITER = ".";
    public static final String CHARSET_UTF_8 = ";charset=utf-8";
    private final String mediaType;
    private final String extension;

    MediaType(String mediaType, String extension) {
        this.mediaType = mediaType;
        this.extension = extension;
    }

    public static MediaType fromExtension(String extension) {
        extension = extension.toLowerCase();
        for (MediaType mediaType : values()) {
            if (mediaType.extension.equals(extension)) {
                return mediaType;
            }
        }
        return OCTET_STREAM;
    }

    public static MediaType fromPath(String path) {
        int index = path.lastIndexOf(EXTENSION_DELIMITER);
        if (index == -1) {
            return MediaType.OCTET_STREAM;
        }
        String extension = path.substring(index + 1).toLowerCase();
        return MediaType.fromExtension(extension);
    }

    public String getMediaType() {
        return mediaType + CHARSET_UTF_8;
    }

}
