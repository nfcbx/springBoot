package zipTest.gzip;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/17 11:41
 **/
public class GZipFiles {

    private final static int THREAD_COUNT = 4;
    private static ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void GZip(File file) {
        if (!file.isDirectory()) {
            GZipRunnable gZipRunnable = new GZipRunnable(file);
            service.submit(gZipRunnable);
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                GZip(f);
            }
        }
    }

    public static void shutdown() {
        service.shutdown();
    }

}
