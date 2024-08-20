import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        ImageProcessor imageProcessor = new ImageProcessor("test/xdd.png","png");
        BufferedImage bufferedImage = imageProcessor.readImage("test/xdd.png");
        ASCIIMagic asciiMagic = new ASCIIMagic();

        float[][] luminanceMap = imageProcessor.getLuminance(bufferedImage);
        char[][] asciiMap = asciiMagic.getASCIIMap(luminanceMap);
        asciiMagic.printASCIIMap(asciiMap);
    }
}
