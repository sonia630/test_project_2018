import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcount")
    val sc = new SparkContext(conf)

    val input = sc.textFile("/Users/yongtali/Downloads/1.txt")
    val lines = input.flatMap( line => line.split(" ")) // flat map
    val  count = lines.map(word => (word,1))//  key value
    val output = count.saveAsTextFile("/Users/yongtali/Downloads/1resp")
  }
}
