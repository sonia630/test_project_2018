package com.examples.mapDemo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;
import scala.Tuple3;

import java.util.*;

public class exampleTest {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("in/sample.txt");
        JavaRDD<String> lines2 = sc.textFile("in/sample2.txt");

        //cogroupRDD
        JavaRDD<Tuple2<String, Double>> score1 = sc.parallelize(Arrays.asList(
                new Tuple2<>("xiaoming-", Math.floor(Math.random() * 100)),
                new Tuple2<>("xiaoming-", Math.floor(Math.random() * 100)),
                new Tuple2<>("xiaoming-", Math.floor(Math.random() * 100)),
                new Tuple2<>("xixibai-", Math.floor(Math.random() * 100)),
                new Tuple2<>("lihua-", Math.floor(Math.random() * 100))
        ));

        JavaRDD<Tuple2<String, Double>> score2 = sc.parallelize(Arrays.asList(
                new Tuple2<>("lihua-", Math.floor(Math.random() * 100)),
                new Tuple2<>("lihua-", Math.floor(Math.random() * 100)),
                new Tuple2<>("xixibai-", Math.floor(Math.random() * 100)),
                new Tuple2<>("xiaoming-", Math.floor(Math.random() * 100)),
                new Tuple2<>("lihua-", Math.floor(Math.random() * 100))
        ));

        JavaRDD<Tuple2<String, Double>> score3 = sc.parallelize(Arrays.asList(
                new Tuple2<>("xixibai-" , Math.floor(Math.random() * 100)),
                new Tuple2<>("xixibai-" , Math.floor(Math.random() * 100)),
                new Tuple2<>("xixibai-" , Math.floor(Math.random() * 100)),
                new Tuple2<>("xiaoming-" , Math.floor(Math.random() * 100)),
                new Tuple2<>("lihua-" , Math.floor(Math.random() * 100))
        ));

        JavaPairRDD<String, Double> scorePair1 = JavaPairRDD.fromJavaRDD(score1);
        JavaPairRDD<String, Double> scorePair2 = JavaPairRDD.fromJavaRDD(score2);
        JavaPairRDD<String, Double> scorePair3 = JavaPairRDD.fromJavaRDD(score3);

        JavaPairRDD<String,Tuple3<Iterable<Double>,Iterable<Double>,Iterable<Double>>> cogroupRdd = scorePair1.cogroup(scorePair2,scorePair3);
        Map<String,Tuple3<Iterable<Double>,Iterable<Double>,Iterable<Double>>> cogroupMap = cogroupRdd.collectAsMap();
        System.out.println("cogroupMap="+cogroupMap);



        //reduceByKey
        JavaPairRDD<String, Integer> wordCount = lines.flatMapToPair((PairFlatMapFunction<String, String, Integer>) s -> {
            ArrayList<Tuple2<String, Integer>> list = new ArrayList<>();

            String[] split = s.split("\\s+");

            for (int i = 0; i < split.length; i++) {
                Tuple2<String, Integer> tp = new Tuple2<>(split[i], 1);
                list.add(tp);
            }
            return list.iterator();
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        //groupByKey
        Map<String, Iterable<Integer>> groupByKeyRdd = wordCount.groupByKey().collectAsMap();
        System.out.println("groupByKeyRdd=" + groupByKeyRdd);


        //word count
        System.out.println("word count=" + wordCount.collect());

        //word count map collectAsMap
        Map<String, Integer> wordCountMap = wordCount.collectAsMap();
        System.out.println("wordCountMap=" + wordCountMap);
        //wordCountMap={kks=1, ee=6, bb=2, zz=1, ff=1, cc=1, zks=2, dd=2, aa=5}

        //foldByKey
        JavaPairRDD<String, Integer> foldByKey = wordCount.foldByKey(10, (v1, v2) -> v1 + v2).sortByKey();
        System.out.println("flodByKey=" + foldByKey.collect());
        //flodByKey=[(ee,6), (aa,5), (dd,2), (zz,1), (zks,2), (ff,1), (bb,2), (cc,1), (kks,1)]
        //flodByKey=[(ee,16), (aa,15), (dd,12), (zz,11), (zks,12), (ff,11), (bb,12), (cc,11), (kks,11)]
        //flodByKey=[(aa,15), (bb,12), (cc,11), (dd,12), (ee,16), (ff,11), (kks,11), (zks,12), (zz,11)]


        //parallelize
        JavaRDD<Integer> parallelizeRdd = sc.parallelize(Arrays.asList(1, 4, 6, 8, 9, 23));
        System.out.println("parallelizeRdd" + parallelizeRdd.collect());

        //map
        JavaRDD<Iterable<String>> mapRdd = lines.map((Function<String, Iterable<String>>) v1 -> {
            String[] split = v1.split("\\s+");
            List<String> arrays = Arrays.asList(split);
            return arrays;
        });

        mapRdd.glom();

        System.out.println("mapRdd" + mapRdd.collect());


        //flatmap
        JavaRDD<String> flatRdd = lines.flatMap((FlatMapFunction<String, String>) s -> {
            String[] split = s.split("\\s+");
            System.out.println(s);
            return Arrays.asList(split).iterator();
        });

        JavaRDD<String> flatRdd2 = lines2.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                String[] split = s.split("\\s+");
                return Arrays.asList(split).iterator();
            }
        });

        //distinct
        JavaRDD<String> flatRddDistinct = flatRdd.distinct();

        System.out.println("flatRdd=" + flatRdd.collect());
        System.out.println("flatRdd distinct=" + flatRddDistinct.collect());

        //union 并集
        System.out.println("flatRdd union=" + flatRdd.union(flatRddDistinct).collect());

        //intersection 交集
        System.out.println("intersection union=" + flatRdd.intersection(flatRddDistinct).collect());

        //substract 差集
        System.out.println("substract = " + flatRdd.subtract(flatRdd2).collect());

        //cartesian  笛卡儿积
        System.out.println("cartesian=" + flatRdd.cartesian(flatRdd2).collect());

        //mapToPair
        JavaPairRDD<String, Integer> pairRDD = lines.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                Tuple2 tuple2 = new Tuple2(s.split("\\s+")[0], 1);
                return tuple2;
            }
        });
        System.out.println("pairRdd=" + pairRDD.collect());
        System.out.println("pairRdd keys=" + pairRDD.keys().collect());

        //flatMapToPair = flatMap + mapToPair
        JavaPairRDD<String, Integer> flatMapPairRdd = lines.flatMapToPair((PairFlatMapFunction<String, String, Integer>) s -> {
            ArrayList<Tuple2<String, Integer>> list = new ArrayList<>();
            String[] split = s.split("\\s+");
            for (int i = 0; i < split.length; i++) {
                Tuple2<String, Integer> tp = new Tuple2<String, Integer>(split[1], 1);
                list.add(tp);
            }

            return list.iterator();
        });
        System.out.println("flatMapPairRdd=" + flatMapPairRdd.collect());

    }
}





















