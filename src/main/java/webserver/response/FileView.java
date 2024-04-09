package webserver.response;

import utils.FileIoUtils;

public class FileView {

    private final String basePath;

    public FileView(String basePath) {
        this.basePath = basePath;
    }


    public FileView() {
        this("");
    }

    public HttpResponse readFromPath(String path) {
        HttpResponse response = new HttpResponse();

        try {
            byte[] fileContents = FileIoUtils.loadFileFromClasspath(basePath + path);
            response.responseStatus(HttpStatus.OK);
            response.contentType(extractMediaType(path));
            response.responseBody(fileContents);
        } catch (Exception e) {
            response.responseStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    private MediaType extractMediaType(String path) {
        int index = path.lastIndexOf(".");
        if (index == -1) {
            return MediaType.OCTET_STREAM;
        }
        String extension = path.substring(index + 1).toLowerCase();
        return MediaType.fromExtension(extension);
    }

}
