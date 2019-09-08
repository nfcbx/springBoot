package com.zsx;

import com.google.common.collect.Lists;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootHbaseApplicationTests {

    @Test
    public void contextLoads() {
        Connection conn = null;
        Admin admin = null;

        Configuration config = HBaseConfiguration.create();

        config.set("hbase.zookeeper.quorum", "127.0.0.1:2181");
//        config.set("hbase.zookeeper.port", "2181");


        try {
            conn = ConnectionFactory.createConnection(config); 
            admin = conn.getAdmin();


            ColumnFamilyDescriptor id = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("id")).build();
            ColumnFamilyDescriptor name = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("name")).build();
            ColumnFamilyDescriptor pwd = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("pwd")).build();

            ArrayList<ColumnFamilyDescriptor> columns = Lists.newArrayList(id, name, pwd);

            TableDescriptor tableDescriptor = TableDescriptorBuilder
                    .newBuilder(TableName.valueOf("table2"))
                    .setColumnFamilies(columns)
                    .build();


            admin.createTable(tableDescriptor);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
