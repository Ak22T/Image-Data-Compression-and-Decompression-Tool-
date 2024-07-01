
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageWriter {
    public static void writeImage(int[][] pixelData, String outputPath) throws IOException {
        int width = pixelData.length;
        int height = pixelData[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixelData[x][y]);
            }
        }
        ImageIO.write(image, "png", new File(outputPath));
    }
}
