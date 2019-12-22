package poiDemo.csv;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvDemo2 {


    /**
     * 生成数据耗时：8秒
     * 耗时：5秒
     *
     * @param args
     */
    public static void main2(String[] args) {

        List<List<String>> data = createData();
        String filePath = "D://demo.csv";
        String gbk = "GBK";
        File file = new File(filePath);
        while (file.exists()) {
            file.delete();
        }

        long start = System.currentTimeMillis();

//        Path path = Paths.get("D://1.csv");
//        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) { // 这个还是有乱码
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D://1.csv")), "GBK"))) { // 用utf-8依然乱码
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // 这个还是有乱码
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), gbk))) {

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

    /**
     * 生成数据耗时：7秒
     * 耗时：28秒
     *
     * @param args
     */
    public static void main(String[] args) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("测试");
//        定义在外部，使对象持有一个引用，每当新创建一个对象时候原来引用失效jvm会自动回收
        SXSSFRow row = null;
        SXSSFCell cell = null;

        List<List<String>> data = createData();

        long start = System.currentTimeMillis();

        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i);
            List<String> list = data.get(i);
            for (int j = 0; j < list.size(); j++) {
                cell = row.createCell(j, CellType.STRING);
                cell.setCellValue(list.get(j));
            }
        }
        try (FileOutputStream out = new FileOutputStream("D:/demo.xlsx");) {
            workbook.write(out);
            workbook.dispose();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000L + "秒");
    }

    public static List<List<String>> createData() {
        long start = System.currentTimeMillis();
        int 列数 = 10;
        int 行数 = 10000 * 100;
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < 行数; i++) {
            List<String> row = createRow(i, 列数);
            list.add(row);
        }
        long end = System.currentTimeMillis();
        System.out.println("生成数据耗时：" + (end - start) / 1000L + "秒");
        return list;
    }

    private static List<String> createRow(int rowNum, int columnNum) {
        List<String> row = new ArrayList<>();
        for (int i = 1; i <= columnNum; i++) {
            StringBuilder str = new StringBuilder();
            str.append("第").append(rowNum).append("行 第").append(i).append("列");
            row.add(str.toString());
        }
        return row;
    }

}
