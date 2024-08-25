import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        BufferedImage image = ImageProcessor.readImage("test/circle.png");

        //testing functions
        BufferedImage scaledImage = ImageProcessor.scaleImage(image,4);
        ImageProcessor.saveImage(scaledImage,"scaled");
        BufferedImage grayImage = ImageProcessor.applyGrayscale(scaledImage);
        ImageProcessor.saveImage(grayImage,"gray");
        BufferedImage sobelledImage300 = ImageProcessor.applySobel(grayImage,300);
        ImageProcessor.saveImage(sobelledImage300,"sobel300");
        BufferedImage sobelledImage200 = ImageProcessor.applySobel(grayImage,200);
        ImageProcessor.saveImage(sobelledImage200,"sobel200");
        BufferedImage sobelledImage100 = ImageProcessor.applySobel(grayImage,80);
        ImageProcessor.saveImage(sobelledImage100,"sobel100");
        BufferedImage combinedImage = ImageProcessor.combineImages(grayImage,sobelledImage100);
        ImageProcessor.saveImage(combinedImage,"combined");

        /*TODO:calculate direction of gradient,render ascii based on direction
        Calculate a color based on the angle and color multiples in the same color, eliminate area that is not the circle itself
        only after that try mapping ascii based on the colors*/

        float[][] luminanceMap = ASCIIMagic.getLuminance(combinedImage);
        char[][] asciiMap = ASCIIMagic.getASCIIMap(luminanceMap);

        double[][] sobelGradient = ASCIIMagic.getSobelGradient(scaledImage);
        char[][] asciiSobel = ASCIIMagic.getASCIISobel(sobelGradient);

        //ASCIIMagic.printASCIIMap(asciiMap);
        ASCIIMagic.printASCIIMap(asciiSobel);
    }
}
