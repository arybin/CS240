import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by andrei on 6/13/15.
 */
public class ImageProcessor {
    private static final Pattern comment = Pattern.compile("#[^\\n]*\\n");
    private static final Pattern number = Pattern.compile(" [01](\\d\\d?)? | 2[0-4]\\d | 25[0-5] | [3-9]\\d?");
    private static final int ROWS_TO_IGNORE = 4;
    private static final int WIDTH_AND_HEIGHT = 2;

    private String filePath;
    private String fileName;
    private String content;
    private int width;
    private int height;
    private Pixel[][] image;

    //man, Java 8 is cool
    private Consumer<Pixel> invertAction = p -> p.invert();
    private Consumer<Pixel> grayScaleAction = p->p.grayScale();

    public ImageProcessor(String filePath) {
        this.filePath = filePath;
        this.fileName = Pattern.compile("/")
                .splitAsStream(filePath)
                .filter(f -> f.contains(".ppm"))
                .findAny()
                .get();
        getContent();
    }

    private void getContent() {
        try {
            content = new String(Files.readAllBytes(Paths.get(this.filePath)));
            Stream<String> lines = Files.lines(Paths.get(this.filePath))
                    .parallel();
            String[] actualLines = lines
                    .parallel()
                    .toArray(size -> new String[size]);
            String[] widthAndHeight = actualLines[WIDTH_AND_HEIGHT].split(" ");
            this.width = Integer.valueOf(widthAndHeight[0]);
            this.height = Integer.valueOf(widthAndHeight[1]);
            String[] imageData = new String[actualLines.length - ROWS_TO_IGNORE];
            //make a copy of the actual data that's needed
            System.arraycopy(actualLines, ROWS_TO_IGNORE, imageData, 0, imageData.length);
            populateImageMatrix(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateImageMatrix(String[] imageData) {
        image = new Pixel[height][width];
        int horizontalPosition = 0;
        int verticalPosition = 0;
        int i = 0;
        while (i < imageData.length) {
            String red = imageData[i];
            ++i;
            String green = imageData[i];
            ++i;
            String blue = imageData[i];
            ++i;
            boolean isEdge =
                    horizontalPosition == 0 ||
                            verticalPosition == 0 ||
                            horizontalPosition + 1 == width ||
                            verticalPosition + 1 == height;
            Pixel p = new Pixel(red, green, blue, isEdge, image);
            image[verticalPosition][horizontalPosition] = p;
            horizontalPosition++;
            //if horizontal is at the end of the line, move to the next vertical position, reset the value
            if ((horizontalPosition % width) == 0) {
                verticalPosition++;
                horizontalPosition = 0;
            }
        }
    }

    private void printContent(String content) {
        System.out.println(content);
    }

    private void applyAction(Consumer<Pixel> action, List<String> newContent) {
        newContent.add("P3\n");
        newContent.add(this.width + " " + this.height + "\n");
        newContent.add("255\n");
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                Pixel p = image[i][j];
                action.accept(p);
                newContent.add(p.getsRed() + "\n");
                newContent.add(p.getGreen() + "\n");
                if ((i + 1) != image.length) {
                    newContent.add(p.getBlue() + "\n");
                } else {
                    newContent.add(p.getsBlue());
                }
            }
        }
    }

    public void invert() {
        String invertFileName = String.join("_","TestFiles/invert",this.fileName);
        List<String> newContent = new ArrayList<>();
        applyAction(invertAction, newContent);
        writeNewContent(invertFileName, newContent);


    }

    private void writeNewContent(String newFileName, List<String> newContent) {
        try {
            Files.write(Paths.get(newFileName), newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void grayScale() {
        String invertFileName = String.join("_","TestFiles/grayscale",this.fileName);
        List<String> newContent = new ArrayList<>();
        applyAction(this.grayScaleAction, newContent);
        writeNewContent(invertFileName, newContent);
    }

    public void emboss() {

    }

    public void motionBlur() {

    }
}
