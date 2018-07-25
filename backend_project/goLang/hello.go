package main
import "fmt"
import "unsafe"

 

type Books struct{
	title string
	author string
	subject string
	book_id int
}

func main() {
	var book1 Books
	// var book2 Books

	book1.title = "go lang"
	book1.author = "www.runoob.com"
	book1.subject = "Go subject"
	book1.book_id = 123

	fmt.Println(book1)



	//iota，特殊常量，可以认为是一个可以被编译器修改的常量。
	//在每一个const关键字出现时，被重置为0，然后再下一个const出现之前，每出现一次iota，其所代表的数字会自动增加1。
	//iota 可以被用作枚举值：
	const(
		c19 = 1 << iota
		c20 = 3 << iota
		c21
		c22
		)
	fmt.Println(c19,c20,c21,c22)

	const(
		c7 = iota  //0
		c8 = iota  //1
		c9 = iota  //2
		)
	fmt.Println(c7,c8,c9)

	const(
		c10 = iota  //0
		c11 		//1
		c12			//2
		c13 = "ha"  //独立值,iota += 1
		c14         // ha
		c15 = 100   //iota += 1
		c16			//100 iota += 1
		c17 = iota  //7,恢复计数
		c18			//8
		)
	fmt.Println(c10,c11,c12,c13,c14,c15,c16,c17,c18)

	//len unsafe.Sizeof
	const(
		c4 = "abc"
		c5 = len(c4)
		c6 = unsafe.Sizeof(c4)
		)
	fmt.Println(c4,c5,c6)

	fmt.Println("Hello, World!")
	 
	var b bool = true;
	 
	fmt.Println(b)

	var v_name int
	v_name = 10
	fmt.Println(v_name)

	var v_name1 int = 30
	fmt.Println(v_name1)

	var a1 = 10
	fmt.Println(a1)

	a2 := 20
	fmt.Println(a2)

	var a3 string = "run go"
	fmt.Println(a3)

	const length int = 10
	const width int =5
	var area int
	const c1,c2,c3 =1 ,false,"str"
	area = length*width
	fmt.Println("area:%d",area)

	const(
		unknow = 0
		female = 1
		male = 2
	)
	fmt.Println(unknow,female,male)


}