package poiDemo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class ExcelUtilTest {

    public static void main(String[] args) {


    }

    @Test
    public void createExcel() {

        SXSSFWorkbook workbook = new SXSSFWorkbook();

        SXSSFSheet sheet = workbook.createSheet("sheet1");

        int total = 10000;

        List<Map<String, String>> data = Lists.newArrayList();

        createTestData(total, data);

//        定义在外部，使对象持有一个引用，每当新创建一个对象时候原来引用失效jvm会自动回收
        SXSSFRow row = null;
        SXSSFCell cell = null;

        for (int i = 0; i < total; i++) {
            Map<String, String> map = data.get(i);

            row = sheet.createRow(i);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(map.get("a"));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(map.get("b"));
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(map.get("c"));
        }
        try (FileOutputStream out = new FileOutputStream("D:/tmp/2.xlsx");) {

            workbook.write(out);

            workbook.dispose();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }


    public void createTestData(int num, List<Map<String, String>> data) {
        HashMap<String, String> map = Maps.newHashMap();
        for (int i = 0; i < num; i++) {
            map = Maps.newHashMap();

            map.put("a", i + "");
            map.put("b", UUID.randomUUID().toString());
            map.put("c", LocalDateTime.now().toString());

            data.add(map);
        }
    }


}
