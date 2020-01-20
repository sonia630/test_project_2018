package  org.apache.spark.examples;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;


public class LocalFileRddTest {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("JavaRddTest");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);


        // read list to RDD
        List<String> data = Arrays.asList("Learn","Apache","Spark","with","Tutorial Kart");
        JavaRDD<String> items = jsc.parallelize(data,1);
        items.foreach(item ->{
            System.out.println("0*"+item);
        });



        JavaRDD<String> lines = jsc.textFile("/Users/yongtali/Downloads/1.txt");

        lines.foreach(item-> {
            System.out.println("1*"+item);
        });


        for(String line : lines.collect()){
            System.out.println("2* "+line);
        }

        JavaPairRDD<String,String> lines1 = jsc.wholeTextFiles("/Users/yongtali/Downloads/1.txt");
        for(Tuple2 line: lines1.collect()){
            System.out.println("3* "+line);
//            System.out.printf("$$$ "+ JSON.parseObject((String) line._2));
        }

    }


}
