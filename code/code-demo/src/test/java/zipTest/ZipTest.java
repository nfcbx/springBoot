package zipTest;

import java.io.*;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhaoshuxue3
 * @Date 2019/4/16 19:02
 **/
public class ZipTest {

    // 缓冲区大小
    private static final int BUFFER = 2048;
    // 字符集
    private static final String CHAR_SET = "GBK";

    public static void main(String[] args) throws Exception {
        test();
        ;
    }

    public static void test() throws IOException {

        String rootPath = new File("").getAbsolutePath();
        String zipFile = rootPath + "/src/test/java/zipTest/" + "a.zip";
        String file1 = rootPath + "/src/test/java/zipTest/" + "1.txt";
        String file2 = rootPath + "/src/test/java/zipTest/" + "2.txt";

        System.out.println(new File("").getAbsolutePath());

        FileOutputStream fos = new FileOutputStream(new File(zipFile));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fos);

        ZipEntry zipEntry = new ZipEntry(file1);
        zipOutputStream.putNextEntry(zipEntry);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(file1)));

        int count = 0;
        byte[] bytes = new byte[BUFFER];
        while ((count = bis.read(bytes, 0, BUFFER)) != -1) {
            zipOutputStream.write(bytes, 0, count);
        }

        bis.close();
        zipOutputStream.closeEntry();

        // 冲刷输出流
        zipOutputStream.flush();
        // 关闭输出流
        zipOutputStream.close();
    }


    public static void compressFile() {


    }


}
