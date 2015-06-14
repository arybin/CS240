/**
 * Created by andrei on 6/13/15.
 */
public class Pixel {
    private int RED;
    private int GREEN;
    private int BLUE;
    private boolean isEDGE;
    //this is just to save the position of self and get the reference to the neighbors
    private Pixel[][] image;
    private int x;
    private int y;

    public Pixel(final int red, final int green, final int blue, final boolean isEdge) {
        this.RED = red;
        this.GREEN = green;
        this.BLUE = blue;
        this.isEDGE = isEdge;
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

    public void setImage(Pixel[][] image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
