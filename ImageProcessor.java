import java.awt.image.BufferedImage;

public class ImageProcessor {

    private String fileName = "";
    private String fileType = "";
    public ImageProcessor(String fileName, String fileType){
        this.fileName = fileName;
        this.fileType = fileType;
    }
//TODO: read Image and extract Luminance 2DArray (-quantized values in steps of 0.1 (Range 0 to 1), -downscaled version of image (Factor = 8))
    public BufferedImage readImage(String fileName){
        BufferedImage bufferedImage = new BufferedImage(0,0,0);
        return bufferedImage;
    }

}
