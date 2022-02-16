import java.awt.*;

public class PolygonColor {
    private final Color color;

    public PolygonColor(int r, int g, int b, int alpha) {
        this.color = new Color(r,g,b,alpha);
    }

    public Color getColor() {
        return color;
    }
}
