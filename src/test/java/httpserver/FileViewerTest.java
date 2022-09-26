package httpserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;

class FileViewerTest {

    @Test
    @DisplayName("Test if return the path to doggo.png")
    void testIfGetFilePathReturnsDoggoPath() throws IOException {
        String path = new File("").getAbsolutePath().concat("/web/").concat("doggo.png");

        FileViewer fileViewer = new FileViewer();
        String filePath = fileViewer.getFilePath("doggo.png");
        assertEquals(filePath, path);
    }
}
