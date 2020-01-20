package com.sparkTutorial.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class exampleTest {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("in/sample.txt");

        JavaRDD<Iterable<String>> mapRdd = lines.map((Function<String, Iterable<String>>) v1 -> {
            String[] split = v1.split("\\s+");

           List<String> arrays = Arrays.asList(split);

            return arrays;
        });

        System.out.println(mapRdd.collect());
    }
}
