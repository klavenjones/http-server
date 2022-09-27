package httpserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileViewerTest {
    @Test
    @DisplayName("Test if png file exists in project")
    void testIfPNGFileExistsInProject() throws IOException {
        FileViewer fileViewer = new FileViewer();
        byte[] file = fileViewer.getFileData("web/doggo.png");
        assertNotNull(file);
    }

    @Test
    @DisplayName("Test if jpg file exists in project")
    void testIfJPEGFileExistsInProject() throws IOException {
        FileViewer fileViewer = new FileViewer();
        byte[] file = fileViewer.getFileData("web/kitteh.jpg");
        assertNotNull(file);
    }

    @Test
    @DisplayName("Test if gif file exists in project")
    void testIfGifFileExistsInProject() throws IOException {
        FileViewer fileViewer = new FileViewer();
        byte[] file = fileViewer.getFileData("web/kisses.gif");
        assertNotNull(file);
    }

    @Test
    @DisplayName("Test if html file exists in project")
    void testIfHTMLFileExistsInProject() throws IOException {
        FileViewer fileViewer = new FileViewer();
        byte[] file = fileViewer.getFileData("web/health-check.html");

        assertNotNull(file);
    }
}
