package com.examples.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import scala.Tuple2;

import java.util.*;


public class Ip2geoTest {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("JavaRddTest");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);

        List<Tuple2<String, HashMap<String, String>>> list = new ArrayList<Tuple2<String, HashMap<String, String>>>();

        List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "127.0.0.1 - ssss");
        map.put("type", "LGN");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("message", "255.255.1.1 - ssss");
        map2.put("type", "LGN");

        HashMap<String, Object> a = new HashMap<String, Object>();
        a.put("message", "163.0.0.1 - 9999");
        a.put("type", "LGN");

        HashMap<String, Object> b = new HashMap<String, Object>();
        b.put("message", "27.0.0.1 - 9999");
        b.put("type", "LGN");

        list3.add(map);
        list3.add(map2);
        list3.add(a);
        list3.add(b);

        JavaRDD<Map<String, Object>> rddString3 = jsc.parallelize(list3);

        JavaPairRDD<String, Map<String, Object>> pairRdd = mapToPair2(rddString3);
        System.out.println(pairRdd);


        JavaRDD<String> setJavaRDD = pairRdd.filter(new Function<Tuple2<String, Map<String, Object>>, Boolean>() {
            @Override
            public Boolean call(Tuple2<String, Map<String, Object>> v1) throws Exception {
                if(v1._2.get("type").equals("LGN")){
                    return true;
                }else{
                    return false;
                }
            }
        }).map(new Function<Tuple2<String, Map<String, Object>>,  String>() {
            @Override
            public  String call(Tuple2<String, Map<String, Object>> v1) throws Exception {
                String ip = v1._2.get("message").toString().split(" - ")[0];
                return  ip;
            }
        }).distinct();

        System.out.println("setJavaRDD="+setJavaRDD.collect());
    }


    /**
     * <p>
     *
     * @param rddString
     */
    private static  JavaPairRDD<String, Map<String, Object>> mapToPair2(JavaRDD<Map<String, Object>> rddString) {
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


        return pairRdd;

    }

}
