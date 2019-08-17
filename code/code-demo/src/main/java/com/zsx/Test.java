package com.zsx;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoshuxue3
 * @Date 2019/8/16 15:46
 **/
public class Test {


    public static ArrayList<List<int[]>> getImagePixel() {
        int[] rgb = new int[3];
        File file = new File("D:/tmp/1.jpg");
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minX = bi.getMinX();
        int minY = bi.getMinY();

        System.out.println("width :  " + width);
        System.out.println("height :  " + height);
        System.out.println("minX :  " + minX);
        System.out.println("minY :  " + minY);


        ArrayList<List<int[]>> list = Lists.newArrayList();
        ArrayList<int[]> subList = Lists.newArrayList();
        for (int y = minY; y < height; y++) {

            subList = Lists.newArrayList();
            for (int x = minX; x < width; x++) {
                int pixel = bi.getRGB(x, y);

                rgb = new int[3];
                rgb[0] = (pixel & 0xff0000) >> 16;// r
                rgb[1] = (pixel & 0xff00) >> 8;// g
                rgb[2] = (pixel & 0xff);// b
                System.out.println("(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");

                subList.add(rgb);
            }
            System.out.println();
            list.add(subList);
        }
        return list;
    }


    public static Map<String, XSSFCellStyle> getColor3(List<List<int[]>> list, XSSFWorkbook workbook) {
        HashSet<String> set = Sets.newHashSet();
        Map<String, int[]> rgbMap = Maps.newHashMap();

        for (List<int[]> sublist : list) {
            for (int[] rgb : sublist) {
                if (rgb[0] > 250 && rgb[1] > 250 && rgb[2] > 250) {
                    continue;
                }

                String str = rgb[0] + "," + rgb[1] + "," + rgb[2];

                set.add(str);
                rgbMap.put(str, rgb);
            }
        }

        System.out.println("8899 set: " + set.size());
        int total = rgbMap.size();
        System.out.println("8899 rgbMap: " + total);

        if (set.size() == 64000) {
            System.out.println("颜色值超过了64000");
            Integer.valueOf("asdf");
            return null;
        }


        Map<String, XSSFCellStyle> colorMap = Maps.newHashMap();

        for (Map.Entry<String, int[]> entry : rgbMap.entrySet()) {
            String key = entry.getKey();
            int[] rgb = entry.getValue();
            byte[] rgbByte = new byte[3];
            rgbByte[0] = (byte) rgb[0];
            rgbByte[1] = (byte) rgb[1];
            rgbByte[2] = (byte) rgb[2];

            XSSFCellStyle cellStyle = workbook.createCellStyle();
//            Color color = new Color(rgb[0], rgb[1], rgb[2]);
            cellStyle.setFillForegroundColor(new XSSFColor(rgbByte, new DefaultIndexedColorMap()));
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            colorMap.put(key, cellStyle);
            int num = colorMap.size();
            System.out.println("colorMap.size() :   " + num + "    进度：" + ((num * 100) / total) + "%");
        }

        return colorMap;
    }


    public static Map<String, XSSFCellStyle> getColor4(List<List<int[]>> list, XSSFWorkbook workbook) {
        HashSet<String> set = Sets.newHashSet();
        for (List<int[]> sublist : list) {
            for (int[] rgb : sublist) {
                String str = rgb[0] + "," + rgb[1] + "," + rgb[2];
                set.add(str);
            }
        }

        System.out.println("8899 : " + set.size());

        if (set.size() == 64000) {
            System.out.println("颜色值超过了64000");
            Integer.valueOf("asdf");
            return null;
        }

        Map<String, XSSFCellStyle> colorMap = Maps.newHashMap();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int i = 0;
        for (List<int[]> sublist : list) {
            for (int[] rgb : sublist) {
                System.out.print(i++);
                System.out.print(" , ");
                if (i % 5000 == 0) {
                    System.out.println();
                }

                String str = rgb[0] + "," + rgb[1] + "," + rgb[2];
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        XSSFCellStyle xssfCellStyle = colorMap.get(str);
                        if (xssfCellStyle == null) {
                            XSSFCellStyle cellStyle = workbook.createCellStyle();
                            Color color = new Color(rgb[0], rgb[1], rgb[2]);
                            cellStyle.setFillForegroundColor(new XSSFColor(color));
                            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                            colorMap.put(str, cellStyle);
                        }
                    }
                });
            }
        }


        System.out.println("8899 : " + colorMap.size());

        executorService.shutdown();

        System.out.println(888);

        do {
            System.out.println("8899 : " + colorMap.size());
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (colorMap.size() < 60549);


        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(999);
        return colorMap;
    }

    public static void createData(XSSFWorkbook workbook, XSSFSheet sheet) {

        ArrayList<List<int[]>> list = getImagePixel();
        int size = list.size();
        int size1 = list.get(0).size();
        int total = size * size1;
        System.out.println("总数为： " + total);

//        Map<String, XSSFCellStyle> colorMap = getColor(list, workbook);
//        ConcurrentMap<String, XSSFCellStyle> ConcurrentMap = getColor2(list, workbook);
        Map<String, XSSFCellStyle> colorMap = getColor3(list, workbook);

        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            List<int[]> subList = list.get(i);
            for (int j = 0; j < subList.size(); j++) {
                System.out.print("打印 " + i + ":" + j);
                System.out.print("               ");
                XSSFCell cell = row.createCell(j);
                int[] rgb = subList.get(j);

                if (rgb[0] > 250 && rgb[1] > 250 && rgb[2] > 250) {
                    continue;
                }
                String str = rgb[0] + "," + rgb[1] + "," + rgb[2];

//                Color color = new Color(rgb[0], rgb[1], rgb[2]);

//                XSSFCellStyle cellStyle = workbook.createCellStyle();
//                cellStyle.setFillForegroundColor(new XSSFColor(color));
//                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                XSSFCellStyle cellStyle = colorMap.get(str);
//                XSSFCellStyle cellStyle = ConcurrentMap.get(str);

                cell.setCellStyle(cellStyle);

                int num = i * size1 + j;

//                System.out.println(num);
                num *= 100;

                System.out.println("进度： " + (num / total) + " %");

            }
        }

    }

    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();


        XSSFSheet sheet = workbook.createSheet("sheet1");

        int defaultColumnWidth = sheet.getDefaultColumnWidth();
        short defaultRowHeight = sheet.getDefaultRowHeight();
        float defaultRowHeightInPoints = sheet.getDefaultRowHeightInPoints();
        System.out.println("defaultColumnWidth :  " + defaultColumnWidth);
//        HSSFRow.Height和HeightInPoints，这两个属性的区别在于HeightInPoints的单位是点，而Height的单位是1/20个点，所以Height的值永远是HeightInPoints的20倍。
        System.out.println("defaultRowHeight :  " + defaultRowHeight);
        System.out.println("defaultRowHeightInPoints :  " + defaultRowHeightInPoints);

//        sheet.setDefaultColumnWidth(2);
//        sheet.setDefaultRowHeightInPoints(19);

        sheet.setDefaultColumnWidth(1);
        sheet.setDefaultRowHeightInPoints(12);

        // TODO zsx
        createData(workbook, sheet);

        try {
            FileOutputStream out = new FileOutputStream("D:/2222.xlsx");
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
