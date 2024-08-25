import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageProcessor {

    public static void saveImage(BufferedImage bufferedImage, String fileName){
        String path = String.format("test/%s.png",fileName);
        try {
            ImageIO.write(bufferedImage, "png",new File(path));

        }
        catch (IOException e){
            System.out.println("some exception");
        }
    }

    public static BufferedImage readImage(String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("LOL");
        }
        return null;
    }

    public static BufferedImage combineImages(BufferedImage bufferedImage1, BufferedImage bufferedImage2){
        BufferedImage combinedImage = new BufferedImage(bufferedImage1.getWidth(),bufferedImage1.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = combinedImage.createGraphics();
        graphics2D.drawImage(bufferedImage1,0,0,null);
        graphics2D.drawImage(bufferedImage2,0,0,null);
        graphics2D.dispose();
        return combinedImage;
    }
    public static BufferedImage scaleImage(BufferedImage bufferedImage, int scale){
        int width = bufferedImage.getWidth()/scale;
        int height = bufferedImage.getHeight()/scale;
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(bufferedImage,0,0,width,height,null);
        graphics2D.dispose();

        return scaledImage;
    }

    //needs to be applied to grayscale
    public static BufferedImage applySobel(BufferedImage bufferedImage, int threshold){
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[][] sobelX = {{ -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 }};
        int[][] sobelY = {{ -1, -2, -1 }, { 0,  0,  0 }, { 1,  2,  1 }};
        BufferedImage sobelImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);

        /* you get away with getRed because image is already in grayscale before going through sobel
        this is just matrix multiplication:
        building a 3x3 matrix around the current pixel and multiplying the sobel kernel with it */
        for (int y = 1; y < height -1; y++) {
            for (int x = 1; x < width -1; x++) {
                int pixelX = 0;
                int pixelY = 0;
                int white = 0xFFFFFFFF;
                Color transparent = new Color(0.0f,0.0f,0.0f,0.0f);

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int gray = new Color(bufferedImage.getRGB(x + i, y + j)).getRed();
                        pixelX += gray * sobelX[i + 1][j + 1];
                        pixelY += gray * sobelY[i + 1][j + 1];
                    }
                }

                int magnitude = (int) Math.sqrt(pixelX * pixelX + pixelY * pixelY);
                if(magnitude > threshold){

                    sobelImage.setRGB(x,y,white);
                }
                //set other pixels transparent, so that it can be combined easily
                else{
                    sobelImage.setRGB(x,y,transparent.getRGB());
                }


            }
        }
        return sobelImage;
    }



    public static BufferedImage applyGrayscale(BufferedImage bufferedImage){
        BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                int gray = (int) (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue());
                grayImage.setRGB(x, y, new Color(gray, gray, gray).getRGB());
            }
        }
        return grayImage;
    }
}
