package 图片处理;

import com.google.common.collect.Lists;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoshuxue3
 * @Date 2019/8/15 11:45
 **/
public class Demo111 {

    public static void main(String[] args) {
        getImagePixel();
    }


    public static ArrayList<List<int[]>> getImagePixel() {
        int[] rgb = new int[3];
//        File file = new File("D:/tmp/155376460130.jpg");
        File file = new File("D:/tmp/1.jpg");
//        File file = new File("D:/tmp/22.png");
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
                System.out.print("(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
                System.out.print("  对应  ");
                System.out.println(ColorUtil.toHex(rgb[0], rgb[1], rgb[2]));

                subList.add(rgb);
            }
            System.out.println();
            list.add(subList);
        }
        return list;
    }

    public static void getImagePixel2() {
        int[] rgb = new int[3];
        File file = new File("D:/tmp/155376460130.jpg");
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


        for (int y = minY; y < height; y++) {
            for (int x = minX; x < width; x++) {
                int pixel = bi.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;// r
                rgb[1] = (pixel & 0xff00) >> 8;// g
                rgb[2] = (pixel & 0xff);// b
                System.out.print("(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
                System.out.print("  对应  ");
                System.out.println(ColorUtil.toHex(rgb[0], rgb[1], rgb[2]));
            }
            System.out.println();
        }

    }

}
