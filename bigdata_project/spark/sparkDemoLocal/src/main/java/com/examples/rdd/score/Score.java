package com.examples.rdd.score;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

public class Score {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("person").setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        ArrayList<ScoreDetail> scoreDetails = new ArrayList<>();
        scoreDetails.add(new ScoreDetail("xiaoming", "Math", 98.0f));
        scoreDetails.add(new ScoreDetail("xiaoming", "English", 88.0f));
        scoreDetails.add(new ScoreDetail("wangwu", "Math", 75.0f));
        scoreDetails.add(new ScoreDetail("wangwu", "Englist", 78.0f));
        scoreDetails.add(new ScoreDetail("lihua", "Math", 90.0f));
        scoreDetails.add(new ScoreDetail("lihua", "English", 80.0f));
        scoreDetails.add(new ScoreDetail("zhangsan", "Math", 91.0f));
        scoreDetails.add(new ScoreDetail("zhangsan", "English", 80.0f));


        JavaRDD<ScoreDetail> rdd = jsc.parallelize(scoreDetails);

        JavaPairRDD<String, ScoreDetail> pairRDD = rdd.mapToPair(new PairFunction<ScoreDetail, String, ScoreDetail>() {
            @Override
            public Tuple2<String, ScoreDetail> call(ScoreDetail scoreDetail) throws Exception {
                Tuple2<String, ScoreDetail> tuple = new Tuple2(scoreDetail.name, scoreDetail);
                return tuple;
            }
        });

        System.out.println("pairRDD = "+rdd.take(1).get(0).score);
        System.out.println("pairRDD = "+rdd.collect());

        rdd.foreach(new VoidFunction<ScoreDetail>() {
            @Override
            public void call(ScoreDetail scoreDetail) throws Exception {
                System.out.println(scoreDetail);
            }
        });

        Function<ScoreDetail, Tuple2<Float, Integer>> createCombiner = new Function<ScoreDetail, Tuple2<Float, Integer>>() {
            @Override
            public Tuple2<Float, Integer> apply(ScoreDetail scoreDetail) {
                return new Tuple2<>(scoreDetail.score, 1);
            }
        };


        //merge value function2 传入二个值,返回一个值
        Function2<Tuple2<Float, Integer>, ScoreDetail, Tuple2<Float, Integer>> mergeValue = new Function2<Tuple2<Float, Integer>, ScoreDetail, Tuple2<Float, Integer>>() {
            @Override
            public Tuple2<Float, Integer> call(Tuple2<Float, Integer> v1, ScoreDetail v2) throws Exception {

                return new Tuple2<>(v1._1 + v2.score, v1._2 + 1);
            }
        };

        //combine by key
        Function2<Tuple2<Float, Integer>, Tuple2<Float, Integer>, Tuple2<Float, Integer>> mergeCombiners = new Function2<Tuple2<Float, Integer>, Tuple2<Float, Integer>, Tuple2<Float, Integer>>() {
            @Override
            public Tuple2<Float, Integer> call(Tuple2<Float, Integer> v1, Tuple2<Float, Integer> v2) throws Exception {
                return new Tuple2<>(v1._1 + v2._1, v1._2 + v2._2);
            }
        };


//        JavaPairRDD<String, Tuple2<Float, Integer>> combineByRdd = pairRDD.combineByKey(createCombiner, mergeValue, mergeCombiners);

        //打印平均数
//        Map<String, Tuple2<Float, Integer>> stringTuple2Map = combineByRdd.collectAsMap();

//        for ( String et:stringTuple2Map.keySet()) {
//            System.out.println(et+" "+stringTuple2Map.get(et)._1/stringTuple2Map.get(et)._2);
//        }


        System.out.println("pairRdd=");
        //  System.out.println("pairRDD="+pairRDD.take(1).get(0)._2);


    }
}


























































