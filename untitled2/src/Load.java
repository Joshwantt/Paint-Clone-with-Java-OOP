import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

/**
 * Class responsible for opening the JFileChooser browsing menu
 */
public class Load {


    /**
     * Opens file browser, collects absolute directory
     * @return absolute directory of chosen file.
     */
    public String loadFile() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        else{ throw new IllegalArgumentException("Select and appropriate file.");}
    }
}



