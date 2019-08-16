package excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import 图片处理.Demo111;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhaoshuxue3
 * @Date 2019/8/15 14:37
 **/
public class Test222 {

    public static void main(String[] args) {

//        SXSSFWorkbook workbook = new SXSSFWorkbook();
        XSSFWorkbook workbook = new XSSFWorkbook();
//        HSSFWorkbook workbook = new HSSFWorkbook();


//        SXSSFSheet sheet = workbook.createSheet("sheet1");
        XSSFSheet sheet = workbook.createSheet("sheet1");
//        HSSFSheet sheet = workbook.createSheet("sheet1");

        int defaultColumnWidth = sheet.getDefaultColumnWidth();
        short defaultRowHeight = sheet.getDefaultRowHeight();
        float defaultRowHeightInPoints = sheet.getDefaultRowHeightInPoints();
        System.out.println("defaultColumnWidth :  " + defaultColumnWidth);
//        HSSFRow.Height和HeightInPoints，这两个属性的区别在于HeightInPoints的单位是点，而Height的单位是1/20个点，所以Height的值永远是HeightInPoints的20倍。
        System.out.println("defaultRowHeight :  " + defaultRowHeight);
        System.out.println("defaultRowHeightInPoints :  " + defaultRowHeightInPoints);

        sheet.setDefaultColumnWidth(2);
        sheet.setDefaultRowHeightInPoints(19);

        // TODO zsx
        createData(workbook, sheet);

//        SXSSFRow row = sheet.createRow(0);
//        for (int i = 0; i < 10; i++) {
//            SXSSFCell cell = row.createCell(i);
//            cell.setCellValue("a" + i);
//        }

        try {
            FileOutputStream out = new FileOutputStream("D:/2.xlsx");
//            FileOutputStream out = new FileOutputStream("D:/3.xls");
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createData(HSSFWorkbook workbook, HSSFSheet sheet) {
        Random random = new Random();

        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < 5; i++) {
            HSSFCell cell = row.createCell(i);

            HSSFPalette palette = workbook.getCustomPalette();
            palette.setColorAtIndex(IndexedColors.RED.getIndex(), (byte) random.nextInt(200), (byte) random.nextInt(200), (byte) random.nextInt(200));
//            HSSFColor color = palette.addColor((byte) random.nextInt(200), (byte) random.nextInt(200), (byte) random.nextInt(200));

            CellStyle cellStyle = workbook.createCellStyle();
//            cellStyle.setFillForegroundColor(color.getIndex());
            cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(cellStyle);
        }

    }

    public static void createData(XSSFWorkbook workbook, XSSFSheet sheet) {

        ArrayList<List<int[]>> list = Demo111.getImagePixel();
        int size = list.size();
        int size1 = list.get(0).size();
        int total = size * size1;

        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            List<int[]> subList = list.get(i);
            for (int j = 0; j < subList.size(); j++) {
                System.out.println("打印 " + i + ":" + j);
                XSSFCell cell = row.createCell(j);
                int[] rgb = subList.get(j);
                Color color = new Color(rgb[0], rgb[1], rgb[2]);

                XSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setFillForegroundColor(new XSSFColor(color));
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                cell.setCellStyle(cellStyle);

                int num = i * size1 + j;

                System.out.println(num);
                num *= 100;

                System.out.println("进度： " + (num / total) + " %");

            }
        }


        /**
         * Exception in thread "main" java.lang.IllegalStateException: The maximum number of Cell Styles was exceeded. You can define up to 64000 style in a .xlsx Workbook
         * 	at org.apache.poi.xssf.model.StylesTable.createCellStyle(StylesTable.java:815)
         * 	at org.apache.poi.xssf.usermodel.XSSFWorkbook.createCellStyle(XSSFWorkbook.java:730)
         * 	at excel.Test222.createData(Test222.java:107)
         * 	at excel.Test222.main(Test222.java:53)
         *
         * Process finished with exit code 1
         */

    }

    /**
     * 解析
     *
     * @param list
     */
    public static void asdf(List<List<int[]>> list) {

    }


    public static void createData2(XSSFWorkbook workbook, XSSFSheet sheet) {

        Random random = new Random();

        XSSFRow row = sheet.createRow(0);

        for (int i = 0; i < 5; i++) {
            XSSFCell cell = row.createCell(i);

            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(new XSSFColor(new Color(random.nextInt(200), random.nextInt(200), random.nextInt(200))));
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(cellStyle);
        }

    }


    public static void main2(String[] args) {

        CellStyle cellStyle = null;

        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFPalette palette = null;
        HSSFColor hssfColor = palette.addColor((byte) 0, (byte) 0, (byte) 0);
        hssfColor.getIndex();
        hssfColor.getIndex2();

    }
}
