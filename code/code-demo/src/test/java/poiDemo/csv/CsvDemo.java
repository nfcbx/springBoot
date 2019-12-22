package poiDemo.csv;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.*;
import java.util.List;
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
    public void 写数据() {
        long start = System.currentTimeMillis();
        List<List<String>> data = this.createData();
        String filePath = "D://1.csv";
        File file = new File(filePath);
        while (file.exists()) {
            file.delete();
        }

//        Path path = Paths.get("D://1.csv");
//        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) { // 这个还是有乱码
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D://1.csv")), "GBK"))) { // 用utf-8依然乱码
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // 这个还是有乱码
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D://1.csv", true), "UTF-8"))) {

            for (List<String> row : data) {
                StringBuilder cellStr = new StringBuilder();
                for (String cell : row) {
                    cellStr.append(cell).append(",");
                }
                writer.write(cellStr.toString());
                writer.newLine();
            }

            writer.write("结束");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000L + "秒");
    }

    @Test
    public void test22() {
        this.createData();
    }


    public List<List<String>> createData() {
        int 列数 = 10;

        int 行数 = 1000;

        List<List<String>> list = Lists.newArrayListWithCapacity(行数);

        IntStream.range(1, 行数).forEach(i -> {
            System.out.println(i);
            List<String> row = this.createRow(i, 列数);
            list.add(row);
        });

        System.out.println(list.size());
        list.forEach(obj -> {
            System.out.println(JSON.toJSONString(obj));
        });

        return list;
    }

    private List<String> createRow(int rowNum, int columnNum) {
        List<String> row = Lists.newArrayList();
        columnNum += 1;
        IntStream.range(1, columnNum).forEach(i -> {
            StringBuilder str = new StringBuilder();
            str.append("第").append(rowNum).append("行 第").append(i).append("列");
            row.add(str.toString());
        });
        return row;
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
