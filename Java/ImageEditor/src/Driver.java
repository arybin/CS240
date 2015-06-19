
/**
 * Created by andrei on 6/13/15.
 */
public class Driver {

    private final static String INVERT = "invert";
    private final static String GRAYSCALE = "grayscale";
    private final static String EMBOSS = "emboss";
    private final static String MOTION_BLUR = "motion_blur";

    public static void main(String[] args) {
        String fileName = "TestFiles/audio.ppm";
        String action = "motion_blur".toLowerCase();
        int motionValue = 20;
        ImageProcessor imageProcessor = new ImageProcessor(fileName);
        imageProcessor.setMotionValue(motionValue);
        imageProcessor.processContent();
        switch (action) {
            case INVERT:
                imageProcessor.invert();
                break;
            case GRAYSCALE:
                imageProcessor.grayScale();
                break;
            case EMBOSS:
                imageProcessor.emboss();
                break;
            case MOTION_BLUR:
                imageProcessor.motionBlur();
                break;
            default:
                System.out.println("case doesn't exist");
        }
    }
}
