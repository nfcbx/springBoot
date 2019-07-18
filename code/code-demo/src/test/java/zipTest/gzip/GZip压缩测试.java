package zipTest.gzip;

import java.io.File;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/17 11:37
 **/
public class GZip压缩测试 {

    public static void main(String[] args) {
        File file = new File("D:\\3");
        GZipFiles.GZip(file);
        GZipFiles.shutdown();
    }


}
