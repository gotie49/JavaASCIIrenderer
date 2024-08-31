package src;

import src.ascii.ASCIIMagic;
import src.image.ImageProcessor;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        BufferedImage image = ImageProcessor.readImage("test/xdd.png");

        //testing functions
        BufferedImage scaledImage = ImageProcessor.scaleImage(image,1);
        ImageProcessor.saveImage(scaledImage,"scaled");
        BufferedImage grayImage = ImageProcessor.applyGrayscale(scaledImage);
        ImageProcessor.saveImage(grayImage,"gray");
        BufferedImage sobelledImage300 = ImageProcessor.applySobel(grayImage,20,true);
        ImageProcessor.saveImage(sobelledImage300,"sobel20");
        BufferedImage sobelledImage200 = ImageProcessor.applySobel(grayImage,200,false);
        ImageProcessor.saveImage(sobelledImage200,"sobel200");
        BufferedImage sobelledImage100 = ImageProcessor.applySobel(grayImage,80,true);
        ImageProcessor.saveImage(sobelledImage100,"sobel80");
        BufferedImage combinedImage = ImageProcessor.combineImages(grayImage,sobelledImage100);
        ImageProcessor.saveImage(combinedImage,"combined");



        float[][] luminanceMap = ASCIIMagic.getLuminance(combinedImage);
        char[][] asciiMap = ASCIIMagic.getASCIIMap(luminanceMap);

        double[][] sobelGradient = ASCIIMagic.getSobelGradient(scaledImage,120);
        char[][] asciiSobel = ASCIIMagic.getASCIISobel(sobelGradient);

        //src.ascii.ASCIIMagic.printASCIIMap(asciiMap);
        //src.ascii.ASCIIMagic.printASCIIMap(asciiSobel);
    }
}
