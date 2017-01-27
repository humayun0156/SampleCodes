package action_java_8.ch_01;

import java.io.File;
import java.io.FileFilter;

/**
 * @author humayun
 */
public class HiddenFiles {
    public static void main(String[] args) {
        File[] hiddenFiles = new File("/home/humayun").listFiles(
                new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isHidden();
                    }
                }
        );

        //Java 8
        hiddenFiles = new File("/home/humayun").listFiles(File::isHidden);

        for (File name: hiddenFiles) {
            System.out.println(name.getName());
        }
    }
}
