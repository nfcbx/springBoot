package zipTest.gzip;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/17 11:41
 **/
public class CountTime implements Runnable {

    private final File file;

    public CountTime(File file) {
        super();
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println("正在压缩" + file.getAbsolutePath());
        try {
            while (true) {
                System.out.println(".");
                TimeUnit.SECONDS.sleep(1L);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(file.getAbsolutePath() + ".gz已经压缩完成");
        }
    }
}
