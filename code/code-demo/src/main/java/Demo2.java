
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo2 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        List<List<String>> data = createData();
        String filePath = "demo2.csv";
        File file = new File(filePath);
        while (file.exists()) {
            file.delete();
        }

//        Path path = Paths.get("D://1.csv");
//        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) { // 这个还是有乱码
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D://1.csv")), "GBK"))) { // 用utf-8依然乱码
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // 这个还是有乱码
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))) {
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "GBK"))) {

            writer.write(getBOM());

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

    public static List<List<String>> createData() {
        int 列数 = 2;
        int 行数 = 5;
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < 行数; i++) {
            List<String> row = createRow(i, 列数);
            list.add(row);
        }
        return list;
    }

    private static List<String> createRow(int rowNum, int columnNum) {
        List<String> row = new ArrayList<>();
        for (int i = 1; i <= columnNum; i++) {
            StringBuilder str = new StringBuilder();
            str.append("第").append(rowNum).append("行 第").append(i).append("列").append(",").append("测试");
            row.add(str.toString());
        }
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
