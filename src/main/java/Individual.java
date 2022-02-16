import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Individual implements Comparable<Individual> {
    private final List<Polygonal> polygons;
    private final BufferedImage bufferedImage;
    private Double errorPercentage;

    public Individual(List<Polygonal> polygons, int width, int height) {
        this.polygons = polygons;
        this.errorPercentage = 0.;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public List<Polygonal> getPolygons() {
        return polygons;
    }

    public Double getErrorPercentage() {
        return errorPercentage;
    }

    public void drawItself() {
        Graphics g = bufferedImage.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        polygons.forEach(x -> {
            x.drawPolygon(g);
        });
        g.dispose();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setErrorPercentage(Double errorPercentage) {
        this.errorPercentage = errorPercentage;
    }

    @Override
    public int compareTo(Individual u) {
        if (getErrorPercentage() == null || u.getErrorPercentage() == null) {
            return 0;
        }
        return getErrorPercentage().compareTo(u.getErrorPercentage());
    }
}
