




object worksheet_helloworld {

  val dataset = Seq((0, "aa"), (1, "bb"), (2, "cc"), (3, "dd")).toDF();
  val upper: String => String = _.toUpperCase

  dataset.







  //输入一个list 输出一个list
  def qSort(a: List[Int]): List[Int] =
    if (a.length < 2) a
    else
      qSort(a.filter(_ < a.head)) ++ //++ 添加add多个元素 把小的放在左边
        a.filter(_ == a.head)

  qSort(List(3, 4, 1)) //List(1, 3, 4)

  qSort(List(8, 6, 9, 3, 4, 1)) //List(1, 3, 4, 6, 8, 9)


  val p = Map(1 -> "David", 7 -> "elwood") //key -> valu
  p(1) //p(key)
  p(7)
  p.contains(7)
  p.keys
  p.values
  p + (8 -> "archer") //add element
  p - 1 //remove key element
  p ++ List(2 -> "alice", 5 -> "boble") //添加多个元素
  p -- List(2, 7) //删除多个元素


  val t1 = (1, 2) //定义二个字段的 cuple
  var t2 = 1 -> 4 //简写
  var t3 = (1, "alice", "math", 980.8)
  t3._1
  t3._3

  //返回一个tuple,t._1+1第一个是长度,  t._2+v第二个是求和,t._3+v*v第三个是平方和
  def sumSq(in: List[Int]): (Int, Int, Int) =
    in.foldLeft((0, 0, 0))((t, v) => (t._1 + 1, t._2 + v, t._3 + v * v))

  val t4 = List(1, 2, 3, 4)
  sumSq(t4)

  val e4 = 1 #:: 2 #:: 3 #:: Stream.empty
  var e5 = (1 to 10000).toStream //stream 惰性求值


  val e1 = 1 to 10
  val e2 = 1 to 10 by 2
  e1.toList

  val e3 = 1 until 10


  val d1 = List(1, 2, 3, 4)
  d1.reduceLeft((x, y) => x + y)
  d1.reduceLeft((x, y) => x * y)
  d1.reduce(_ + _)
  d1.reduce(_ * _)

  d1.foldLeft(0)(_ + _)
  d1.foldLeft(1)(_ * _)


  val c1 = List("x", "ahdb")
  c1.map(x => x.toUpperCase)
  c1.map(_.toUpperCase)
  val c2 = List(1, 2, 3, 4)
  c2.filter(_ % 2 == 1).map(_ + 10) //过滤奇数,然后加10 List(11, 13)
  val c3 = List(c2, List(7, 8, 9))
  c3.map(x => x.filter(_ % 2 == 0)) //对c2 取偶数
  c3.map(_.filter(_ % 2 == 0)) // 简写
  c3.flatMap(_.filter(_ % 2 == 0)) // flatMap把二层的list转成一层


  val a1 = List(1, 2, 3, 4)
  a1.filter(x => x % 2 == 1)
  val a2 = "88Red Boolean total".toList
  val a3 = "88  33 88 Red Boolean total".split(" ").toList
  a2.filter(x => Character.isDigit(x))
  a2.takeWhile(x => x != 'B') //takeWhile true继续取数 ,如果不是B继续


  val list1 = List(1, 2, 3, 4)
  val list2 = 0 :: list1 // :: 添加 item add to list1
  val c = "x" :: "y" :: "z" :: Nil
  val d = "z" :: Nil
  val d2 = "y" :: d
  val d3 = list1 ::: c
  d2.head
  d3.head
  d2.tail
  d2.isEmpty
  d2.length
  Nil.isEmpty

  def walkthru(l: List[Int]): String = {
    if (l.isEmpty) " "
    else l.head.toString + " " + walkthru(l.tail)
  }

  walkthru(list1)


  def sum(f: Int => Int)(a: Int)(b: Int): Int = {

    @annotation.tailrec
    def loop(n: Int, acc: Int): Int = {
      if (n > b) {
        println(s"{n},acc=${acc}")
        acc
      } else {
        println(s"n=${n},acc=${acc}")
        loop(n + 1, acc + f(n))
      }
    }

    loop(a, 0)
  }

  sum(x => x)(1)(5)

  sum(x => x * x)(1)(5)

  val sumSquare = sum(x => x * x) _
  sumSquare(1)(5)


  def factorial(n: Int, m: Int): Int =
    if (n <= 0) m
    else factorial(n - 1, m * n)

  factorial(5, 1)


  (x: Int, y: Int) => x + y


  def bar(x: Int, y: => Int) = 1

  def loop(): Int = loop

  bar(1, loop)
  // bar(loop,1) // 死循环


  def test1(x: Int, y: Int): Int = x * x

  def test2(x: => Int, y: => Int): Int = x * x

  test1(3 + 41, 8)
  test2(3 + 41, 8)


//  val result_try = try {
//    Integer.parseInt("dog")
//  } catch {
//    case _ => 0
//  } finally {
//    println("always be printed!")
//  }

  val code = 2
  val result_match = {
    code match {
      case 1 => "one"
      case 2 => "two"
      case _ => "other"
    }
  }


  println("welcome to the scala worksheet!!")
  val x = 1
  println(x)

  def hello(name: String): String = {
    s"Hello,${name}"
  }

  hello("yongtali")

  def hello2(name: String) = {
    s"hello,${name}"
  }

  hello2("yongTao liu")

  def add(x: Int, y: Int): Int = x + y

  add(9, 2)

  def add2(x: Int, y: Int): Int = x - y

  add2(9, 2)

  if (true) 1 else 2
  val a: Int = 10
  if (a == 10) a
  if (a != 10) a
  if (a != 10) "not one" else a


  def forTest() = {
    val list = List("a", "b", "c444")
    for (
      s <- list //<- 循环 list generator
    ) println(s)


    for {
      s <- list
      s1 = s.toUpperCase()
      if (s1 != "")
    } println(s1)

    for {
      s <- list
      if (s.length > 3)
    } println(s)

    for {
      s <- list
      s1 = s.toUpperCase()
      if (s1 != "")
    } yield (s1) //generate new collection
  }

  forTest()


}































