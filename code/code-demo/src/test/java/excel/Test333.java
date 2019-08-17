package excel;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import 图片处理.Demo111;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhaoshuxue3
 * @Date 2019/8/15 14:37
 **/
public class Test333 {

    public static void main(String[] args) {

        Random random = new Random();

        XSSFWorkbook workbook = new XSSFWorkbook();


        HashMap<String, XSSFCellStyle> colorMap = Maps.newHashMap();

//        ExecutorService executorService = Executors.newFixedThreadPool(16);
//        ExecutorService executorService = Executors.newCachedThreadPool();

//         这个速度不行，因为创建线程消耗的资源也大
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(8, 32, 30L, TimeUnit.SECONDS, queue);


        long starttime = System.nanoTime();
        System.out.println(starttime);
        for (int i = 0; i < 3000; i++) {

            final int num = i;

            executorService.execute(() -> {

                byte[] rgb = new byte[3];
                rgb[0] = (byte) random.nextInt(255);
                rgb[1] = (byte) random.nextInt(255);
                rgb[2] = (byte) random.nextInt(255);

                XSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setFillForegroundColor(new XSSFColor(rgb, new DefaultIndexedColorMap()));
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                colorMap.put(UUID.randomUUID().toString(), cellStyle);
                System.out.println(num + "      :     " + colorMap.size());
            });

        }


        executorService.shutdown();

        try {
            executorService.awaitTermination(10L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(starttime);
        long endtime = System.nanoTime();
        System.out.println(endtime);

        System.out.println(endtime - starttime);

    }

    public static void main2(String[] args) {

        Random random = new Random();

        XSSFWorkbook workbook = new XSSFWorkbook();


        HashMap<String, XSSFCellStyle> colorMap = Maps.newHashMap();

        long starttime = System.nanoTime();
        System.out.println(starttime);
        for (int i = 0; i < 3000; i++) {
            byte[] rgb = new byte[3];
            rgb[0] = (byte) random.nextInt(255);
            rgb[1] = (byte) random.nextInt(255);
            rgb[2] = (byte) random.nextInt(255);

//            Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));


            XSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(new XSSFColor(rgb, new DefaultIndexedColorMap()));
//            cellStyle.setFillForegroundColor(new XSSFColor(color));
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            colorMap.put(UUID.randomUUID().toString(), cellStyle);
            System.out.println(colorMap.size());
        }


        System.out.println(starttime);
        long endtime = System.nanoTime();
        System.out.println(endtime);

        System.out.println(endtime - starttime);

//        27秒
//              3秒

    }


}
