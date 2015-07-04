import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by andrei on 6/13/15.
 */
public class ImageProcessor {
    private static final int ROWS_TO_IGNORE = 4;
    private static final int WIDTH_AND_HEIGHT = 2;

    private String filePath;
    private String fileName;
    private int width;
    private int height;
    private Pixel[][] originalImage;
    private Pixel[][] changedImage;
    private int motionValue;

    //man, Java 8 is cool
    private Consumer<Pixel> invertAction = Pixel::invert;//p -> p.invert();
    private Consumer<Pixel> grayScaleAction = Pixel::grayScale;//p -> p.grayScale();
    private Consumer<Pixel> embossAction = Pixel::emboss; //p -> p.emboss();
    private Consumer<Pixel> motionBlurAction = Pixel::motionBlur; //p -> p.motionBlur();

    public ImageProcessor(String filePath) {
        this.filePath = filePath;
        this.motionValue = 0;
        this.fileName = Pattern.compile("/")
                .splitAsStream(filePath)
                .filter(f -> f.contains(".ppm"))
                .findFirst()
                .get();
    }

    public void processContent() {
        try {
            Stream<String> lines = Files.lines(Paths.get(this.filePath))
                    .parallel();
            populateImageMatrix(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateImageMatrix(Stream<String> lines) {
        Iterator<String> iterator = lines.iterator();
        String fileType = iterator.next();
        String comment = iterator.next();
        String[] dimensions = iterator.next().split(" ");
        String highestValue = iterator.next();
        this.width = Integer.valueOf(dimensions[0]);
        this.height = Integer.valueOf(dimensions[1]);
        originalImage = new Pixel[height][width];
        changedImage = new Pixel[height][width];
        int horizontalPosition = 0;
        int verticalPosition = 0;

        while(iterator.hasNext()) {
            String red = iterator.next();
            String green = iterator.next();
            String blue = iterator.next();
            boolean isEdge =
                    horizontalPosition == 0 ||
                            verticalPosition == 0 ||
                            horizontalPosition + 1 == width ||
                            verticalPosition + 1 == height;
            Pixel p = new Pixel(red,
                    green,
                    blue,
                    isEdge,
                    originalImage,
                    changedImage,
                    horizontalPosition,
                    verticalPosition);
            p.setMotionValue(motionValue);
            originalImage[verticalPosition][horizontalPosition] = p;
            horizontalPosition++;
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
        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                Pixel p = originalImage[i][j];
                action.accept(p);
                p = changedImage[i][j];
                newContent.add(p.getsRed() + "\n");
                newContent.add(p.getsGreen() + "\n");
                if ((i + 1) != originalImage.length) {
                    newContent.add(p.getsBlue() + "\n");
                } else {
                    newContent.add(p.getsBlue());
                }
            }
        }
    }

    public void invert() {
        String invertFileName = String.join("_", "TestFiles/invert", this.fileName);
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
        String invertFileName = String.join("_", "TestFiles/grayscale", this.fileName);
        List<String> newContent = new ArrayList<>();
        applyAction(this.grayScaleAction, newContent);
        writeNewContent(invertFileName, newContent);
    }

    public void emboss() {
        String invertFileName = String.join("_", "TestFiles/emboss", this.fileName);
        List<String> newContent = new ArrayList<>();
        applyAction(this.embossAction, newContent);
        writeNewContent(invertFileName, newContent);

    }

    public void setMotionValue(int motionValue) {
        this.motionValue = motionValue;
    }

    public void motionBlur() {
        String invertFileName = String.join("_", "TestFiles/motionblur", this.fileName);
        List<String> newContent = new ArrayList<>();
        applyAction(this.motionBlurAction, newContent);
        writeNewContent(invertFileName, newContent);
    }
}
