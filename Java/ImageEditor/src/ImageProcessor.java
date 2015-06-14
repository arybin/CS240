import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by andrei on 6/13/15.
 */
public class ImageProcessor {
    private static final Pattern comment = Pattern.compile("#[^\\n]*\\n");
    private static final Pattern number = Pattern.compile(" [01](\\d\\d?)? | 2[0-4]\\d | 25[0-5] | [3-9]\\d?");

    private String fileName;
    private String content;

    public ImageProcessor(String fileName) {
        this.fileName = fileName;
        getContent();
    }

    private void getContent() {
        try {
            content = new String(Files.readAllBytes(Paths.get(this.fileName)));
            Stream<String> lines = Files.lines(Paths.get(this.fileName));
            String[] actualLines = lines.toArray(size -> new String[size]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printContent(String content) {
        System.out.println(content);
    }

    public void invert() {

    }

    public void grayScale() {

    }

    public void emboss() {

    }

    public void motionBlur() {

    }
}
