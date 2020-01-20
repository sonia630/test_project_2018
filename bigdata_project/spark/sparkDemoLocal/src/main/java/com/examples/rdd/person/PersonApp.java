package  com.examples.rdd.person;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import scala.Tuple2;

import java.util.*;

public class PersonApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("person").setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(conf);


        // 将 JavaRDD 转成 JavaPairRDD
        JavaRDD<Tuple2<String, Integer>> scoreDetails = jsc.parallelize(Arrays.asList(
                new Tuple2<>("xiao000", 90),
                new Tuple2<>("xiao111", 60),
                new Tuple2<>("xiao333", 50),
                new Tuple2<>("xiao222", 20)
        ));
        System.out.println("scoreDetails=" + scoreDetails.collect());

        JavaPairRDD<String, Integer> scoreMapRdd = JavaPairRDD.fromJavaRDD(scoreDetails);
        System.out.println("scoreMapRdd=" + scoreMapRdd.take(1).get(0));

        Map<String, Iterable<Integer>> resultMap = scoreMapRdd.groupByKey().collectAsMap();
        System.out.println("resultMap=" + resultMap.values());


        PersonBean person = new PersonBean("yongtali", "man", 20, 100);
        PersonBean person2 = new PersonBean("22", "man", 22, 120);


        person.setAge(20);
        person.setName("yongtali");
        person.setIncome(100);
        person.setSex("man");

        int ar[] = {1, 2, 7, 3, 9, 10, 33, 55, 78, 88, 9};
        PersonBean persons[] = new PersonBean[10];

        Arrays.fill(ar, 20);
        ArrayList<PersonBean> personList = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();

        // Integer.parseInt(String.valueOf(Math.random() * 20))
        for (int i = 0; i < 20; i++) {
            personList.add(new PersonBean("yongtali-" + Math.floor(Math.random() * 1000), "man-" + Math.floor(Math.random() * 1000), 20 + i, 1000));
            list1.add("yongtali-" + i);
        }

        System.out.println("personList = " + personList);
        JavaRDD<PersonBean> rdd = jsc.parallelize(personList);


        JavaRDD<String> rdd1 = jsc.parallelize(list1);
        System.out.println("** " + rdd1.collect());

//

        JavaRDD<HashMap<String, Double>> rdd2 = rdd1.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String v1) throws Exception {
                if (Integer.parseInt(v1.split("-")[1]) > 10) {
                    System.out.println("22* " + v1);
                    return true;
                } else {
                    return false;
                }
            }
        }).map(new Function<String, HashMap<String, Double>>() {
            @Override
            public HashMap<String, Double> call(String v1) throws Exception {
                HashMap<String, Double> map = new HashMap<>();
                map.put(v1, Math.floor(Math.random() * 200));
                return map;
            }
        });

        System.out.println("*=1=* " + rdd2.collect());

        System.out.println(rdd2);

        List<HashMap<String, Double>> list = rdd2.take(1);
        System.out.println("*=2=* " + list.get(0));


        HashMap<String, Double> rdd3 = rdd1.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String v1) throws Exception {
                if (Integer.parseInt(v1.split("-")[1]) > 10) {
                    return true;
                } else {
                    return false;
                }
            }
        }).map(new Function<String, HashMap<String, Double>>() {
            @Override
            public HashMap<String, Double> call(String v1) throws Exception {
                HashMap<String, Double> map = new HashMap<>();
                map.put(v1, Math.floor(Math.random() * 200));
                return map;
            }
        }).reduce(new Function2<HashMap<String, Double>, HashMap<String, Double>, HashMap<String, Double>>() {
            @Override
            public HashMap<String, Double> call(HashMap<String, Double> v1, HashMap<String, Double> v2) throws Exception {
                HashMap<String, Double> map3 = new HashMap<String, Double>();
                map3.put("task--", 80.0);
                map3.putAll(v1);
                map3.putAll(v2);

                return map3;
            }
        });


        System.out.println("*=3=* " + rdd3);


//        rdd.foreach(new VoidFunction<PersonBean>() {
//            @Override
//            public void call(PersonBean personBean) throws Exception {
////                System.out.println(personBean.getName());
//
//                System.out.println("000");
//            }
//        });

    }
}
