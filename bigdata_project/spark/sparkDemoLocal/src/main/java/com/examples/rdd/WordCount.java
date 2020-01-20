package com.examples.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaPairRDD lines1 =  sc.wholeTextFiles("in/word_count.text",16);
        System.out.println("lines1 = "+lines1.collect());
        lines1.filter(new Function<Tuple2, Boolean>() {
            @Override
            public Boolean call(Tuple2 v1) throws Exception {
                return  true;
            }
        });

        JavaRDD<String> lines = sc.textFile("in/word_count.text");
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        System.out.println("1* "+words.collect());
        Map<String,Long> wordCounts = words.countByValue();

        for (Map.Entry<String,Long> entry:wordCounts.entrySet()){
//            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

        JavaRDD rdd = words.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String v1) throws Exception {
                if(v1.length() > 10){
                    return true;
                }else{
                    return false;
                }
            }
        });


        System.out.println("rdd="+rdd.distinct().collect());
        Map<String,Long> rddCount = rdd.countByValue();
        for (Map.Entry<String,Long> entry:rddCount.entrySet()){
            System.out.println(entry.getKey() +" : "+entry.getValue());
        }

    }
}
