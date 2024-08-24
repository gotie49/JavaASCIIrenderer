import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        BufferedImage image = ImageProcessor.readImage("test/xdd.png");

        //testing functions
        BufferedImage scaledImage = ImageProcessor.scaleImage(image,2);
        ImageProcessor.saveImage(scaledImage,"scaled");
        BufferedImage grayImage = ImageProcessor.applyGrayscale(scaledImage);
        ImageProcessor.saveImage(grayImage,"gray");
        BufferedImage sobelledImage = ImageProcessor.applySobel(grayImage,300);
        ImageProcessor.saveImage(sobelledImage,"sobel300");
        BufferedImage sobelledImage200 = ImageProcessor.applySobel(grayImage,200);
        ImageProcessor.saveImage(sobelledImage200,"sobel200");
        BufferedImage sobelledImage300 = ImageProcessor.applySobel(grayImage,100);
        ImageProcessor.saveImage(sobelledImage300,"sobel100");


        //TODO:add image information together,calculate direction of gradient,render ascii based on direction

        float[][] luminanceMap = ASCIIMagic.getLuminance(scaledImage);
        char[][] asciiMap = ASCIIMagic.getASCIIMap(luminanceMap);
        ASCIIMagic.printASCIIMap(asciiMap);
    }
}
