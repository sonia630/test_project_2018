/**
 * Copyright (C) 2015 Baifendian Corporation
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.examples.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import scala.Tuple2;

import java.util.*;

/**
 * 熟悉各种RDD的操作
 * <p>…
 *
 * @author : dsfan
 * @date : 2016年3月16日
 */
public class JavaRddTest2 {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("JavaRddTest");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> rdd = jsc.parallelize(Arrays.asList(1, 2, 3, 4, 5));
        List<String> list = new ArrayList<String>();
        list.add("a,b,c,d,e");
        list.add("1,2,3,4,5");
        list.add("12,22,32,42,52");
        list.add("a12,a22,a32,a42,a52");
        JavaRDD<String> rddString = jsc.parallelize(list);

        List<String> list2 = new ArrayList<String>();
        list2.add("1,2,3,4,5");
        list2.add("aa,bb,cc,dd,ee");
        list2.add("11,22,33,44,55");
        JavaRDD<String> rddString2 = jsc.parallelize(list2);

//        // map
//        rddMap(rdd);
//
//        // map 2
//        rddMap2(rdd);
//
//        // flatMap
//        rddFlatMap(rddString);
//
//        // filter
//        rddFilter(rdd);
//
//        // union
//        rddUnion(rddString, rddString2);

        // mapToPair
//        mapToPair(rddString);


        List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "127.0.0.1 - ssss");
        map.put("type", "LNG");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("message", "255.255.1.1 - ssss");
        map2.put("type", "LNG");

        list3.add(map);
        list3.add(map2);
        JavaRDD<Map<String, Object>> rddString3 = jsc.parallelize(list3);


        mapToPair2(rddString3);
    }

    /**
     * <p>
     *
     * @param rddString
     */
    private static void mapToPair2(JavaRDD<Map<String, Object>> rddString) {
        JavaPairRDD<String, Map<String, Object>> pairRdd = rddString.flatMapToPair(new PairFlatMapFunction<Map<String, Object>, String, Map<String, Object>>() {
            @Override
            public Iterator<Tuple2<String, Map<String, Object>>> call(Map<String, Object> s) throws Exception {
                ArrayList<Tuple2<String, Map<String, Object>>> list = new ArrayList<Tuple2<String, Map<String, Object>>>();
                list.add(new Tuple2<String, Map<String, Object>>(Long.toString(System.currentTimeMillis()), s));
                return list.iterator();
            }
        });

        System.out.println("pairRdd=" + pairRdd.collect());
        for (Tuple2 tuple2 : pairRdd.collect()) {
            System.out.print(" " + tuple2._1() + ":" + tuple2._2());
        }



    }

    /**
     * <p>
     *
     * @param rddString
     */
    private static void mapToPair(JavaRDD<String> rddString) {
        JavaPairRDD<String, String> pairRdd = rddString.flatMapToPair(new PairFlatMapFunction<String, String, String>() {

            @Override
            public Iterator<Tuple2<String, String>> call(String s) throws Exception {
                String[] temp = s.split(",");
                ArrayList<Tuple2<String, String>> list = new ArrayList<Tuple2<String, String>>();
                list.add(new Tuple2<String, String>(temp[0], temp[1]));
                return list.iterator();
            }
        });
        System.out.println("pairRdd=" + pairRdd.collect());
        for (Tuple2 tuple2 : pairRdd.collect()) {
            System.out.print(" " + tuple2._1() + ":" + tuple2._2());
        }
        System.out.println();
    }


    /**
     * <p>
     *
     * @param rddString
     * @param rddString2
     */
    private static void rddUnion(JavaRDD<String> rddString, JavaRDD<String> rddString2) {
        JavaRDD<String> unionRdd = rddString.union(rddString2);
        for (String t : unionRdd.collect()) {
            System.out.print(" " + t + " ");
        }
        System.out.println();
    }

    /**
     * <p>
     *
     * @param rdd
     */
    private static void rddFilter(JavaRDD<Integer> rdd) {
        JavaRDD<Integer> filterRdd = rdd.filter(new Function<Integer, Boolean>() {
            @Override
            public Boolean call(Integer v1) throws Exception {
                return v1 % 2 == 0;
            }
        });

        for (Integer value : filterRdd.collect()) {
            System.out.print(" " + value);
        }
        System.out.println();
    }

    /**
     * <p>
     *
     * @param rddString
     */
    private static void rddFlatMap(JavaRDD<String> rddString) {
        JavaRDD<String> flatMapRdd = rddString.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String t) throws Exception {
                return Arrays.asList(t.split(",")).iterator();
            }
        });

        for (String t : flatMapRdd.collect()) {
            System.out.print(" " + t + " ");
        }
        System.out.println();
    }

    /**
     * <p>
     *
     * @param rdd
     */
    private static void rddMap2(JavaRDD<Integer> rdd) {
        JavaRDD<Tuple2> mapRdd2 = rdd.map(new Function<Integer, Tuple2>() {
            @Override
            public Tuple2 call(Integer v1) throws Exception {
                return new Tuple2(v1, 2 * v1);
            }
        });

        for (Tuple2 tuple2 : mapRdd2.collect()) {
            System.out.print(" " + tuple2._1() + ":" + tuple2._2());
        }
        System.out.println();
    }

    /**
     * <p>
     *
     * @param rdd
     */
    private static void rddMap(JavaRDD<Integer> rdd) {
        JavaRDD<Integer> mapRdd = rdd.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1 * 2;
            }
        });

        for (Integer value : mapRdd.collect()) {
            System.out.print(" " + value);
        }
        System.out.println();
    }
}
