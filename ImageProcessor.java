import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {


    public static BufferedImage processImage(String fileName, int scale){
        BufferedImage bI = readImage(fileName);
        return scaleImage(bI,scale);
    }

    public static BufferedImage readImage(String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("LOL");
        }
        return null;
    }

    public static BufferedImage scaleImage(BufferedImage bufferedImage, int scale){
        int width = bufferedImage.getWidth()/scale;
        int height = bufferedImage.getHeight()/scale;
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(bufferedImage,0,0,width,height,null);
        graphics2D.dispose();

        //save the img to test for debugging reasons
        try {
            ImageIO.write(scaledImage, "png",new File("test/scaled.png"));

        }
        catch (IOException e){
            System.out.println("some exception");
        }

        return scaledImage;
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
