import java.awt.*;
import java.util.Random;

public class PolygonFactory {
    private final Double maxHeight;
    private final Double maxWidth;
    private final Random random;

    public PolygonFactory(final Double maxHeight, final Double maxWidth) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        this.random = new Random();
    }

    public Polygonal createTriangle() {
        int[] x = {getRandomXPosition(), getRandomXPosition(), getRandomXPosition()};
        int[] y = {getRandomYPosition(), getRandomYPosition(), getRandomYPosition()};

        return new Polygonal(new Polygon(x, y, 3), new PolygonColor(getRandomColor(), getRandomColor(), getRandomColor(), getRandomColor()));
    }

    private Integer getRandomXPosition() {
        return random.nextInt(maxWidth.intValue() * 2) - (int) (maxWidth / 2);
    }

    private Integer getRandomYPosition() {
        return random.nextInt(maxHeight.intValue() * 2) - (int) (maxHeight / 2);
    }

    private Integer getRandomColor() {
        return random.nextInt(256);
    }

    public Double getMaxHeight() {
        return maxHeight;
    }

    public Double getMaxWidth() {
        return maxWidth;
    }
}
