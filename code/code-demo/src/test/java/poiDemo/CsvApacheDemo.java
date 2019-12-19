package poiDemo;

import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvApacheDemo {


    @Test
    public void createCSVFile() throws IOException {
        ArrayList<String> list = Lists.newArrayList("aaa", "bbb", "ccc", "ddd");

        FileWriter writer = new FileWriter("D://3.csv");

        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);

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
