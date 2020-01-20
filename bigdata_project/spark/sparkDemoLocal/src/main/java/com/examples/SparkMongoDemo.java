package com.examples;

import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.ReadConfig;
import com.mongodb.spark.config.WriteConfig;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrameNaFunctions;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;


import java.sql.Array;
import java.util.*;

import static java.util.Arrays.asList;

public class SparkMongoDemo {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .master("local[2]")
                .appName("mongo spark")
                .appName("MongoSparkConnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/test.myCollection")
                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/test.myCollection")
                .getOrCreate();

//
//       Dataset<Row> myCollection = spark.sql("select * from spark");
//        Object array =  myCollection.collect();

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        //----------------------write    create a custom writeconfig
        Map<String, String> writeOverrides = new HashMap<>();
        writeOverrides.put("collection", "spark");
        writeOverrides.put("writeConcern.w", "majority");
        WriteConfig writeConfig = WriteConfig.create(jsc).withOptions(writeOverrides);

        //create a rdd of 10 documents
        List<String> arr = (List<String>) Arrays.asList("1", "2", "3");
        JavaRDD<String> sparkDocument = (JavaRDD<String>) jsc.parallelize(arr);
        JavaRDD<Document> sparkDocument2 = (JavaRDD<Document>) sparkDocument.map(new Function<String, Document>() {
            @Override
            public Document call(String s) throws Exception {
                return Document.parse("{spark:" + s + "}");
            }
        });


        MongoSpark.save(sparkDocument2, writeConfig);


        //----------------------read
        Map<String, String> readOverrides = new HashMap<>();
        readOverrides.put("collection", "spark");
        readOverrides.put("readPreference.name", "secondaryPreferred");
        ReadConfig readConfig = ReadConfig.create(jsc).withOptions(readOverrides);



        /*Start Example: Read data from MongoDB************************/
        JavaMongoRDD<Document> myRdd = MongoSpark.load(jsc, readConfig);
        /*End Example**************************************************/

        // Analyze data from MongoDB
        System.out.println("count:" + myRdd.count());
        System.out.println("first:" + myRdd.first().toJson());
        System.out.println("collections:" + myRdd.first().toJson());

        //---------------------- aggregation
        JavaMongoRDD<Document> aggregationRdd = MongoSpark.load(jsc);
        JavaMongoRDD<Document> aggregatedRdd = aggregationRdd.withPipeline(
                Collections.singletonList(Document.parse("{$match:{test:{$gt:4}}}"))
        );

        System.out.println("aggregatedRdd.count:" + aggregatedRdd.count());
//        System.out.println(aggregatedRdd.first().toJson());



        //---------------------- aggregation
        Dataset<Row> implicitDS = MongoSpark.load(jsc).toDF();
        System.out.println("implicitDS.printSchema");
        implicitDS.printSchema();
        System.out.println("implicitDS.show");
        implicitDS.show();

        Dataset<Character> explicitDS = (Dataset<Character>) MongoSpark.load(jsc).toDS(Character.class);
        System.out.println("explicitDS.printSchema");
        explicitDS.printSchema();
        System.out.println("explicitDS.show");
        explicitDS.show();

        explicitDS.createOrReplaceTempView("characters");
        Dataset<Row> centenarians = spark.sql("select name ,age from characters where age >= 4");
        centenarians.show();

        MongoSpark.write(centenarians).option("collections","hundredClub").mode("overwrite").save();
//        MongoSpark.load(spark,ReadConfig.create(spark).withOption("coll"));

        jsc.close();

    }
}
























