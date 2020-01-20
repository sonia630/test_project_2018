package com.sparkTutorial.rdd;

import org.apache.avro.ipc.specific.Person;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class App {
    
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("person").setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(conf);



        try {
            List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
            JavaRDD<Integer> javaRdd = jsc.parallelize(integers, 3);


            System.out.println("javaRdd = "+ javaRdd.collect().size() +" - "+ javaRdd.collect());

            javaRdd.foreach((VoidFunction<Integer>) integer ->
            {
                System.out.println("java RDD:" + integer);
                Thread.sleep(200);
            });

            Thread.sleep(1000000);
            jsc.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
