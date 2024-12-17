import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*  

    this code to Differentiate between images formates
    JPEG
    RAW (CR2)
    TIFF
    PNG 
    GIF
    BMP

*/

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame jF = new JFrame();
        jF.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(jF, "Select a file to recognize format");

        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(jF);

        JOptionPane.showMessageDialog(jF, "File Format is " + getImageFormat(jf.getSelectedFile().getAbsolutePath()));
        jF.dispose();
    }

    public static String getImageFormat(String path) throws IOException {
        String formatString = "Not Recognized";
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[12];
        fis.read(buffer);
        fis.close();

        if (buffer[0] == (byte) 0xFF && buffer[1] == (byte) 0xD8) {
            formatString = "JPEG";
        } else if (buffer[0] == (byte) 0x49 && buffer[1] == 0x49 && buffer[2] == 0x2A && buffer[3] == 0x00
                && buffer[4] == 0x10 && buffer[5] == 0x00 && buffer[6] == 0x00 && buffer[7] == 0x00 && buffer[8] == 0x43
                && buffer[9] == 0x52) { // CR2
            formatString = "RAW";
        } else if (buffer[0] == (byte) 0x49 && buffer[1] == (byte) 0x49 &&
                buffer[2] == (byte) 0x2A && buffer[3] == (byte) 0x00) {
            formatString = "TIFF";
        } else if (buffer[0] == 0x52 && buffer[1] == 0x41 && buffer[2] == 0x57) {
            formatString = "PNG";
        } else if (buffer[0] == 0x47 && buffer[1] == 0x49 && buffer[2] == 0x46) {
            formatString = "GIF";
        } else if (buffer[0] == 0x42 && buffer[1] == 0x4D) {
            formatString = "BMP";
        }

        return formatString;
    }

}
