package com.example.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author shimi
 */
public class TestHbase {

    static Configuration configuration= new Configuration();
    static {
        String hbaseConf = "D:\\Tongtech\\TongESB53_xinchuang\\hbase-site.xml";
        String coreConf = "D:\\Tongtech\\TongESB53_xinchuang\\core-site.xml";
        String hdfsConf = "D:\\Tongtech\\TongESB53_xinchuang\\hdfs-site.xml";
        configuration.addResource(new Path(hbaseConf));
        configuration.addResource(new Path(coreConf));
        configuration.addResource(new Path(hdfsConf));
    }

    private void testDebug() throws Exception{
        Configuration hbaseConfig = HBaseConfiguration.create();
        hbaseConfig.set("hbase.zookeeper.quorum", "168.1.18.11");
        hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181");
        Connection conn = ConnectionFactory.createConnection(hbaseConfig);
        org.apache.hadoop.hbase.client.Table table = conn.getTable(org.apache.hadoop.hbase.TableName.valueOf("user"));
        org.apache.hadoop.hbase.client.Get get = new org.apache.hadoop.hbase.client.Get("row1".getBytes());
        table.get(get);
    }

    public static void main(String[] args) throws Exception {
        Configuration hbaseConfig = HBaseConfiguration.create(configuration);
//        hbaseConfig.set("hbase.zookeeper.quorum", "168.1.100.127");
//        hbaseConfig.set("hbase.zookeeper.quorum", "168.1.18.11");
//        hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181");
        Connection conn = ConnectionFactory.createConnection(hbaseConfig);

        System.out.println(conn);
        Table table = conn.getTable(TableName.valueOf("esb_user"));
        getScanner(table, new Scan());
    }

    private static void get(Table table, Get get) throws IOException {
        Result result = table.get(get);
        System.out.println(new String(result.getRow()) + "  " + new String(result.getValue("info".getBytes(),"name".getBytes())));
    }

    private static void getScanner(Table table, Scan scan) throws IOException {
        ResultScanner resultScanner = table.getScanner(scan);
        Iterator<Result> resultIterator = resultScanner.iterator();
        while (resultIterator.hasNext()){
            Result result = resultIterator.next();
            System.out.println(new String(result.getRow()) + "  " + new String(result.getValue("info".getBytes(),"name".getBytes())));
        }
    }
}
