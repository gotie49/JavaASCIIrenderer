package src.ascii;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIIMagic {
    private static final String ASCIILegend = ".;coPBO?@■";
    private static final String ASCIIAngleLegend = "|-/\\";

    public static void printASCIIMap(char[][] asciiMap) {
        for (int i = 0; i < asciiMap[0].length; i++) {
            for (char[] chars : asciiMap) {
                System.out.print(chars[i]);
            }
            System.out.println();
        }
    }

    public static char[][] getASCIIMap(float[][] luminanceMap) {
        int asciiIndex;
        int height = luminanceMap.length;
        int width = luminanceMap[0].length;
        char[][] asciiMap = new char[height + 1][width + 1];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                asciiIndex = (int) luminanceMap[i][j];
                asciiMap[i][j] = ASCIILegend.charAt(Math.min(asciiIndex, 9));
            }
        }
        return asciiMap;
    }

    public static char[][] getASCIISobel(double[][] sobelGradient) {
        int sobelIndex;
        int height = sobelGradient.length;
        int width = sobelGradient[0].length;
        char[][] asciiSobelMap = new char[height + 1][width + 1];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sobelIndex = (int) (sobelGradient[i][j]);
                if (sobelGradient[i][j] == 0) {
                    asciiSobelMap[i][j] = ' ';
                }else if(sobelGradient[i][j] == 1){
                   asciiSobelMap[i][j] = '_';
                }
                else {
                    asciiSobelMap[i][j] = ASCIIAngleLegend.charAt(Math.min(sobelIndex, 3));
                }
            }
        }
        return asciiSobelMap;

    }

    public static float[][] getLuminance(BufferedImage bufferedImage) {
        int[][] colorMap = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        float[][] luminanceMap = new float[bufferedImage.getWidth()][bufferedImage.getHeight()];
        int red;
        int green;
        int blue;
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                colorMap[i][j] = bufferedImage.getRGB(i, j);
                red = (colorMap[i][j] >>> 16) & 0xFF;
                green = (colorMap[i][j] >>> 8) & 0xFF;
                blue = (colorMap[i][j]) & 0xFF;
                luminanceMap[i][j] = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
                luminanceMap[i][j] = (float) (Math.floor(luminanceMap[i][j] * 10));
            }
        }
        return luminanceMap;
    }

    public static double[][] getSobelGradient(BufferedImage bufferedImage, int threshold) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[][] sobelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] sobelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        double[][] sobelGradient = new double[width][height];

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int pixelX = 0;
                int pixelY = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int gray = new Color(bufferedImage.getRGB(x + i, y + j)).getRed();
                        pixelX += gray * sobelX[i + 1][j + 1];
                        pixelY += gray * sobelY[i + 1][j + 1];
                    }
                }

                int magnitude = (int) Math.sqrt(pixelX * pixelX + pixelY * pixelY);
                if (magnitude > threshold) {
                    sobelGradient[x][y] = Math.floor(((Math.atan2(pixelY, pixelX) / Math.PI) * 0.5 + 0.5) *3 );
                } else {
                    sobelGradient[x][y] = 0;
                }
            }
        }
        return sobelGradient;
    }
}
