import java.awt.*;

public class Polygonal {
    private final Polygon polygon;
    private final PolygonColor polygonColor;

    public Polygon getPolygon() {
        return polygon;
    }

    public PolygonColor getPolygonColor() {
        return polygonColor;
    }



    public Polygonal(Polygon polygon, PolygonColor polygonColor) {
        this.polygon = polygon;
        this.polygonColor = polygonColor;
    }

    public void drawPolygon(Graphics graphics) {
        graphics.setColor(polygonColor.getColor());
        graphics.fillPolygon(polygon);
    }
}
