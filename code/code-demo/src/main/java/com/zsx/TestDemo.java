package com.zsx;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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
public class TestDemo {



    public static ArrayList<List<int[]>> getImagePixel() {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new File("/tmp/demo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minX = bi.getMinX();
        int minY = bi.getMinY();

        ArrayList<List<int[]>> list = Lists.newArrayList();
        ArrayList<int[]> subList;
        int[] rgb;
        for (int y = minY; y < height; y++) {
            subList = Lists.newArrayList();
            for (int x = minX; x < width; x++) {
                int pixel = bi.getRGB(x, y);

                rgb = new int[3];
                rgb[0] = (pixel & 0xff0000) >> 16;// r
                rgb[1] = (pixel & 0xff00) >> 8;// g
                rgb[2] = (pixel & 0xff);// b
                subList.add(rgb);
            }
            list.add(subList);
        }
        return list;
    }


    public static Map<String, XSSFCellStyle> getColorMap(List<List<int[]>> list, XSSFWorkbook workbook) {
        Map<String, int[]> rgbMap = Maps.newHashMap();
        for (List<int[]> sublist : list) {
            for (int[] rgb : sublist) {
                if (rgb[0] > 250 && rgb[1] > 250 && rgb[2] > 250) {
                    continue;
                }
                String str = rgb[0] + "," + rgb[1] + "," + rgb[2];
                rgbMap.put(str, rgb);
            }
        }

        if (rgbMap.size() > 64000) {
            System.out.println("颜色值超过了64000");
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
            cellStyle.setFillForegroundColor(new XSSFColor(rgbByte, new DefaultIndexedColorMap()));
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            colorMap.put(key, cellStyle);
        }
        return colorMap;
    }

    public static void main(String[] args) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        // 调整单元格的高度和宽度，使其尽量变成一个正方形
        sheet.setDefaultColumnWidth(1);
        sheet.setDefaultRowHeightInPoints(12);

        ArrayList<List<int[]>> list = getImagePixel();
        Map<String, XSSFCellStyle> colorMap = getColorMap(list, workbook);
        if (colorMap == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            List<int[]> subList = list.get(i);
            for (int j = 0; j < subList.size(); j++) {
                XSSFCell cell = row.createCell(j);
                int[] rgb = subList.get(j);

                if (rgb[0] > 250 && rgb[1] > 250 && rgb[2] > 250) {
                    continue;
                }
                String str = rgb[0] + "," + rgb[1] + "," + rgb[2];
                XSSFCellStyle cellStyle = colorMap.get(str);

                cell.setCellStyle(cellStyle);
            }
        }

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
