package poiDemo.csv;

import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CsvApacheDemo {

    public static final String charsetName = "GBK";

    @Test
    public void createCSVFile() throws IOException {
        ArrayList<String> list = Lists.newArrayList("中文", "中文", "中文", "中文");

//        FileWriter writer = new FileWriter("D://3.csv");
        FileOutputStream fileOutputStream = new FileOutputStream("D://3.csv", true);
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, "UTF-8");

        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecord(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));

        list.forEach(obj -> {
            try {
                printer.printRecord(obj, obj, obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        printer.flush();
        printer.close();

        writer.close();


    }
}
