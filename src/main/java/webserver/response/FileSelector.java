package webserver.response;

import java.util.List;

public class FileSelector {

    private final List<String> bases;

    public FileSelector(List<String> bases) {
        this.bases = bases;
    }

//    public String select(String path) {
//        for (String base : bases) {
//            if (FileIoUtils.existsFilePath(base + path)) {
//                return base + path;
//            }
//        }
//        throw new IllegalArgumentException("File not found");
//    }
}
