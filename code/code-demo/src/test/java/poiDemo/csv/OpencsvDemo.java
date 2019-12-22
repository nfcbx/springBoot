//package poiDemo.csv;
//
//import com.opencsv.CSVWriterBuilder;
//import com.opencsv.ICSVWriter;
//import org.junit.Test;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//<dependency>
//<groupId>com.opencsv</groupId>
//<artifactId>opencsv</artifactId>
//<version>5.0</version>
//</dependency>
//public class OpencsvDemo {
//
//
//    @Test
//    public void test1() throws FileNotFoundException, UnsupportedEncodingException {
//
//        String charsetName = "GBK";
////        charsetName = "UTF-8";
//
//        ICSVWriter writer = new CSVWriterBuilder(
//                new BufferedWriter(
//                        new OutputStreamWriter(
//                                new FileOutputStream(new File("D://11.csv")), charsetName))).build();
//        List<String[]> data = createData();
//
//        writer.writeAll(data);
//
//        try {
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    public static List<String[]> createData() {
//        int 列数 = 2;
//        int 行数 = 5;
//        List<String[]> list = new ArrayList<>();
//        for (int i = 0; i < 行数; i++) {
//            String[] row = createRow(i, 列数);
//            list.add(row);
//        }
//        return list;
//    }
//
//    private static String[] createRow(int rowNum, int columnNum) {
//        String[] array = new String[columnNum];
//        for (int i = 0; i < columnNum; i++) {
//            StringBuilder str = new StringBuilder();
//            str.append("第").append(rowNum).append("行 第").append(i).append("列").append(",").append("测试");
//            array[i] = str.toString();
//        }
//        return array;
//    }
//
//}
