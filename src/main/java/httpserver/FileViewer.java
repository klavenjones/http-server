package httpserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileViewer {
    public byte[] getFileData(String filePath) throws IOException {
        File file = new File(filePath);
        Path path = file.toPath();
        return Files.readAllBytes(path);
    }
}
