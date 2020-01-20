import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

public class sparksql1 {
    public static void main(String[] args) {
//        SparkConf sparkConf = new SparkConf().setAppName("TelephonyJobTest").setMaster("local[2]").set("spark.executor.memory", "1g");
//        JavaSparkContext ctx = new JavaSparkContext(sparkConf);

        String warehouseLocation = "/Users/yongtali/Downloads/spark-warehouse";
        String resourcesPath = "/Users/yongtali/Project/test_project/bigdata_project/javaSpark/sparksqltest/src/main/resources/";
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .config("spark.sql.warehouse.dir", warehouseLocation)
//            .enableHiveSupport()
                .master("local[2]")
                .getOrCreate();
        Dataset<Row> jsonDF = spark.read()
                .json(resourcesPath + "employees.json");
//        jsonDF.show();

        jsonDF.printSchema();
//        jsonDF.select("name").show();
        jsonDF.createOrReplaceTempView("people");
        Dataset<Row> sqlDF = spark.sql("select * from people");
//        sqlDF.show();

        // read from mysql
        Dataset<Row> csvDF = spark.read()
                .option("header", "true")
                .format("csv")
//                .option("sep", ";")
//                .option("inferSchema", "true")
                .load(resourcesPath + "person.csv");
//        csvDF.show();



        Dataset<Row> mysqlJdbcDF = spark.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/hive")
                .option("dbtable", "person")
                .option("user", "hive")
                .option("password", "hive")
                .load();
        System.out.println("show mysqlJdbcDF");
//        mysqlJdbcDF.show();


        /**
         * Map<String, String> options = new HashMap<String, String>();
         * options.put("url", "jdbc:mysql://localhost:3306/video_rcmd?user=root&password=123456");
         * options.put("dbtable", "video");
         * options.put("driver", "com.mysql.jdbc.Driver"); //here
         * DataFrame mysqlJdbcDF = sqlContext.load("jdbc", options);
         */

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "hive");
        connectionProperties.put("password", "hive");
        String mysqlurl = "jdbc:mysql://localhost:3306/hive";
        Dataset<Row> mysqlJdbcDF2 = spark.read()
                .jdbc(mysqlurl, "person", connectionProperties);
        System.out.println("show mysqlJdbcDF2");
//        mysqlJdbcDF2.show();

        //write to mysql
        mysqlJdbcDF.write().format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/hive")
                .option("dbtable", "spark_person3")
                .option("user", "hive")
                .option("password", "hive")
                .mode(SaveMode.Overwrite)  //table 存在时
                .save();

        mysqlJdbcDF.createOrReplaceTempView("spark_person");

        Dataset<Row> spark_person_df = spark.sql("select * from spark_person");

//        spark_person_df.write().format("jdbc")
//                .option("createTableColumnTypes", "id int,name CHAR(64), owner VARCHAR(1024), address VARCHAR(1024), sex VARCHAR(1024), birth date")
//                .jdbc(mysqlurl,"spark_person",connectionProperties);


        //read from redis
        Dataset<Row> redisJdbcDF = spark.read()
                .format("jdbc")
                .option("url", "jdbc:redis://localhost")
                .option("redis.host", "localhost")
                .option("redis.port", "6379")
                .option("redis.auth", "")
//                .option("dbtable", "foo")
//                .option("dbtable", "moaredis")
//                .option("password", "redis@easy")
                .load();
        redisJdbcDF.show();
        
    }

    


}
