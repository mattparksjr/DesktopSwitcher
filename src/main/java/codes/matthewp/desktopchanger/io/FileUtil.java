package codes.matthewp.desktopchanger.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {


    /**
     * Utility to copy one file to another
     * @param src File to copy from
     * @param dest File to be written to
     */
    public static void copyFile(File src, File dest) throws IOException {
        FileInputStream ins = null;
        FileOutputStream outs = null;
        try {
            ins = new FileInputStream(src);
            outs = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, length);
            }
            ins.close();
            outs.close();
        } catch (IOException ex) {
            throw new IOException(ex.getCause());
        }
    }

}
