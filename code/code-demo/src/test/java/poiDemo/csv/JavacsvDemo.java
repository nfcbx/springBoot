package poiDemo.csv;

import com.csvreader.CsvWriter;
import io.netty.util.ConstantPool;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class JavacsvDemo {

    @Test
    public void test1() throws IOException {

        CsvWriter writer = new CsvWriter("D://22.csv", ',', Charset.forName("gbk"));

        List<List<String>> data = createData();

        for (List<String> row : data) {
            String str = StringUtils.join(row, ",");
            writer.write(str);
            writer.write("\n"); // 怎么换行
        }
        writer.flush();
        writer.close();

    }

    public static List<List<String>> createData() {
        int 列数 = 2;
        int 行数 = 5;
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < 行数; i++) {
            List<String> row = createRow(i, 列数);
            list.add(row);
        }
        return list;
    }

    private static List<String> createRow(int rowNum, int columnNum) {
        List<String> row = new ArrayList<>();
        for (int i = 1; i <= columnNum; i++) {
            StringBuilder str = new StringBuilder();
            str.append("第").append(rowNum).append("行 第").append(i).append("列");
            row.add(str.toString());
        }
        return row;
    }

}
