package webserver.response;

import java.util.Optional;

import utils.FileIoUtils;

public class FileView {

    private final String basePath;

    public FileView(String basePath) {
        this.basePath = basePath;
    }


    public FileView() {
        this("");
    }

    public Optional<byte[]> readFromPath(String path) {
        try {
            return Optional.of(FileIoUtils.loadFileFromClasspath(basePath + path));
        } catch (Exception ignored) {
        }
        return Optional.empty();
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
