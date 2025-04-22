import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImgService {

    public static JLabel loadImage(String filePath) {
        try {
            // Assuming the image is in the resources folder within the classpath
            // Add leading / to make it an absolute path from the classpath root
            InputStream inputStream = ImgService.class.getResourceAsStream("/" + filePath);

            // Check if the input stream is null (file not found)
            if (inputStream == null) {
                System.out.println("Resource not found at: " + ImgService.class.getResource("/" + filePath));
                throw new IllegalArgumentException("File not found: " + filePath);
            }

            // Read the image from the input stream
            BufferedImage image = ImageIO.read(inputStream);

            // Check if the image is loaded successfully
            if (image == null) {
                System.out.println("Image format not supported or file corrupted: " + filePath);
                return null;
            }

            // Return a JLabel with the image icon
            return new JLabel(new ImageIcon(image));
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return null;
        }
    }


    // Method to update the icon of an existing JLabel
    public static void updateImage(JLabel imageContainer, String filePath) {
        try {
            // Add leading / to make it an absolute path from the classpath root
            InputStream inputStream = ImgService.class.getResourceAsStream("/" + filePath);
            if (inputStream == null) {
                System.out.println("Resource not found at: " + ImgService.class.getResource("/" + filePath));
                throw new IllegalArgumentException("File not found: " + filePath);
            }

            BufferedImage image = ImageIO.read(inputStream);

            // Check if the image is null (could happen if the format isn't supported)
            if (image == null) {
                throw new IllegalArgumentException("Unable to read image: " + filePath);
            }

            imageContainer.setIcon(new ImageIcon(image));
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating image: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException occurred while reading image: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
