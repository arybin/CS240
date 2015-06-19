/**
 * Created by andrei on 6/13/15.
 */
public class Pixel {
    private final static int MAX_VALUE = 255;
    private final static int TOTAL_COLORS = 3;

    private int RED;
    private int GREEN;
    private int BLUE;
    private String sRed;
    private String sGreen;
    private String sBlue;
    private boolean isEDGE;
    //this is just to save the position of self and get the reference to the neighbors
    private Pixel[][] originalImage;
    private Pixel[][] changedImage;
    private int x;
    private int y;
    private int motionValue;

    public Pixel(final int red,
                 final int green,
                 final int blue) {
        this.RED = red;
        this.sRed = String.valueOf(red);
        this.GREEN = green;
        setStringValues();
    }

    public Pixel(final String red,
                 final String green,
                 final String blue,
                 final boolean isEdge,
                 final Pixel[][] originalImage,
                 final Pixel[][] changedImage,
                 final int x,
                 final int y) {
        this.sRed = red;
        this.RED = Integer.valueOf(red);
        this.sGreen = green;
        this.GREEN = Integer.valueOf(green);
        this.sBlue = blue;
        this.BLUE = Integer.valueOf(blue);
        this.isEDGE = isEdge;
        this.originalImage = originalImage;
        this.changedImage = changedImage;
        this.x = x;
        this.y = y;
    }

    public int getRed() {
        return RED;
    }

    public int getGreen() {
        return GREEN;
    }

    public int getBlue() {
        return BLUE;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void invert() {
        int red = Math.abs(this.RED - MAX_VALUE);
        int green = Math.abs(this.GREEN - MAX_VALUE);
        int blue = Math.abs(this.BLUE - MAX_VALUE);
        setStringValues();
        Pixel p = new Pixel(red, green, blue);
        this.changedImage[y][x] = p;
    }

    private void setStringValues() {
        this.sRed = String.valueOf(this.RED);
        this.sGreen = String.valueOf(this.GREEN);
        this.sBlue = String.valueOf(this.BLUE);
    }

    public void grayScale() {
        int average = (this.RED + this.GREEN + this.BLUE) / TOTAL_COLORS;
        int red = average;
        int green = average;
        int blue = average;
        setStringValues();
        Pixel p = new Pixel(red, green, blue);
        this.changedImage[y][x] = p;
    }

    public void emboss() {
        int embossValue = 0;
        if ((x - 1) < 0 || (y - 1) < 0) {
            embossValue = 128;
        } else {
            int redDiff = Math.abs(this.RED - this.originalImage[y - 1][x - 1].getRed());
            int greenDiff = Math.abs(this.GREEN - this.originalImage[y - 1][x - 1].getGreen());
            int blueDiff = Math.abs(this.BLUE - this.originalImage[y - 1][x - 1].getBlue());
            int maxDifference = Math.max(redDiff, Math.max(greenDiff, blueDiff));
            embossValue = maxDifference;
        }

        embossValue += 128;

        if (embossValue < 0) {
            embossValue = 0;
        } else if (embossValue > 255) {
            embossValue = 255;
        }
        Pixel p = new Pixel(embossValue, embossValue, embossValue);
        this.changedImage[y][x] = p;

    }

    public void setMotionValue(final int motionValue) {
        this.motionValue = motionValue;
    }

    public void motionBlur() {
        Pixel[] row = this.originalImage[y];
        int blurValue = motionValue;
        //how far are we moving in the row
        int red  = 0;
        int green = 0;
        int blue = 0;
        //this should be the number of cells to check, thus making values averages
        if(blurValue + x >= row.length) {
            blurValue = row.length - x;
        }
        for(int i = x; i < blurValue + x - 1; i++) {
            red += row[i].getRed();
            green += row[i].getGreen();
            blue += row[i].getBlue();
        }
        red /= blurValue;
        green /= blurValue;
        blue /= blurValue;
        Pixel p = new Pixel(red, green, blue);
        changedImage[y][x] = p;

    }

    public String getsRed() {
        return sRed;
    }

    public String getsGreen() {
        return sGreen;
    }

    public String getsBlue() {
        return sBlue;
    }
}
