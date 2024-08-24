import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        ASCIIMagic asciiMagic = new ASCIIMagic();
        BufferedImage image = ImageProcessor.processImage("test/xdd.png",5);
        float[][] luminanceMap = ImageProcessor.getLuminance(image);
        char[][] asciiMap = asciiMagic.getASCIIMap(luminanceMap);
        asciiMagic.printASCIIMap(asciiMap);
    }
}
