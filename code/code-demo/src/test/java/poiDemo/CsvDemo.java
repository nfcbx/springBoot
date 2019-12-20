package poiDemo;

import org.junit.Test;

import java.io.*;
import java.util.stream.IntStream;

public class CsvDemo {

    @Test
    public void test1() throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream("D://1.csv");

        BufferedWriter outputStream = null;
        outputStream = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"), 1024);


        outputStream.write(getBOM());

        outputStream.write("name,");
        outputStream.write("age,");
        outputStream.write("pwd");

        outputStream.newLine();


        outputStream.write("赵,");
        outputStream.write("29,");
        outputStream.write("pwd");

        outputStream.flush();
        ;

        outputStream.close();

    }

    @Test
    public void test2() {
        try {
            File csv = new File("D:/2.csv"); // CSV数据文件

            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // 附加
            // 添加新的数据行
            bw.write("\"姓名\"" + "," + "\"test\"" + "," + "\"2020\"");
            bw.newLine();
            bw.close();
            // 这个有乱码
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void createData(){
        IntStream.range(1, 10).forEach(i -> {
            System.out.println(i);
        });
    }


    /**
     * 功能说明：获取UTF-8编码文本文件开头的BOM签名。
     * BOM(Byte Order Mark)，是UTF编码方案里用于标识编码的标准标记。例：接收者收到以EF BB BF开头的字节流，就知道是UTF-8编码。
     *
     * @return UTF-8编码文本文件开头的BOM签名
     */
    public static String getBOM() {

        byte b[] = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        return new String(b);
    }
}
