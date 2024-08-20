import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    private String fileName = "xdd.png";
    private String fileType = "png";
    public ImageProcessor(String fileName, String fileType){
        this.fileName = fileName;
        this.fileType = fileType;
    }
//TODO: -downscaled version of image (Factor = x)
    public BufferedImage readImage(String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("LOL");
        }
        return null;
    }

    public float [][] getLuminance(BufferedImage bufferedImage){
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
