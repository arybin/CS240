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
    private Pixel[][] image;
    private int x;
    private int y;

    public Pixel(final String red, final String green, final String blue, final boolean isEdge, Pixel[][] image) {
        this.sRed = red;
        this.RED = Integer.valueOf(red);
        this.sGreen = green;
        this.GREEN = Integer.valueOf(green);
        this.sBlue = blue;
        this.BLUE = Integer.valueOf(blue);
        this.isEDGE = isEdge;
        this.image = image;
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
        this.RED = Math.abs(this.RED - MAX_VALUE);
        this.GREEN = Math.abs(this.GREEN - MAX_VALUE);
        this.BLUE = Math.abs(this.BLUE - MAX_VALUE);
        setStringValues();
    }

    private void setStringValues() {
        this.sRed = String.valueOf(this.RED);
        this.sGreen = String.valueOf(this.GREEN);
        this.sBlue = String.valueOf(this.BLUE);
    }

    public void grayScale() {
        int average = (this.RED + this.GREEN + this.BLUE) / TOTAL_COLORS;
        this.RED = average;
        this.GREEN = average;
        this.BLUE = average;
        setStringValues();
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
