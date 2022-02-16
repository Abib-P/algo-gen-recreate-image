import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComparator {
    public Double compareImage(BufferedImage img1, BufferedImage img2) {
        double error = 0.;
        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                error += comparePixel(new Color(img1.getRGB(x, y), true), new Color(img2.getRGB(x, y), true));
            }
        }
        return error / (img1.getHeight()*img1.getWidth());
    }

    private Double comparePixel(Color color1, Color color2) {
        int red = Math.abs(color1.getRed() - color2.getRed());
        int green = Math.abs(color1.getGreen() - color2.getGreen());
        int blue = Math.abs(color1.getBlue() - color2.getBlue());
        int alpha = Math.abs(color1.getAlpha() - color2.getAlpha());

        return (red + green + blue + alpha)/(255.*4.);
    }
}
