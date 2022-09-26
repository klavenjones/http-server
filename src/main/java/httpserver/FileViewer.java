package httpserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileViewer {

    public byte[] convertFilePathToByte(String filePath) throws IOException {
        return Files.readAllBytes(new File(filePath).toPath());
    }

    public String getFilePath(String fileName) throws IOException {
        return new File("").getAbsolutePath().concat("/web/").concat(fileName);
    }
}
