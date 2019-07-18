package zipTest.gzip;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/17 11:38
 **/
public class GZipRunnable implements Runnable {

    private final File file;

    public GZipRunnable(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        if (!file.getName().endsWith(".gz")) {
            File outputFile = new File(this.file.getParent(), this.file.getName() + ".gz");
            if (!outputFile.exists()) {
                CountTime countTime = new CountTime(file);
                Thread thread = new Thread(countTime);
                thread.start();
                try {
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                    BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(outputFile)));

                    int b;
                    while ((b = in.read()) != -1) {
                        out.write(b);
                    }
                    out.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(outputFile + "文件已经存在，无法压缩");
            }
        }
    }
}
