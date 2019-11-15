package newIODemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ZSX on 2018/11/7.
 *
 * @author ZSX
 */
public class NewIOTest1 {

    public static void main(String[] args) {

        Path path = Paths.get("");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {

        }


    }

}
