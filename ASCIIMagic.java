import java.awt.image.BufferedImage;

public class ASCIIMagic {
    private static final String ASCIILegend = ".;coPBO?@■";

    public static void printASCIIMap(char[][] asciiMap){
        for (int i = 0; i < asciiMap[0].length; i++) {
            for (int j = 0; j < asciiMap.length; j++) {
                System.out.print(asciiMap[j][i]);
            }
            System.out.println();
        }
    }

    public static char[][]getASCIIMap(float[][]luminanceMap){
        int asciiIndex;
        int height = luminanceMap.length;
        int width = luminanceMap[0].length;
        char[][]asciiMap = new char[height+1][width+1];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                asciiIndex = (int)luminanceMap[i][j];
                asciiMap[i][j]= ASCIILegend.charAt(Math.min(asciiIndex,9));
            }
        }
        return asciiMap;
    }

    public static float [][] getLuminance(BufferedImage bufferedImage){
        int[][] colorMap= new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        float[][] luminanceMap= new float[bufferedImage.getWidth()][bufferedImage.getHeight()];
        int red;
        int green;
        int blue;
        for (int i = 0; i <bufferedImage.getWidth() ; i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                colorMap[i][j] = bufferedImage.getRGB(i,j);
                red = (colorMap[i][j] >>> 16) &0xFF;
                green = (colorMap[i][j] >>> 8) &0xFF;
                blue = (colorMap[i][j]) &0xFF;
                luminanceMap[i][j] = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) /255;
                luminanceMap[i][j]= (float) (Math.floor(luminanceMap[i][j]*10));
            }
        }
        return luminanceMap;
    }

}
