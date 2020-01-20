package main.java.com.examples;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class JavaHbaseDemo {
    public Configuration conf = null;

    @Before
    public void init() throws MasterNotRunningException {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "localhost:2181");

        HBaseAdmin admin;

        try {
//            admin = new HBaseAdmin(conf);
        } catch (Exception e) {

        }

    }

    @Test
    public void testScan() throws Exception {
        try {


        } catch (Exception e) {

        }

    }

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.addResource(new Path("/usr/local/Cellar/hbase/1.2.6/conf/hbase-site.xml"));
        Connection connection = ConnectionFactory.createConnection(conf);
//        conf.set("hbase.zookeeper.quorum","localhost:2181");
        try {
            Table table = connection.getTable(TableName.valueOf("testtable"));

            //get
            Get g = new Get(Bytes.toBytes("001"));
            Result r = table.get(g);
            System.out.println("Get 001:" + r);

            //put
            Put p = new Put(Bytes.toBytes("003"));
            byte[] family = Bytes.toBytes("info");
            byte[] column1 = Bytes.toBytes("age");
            byte[] column2 = Bytes.toBytes("name");

            //p.add(faimly,column,val)
            p.addColumn(family, column1, Bytes.toBytes("3"));
            table.put(p);

            p.addColumn(family, column2, Bytes.toBytes("name name 33"));
            table.put(p);

            g = new Get(Bytes.toBytes("003"));
            r = table.get(g);
            System.out.println("Get 003:" + r);

            //scan
            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);
            try {
                for (Result sr : scanner) {
                    System.out.println("scan: " + sr);
                }
            } finally {
                scanner.close();
            }

            //special scan row
            byte[] start = Bytes.toBytes("002");
            byte[] end = Bytes.toBytes("005");
            scan = new Scan().withStartRow(start).withStopRow(end);
            scanner = table.getScanner(scan);
            try {
                for (Result sr : scanner) {
                    System.out.println("scan2: " + sr);
                }
            } finally {
                scanner.close();
            }
        } catch (Exception e) {

        }
    }
}































