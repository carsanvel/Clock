package View;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static javax.imageio.ImageIO.read;

public class FileImageLoader {

    public static BufferedImage load(String path) {
        try {
            return read(new BufferedInputStream(new FileInputStream(new File(path))));
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
