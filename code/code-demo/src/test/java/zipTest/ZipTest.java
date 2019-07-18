package zipTest;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;

import java.io.*;
import java.util.ArrayList;
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
//        test();
//        test2();

        String rootPath = "D:/2/";
        File file = new File(rootPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            System.out.println(JSON.toJSONString(files));
            String[] list = file.list();
            System.out.println(JSON.toJSONString(list));
            for (File file1 : files) {
                System.out.println(file1.getName());
            }
        }

        String yyyyMMddHHmm = DateTime.now().toString("-yyyy-MM-dd-HH-mm");
        System.out.println(yyyyMMddHHmm);

        File file1 = new File(rootPath, "22.txt");
        System.out.println(file1.exists());


        File file2 = new File(rootPath + "33.txt");
        System.out.println(file2.exists());

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


    public static void test2() throws IOException {
        String rootPath = "D:/2/";
        // 目标
        ArrayList<String> files = Lists.newArrayList("1.txt", "2.txt", "3.txt");
        // 结果文件
        String zipFile = rootPath + "anjian.zip";

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(zipFile)));
        BufferedInputStream bis = null;
        for (String file : files) {
            ZipEntry zipEntry = new ZipEntry(file);
            zipOutputStream.putNextEntry(zipEntry);

            bis = new BufferedInputStream(new FileInputStream(new File(rootPath + file)));

            int count = 0;
            byte[] bytes = new byte[BUFFER];
            while ((count = bis.read(bytes, 0, BUFFER)) != -1) {
                zipOutputStream.write(bytes, 0, count);
            }
        }

        bis.close();
        zipOutputStream.closeEntry();

        // 冲刷输出流
        zipOutputStream.flush();
        // 关闭输出流
        zipOutputStream.close();
    }


}
