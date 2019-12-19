package poiDemo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class CsvDemo {

    public static void main(String[] args) throws Exception {


        FileOutputStream fileOutputStream = new FileOutputStream("D://1.csv");

        BufferedWriter outputStream = null;
        outputStream = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"), 1024);


        outputStream.write(getBOM());

        outputStream.write("name,");
        outputStream.write("age,");
        outputStream.write("pwd");

        outputStream.newLine();



        outputStream.flush();;



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
