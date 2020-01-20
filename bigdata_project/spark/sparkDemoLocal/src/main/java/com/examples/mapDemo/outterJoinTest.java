package com.examples.mapDemo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;
import scala.Tuple3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class outterJoinTest {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("in/sample.txt");
        JavaRDD<String> lines2 = sc.textFile("in/sample2.txt");

        //cogroupRDD
        JavaRDD<Tuple2<String, Double>> score1 = sc.parallelize(Arrays.asList(
                new Tuple2<>("3", Math.floor(Math.random() * 100)),
                new Tuple2<>("3", Math.floor(Math.random() * 100)),
                new Tuple2<>("6", Math.floor(Math.random() * 100)),
                new Tuple2<>("7", Math.floor(Math.random() * 100)),
                new Tuple2<>("8", Math.floor(Math.random() * 100))
        ));

        JavaRDD<Tuple2<String, Double>> score2 = sc.parallelize(Arrays.asList(
                new Tuple2<>("3", Math.floor(Math.random() * 100)),
                new Tuple2<>("4", Math.floor(Math.random() * 100)),
                new Tuple2<>("4", Math.floor(Math.random() * 100)),
                new Tuple2<>("5", Math.floor(Math.random() * 100)),
                new Tuple2<>("9", Math.floor(Math.random() * 100))
        ));


        //javaRdd 转成 javaPairRdd
        JavaPairRDD<String, Double> scorePair1 = JavaPairRDD.fromJavaRDD(score1);
        JavaPairRDD<String, Double> scorePair2 = JavaPairRDD.fromJavaRDD(score2);

        //subtractByKey
        JavaPairRDD<String,Double> subtractRdd = scorePair1.subtractByKey(scorePair2);

        //join
        JavaPairRDD<String, Tuple2<Double, Double>> joinRdd = scorePair1.join(scorePair2);

        //fulloutjoin
        JavaPairRDD<String, Tuple2<Optional<Double>, Optional<Double>>> fullOutJoinRdd = scorePair1.fullOuterJoin(scorePair2);




        Map<String,Double> subtractMap = subtractRdd.collectAsMap();
        System.out.println("subtractMap = "+subtractRdd.collectAsMap());
        System.out.println("joinRdd = "+joinRdd.collectAsMap());
        System.out.println("fullOutJoinRdd = "+fullOutJoinRdd.collectAsMap());



    }
}





















