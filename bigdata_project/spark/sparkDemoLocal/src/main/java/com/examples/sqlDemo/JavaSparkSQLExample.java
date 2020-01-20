package com.examples.sqlDemo;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JavaSparkSQLExample {
    public static void main(String[] args) throws AnalysisException {
        SparkSession spark = SparkSession.builder()
                .appName("spark sql example")
                .master("local[2]")
                .config("spark.some.config.option", "some-value")
                .getOrCreate();

        Dataset<Row> df = spark.read().json("in/people.json");
        df.show();
        df.printSchema();
        df.select("name").show();
        df.groupBy("age").count().show();
        df.createOrReplaceTempView("people");
        Dataset<Row> sqlDF = spark.sql("select * from people");
        sqlDF.show();

        df.createGlobalTempView("people");

        spark.sql("select * from global_temp.people").show();
        spark.newSession().sql("select * from global_temp.people").show();

    }
}
