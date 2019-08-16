package excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoshuxue3
 * @Date 2019/8/15 12:09
 **/
public class Test1111 {

    public static void main(String[] args) throws Exception {

        OutputStream os = null;
        os = new FileOutputStream(new File("D:/1.xlsx"));
        ExcelWriter writer = EasyExcelFactory.getWriter(os);

        Sheet sheet = new Sheet(1, 1, BaseData.class);
        sheet.setSheetName("标题1");

        sheet.setHead(createTestListStringHead());

        List<BaseData> list = Lists.newArrayList();
        BaseData baseData = new BaseData();
        for (int i = 0; i < 10; i++) {
            baseData = new BaseData();
            baseData.setName("名字 " + i);
            baseData.setDept("部门-" + i);

            list.add(baseData);
        }

        writer.write(list, sheet);

//
        writer.finish();
        os.close();
    }

    public static List<List<String>> createTestListStringHead() {
        List<List<String>> head = Lists.newArrayList();

        List<String> 第一列 = Lists.newArrayList("姓名");
        List<String> 第二列 = Lists.newArrayList("部门");
        List<String> 第三列 = Lists.newArrayList("分组");

//      添加第一行表头
        head.add(第一列);
        head.add(第二列);
        head.add(第三列);

        return head;
    }
}
