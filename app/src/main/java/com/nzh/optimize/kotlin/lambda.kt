package com.nzh.optimize.kotlin


import android.view.View

// 匿名函数：
//      匿名函数看起来非常像一个常规函数声明，除了其名称省略了。

//      匿名函数 和lambda 表达式的区别：
//         1：匿名函数参数总是在括号内传递。
//         2：匿名函数中的 return 将从匿名函数自身返回。lambda可显示可隐士返回。


//lambda表达式 :
// 可以使用限定的返回语法从 lambda 显式返回一个值。 否则，将隐式返回最后一个表达式的值
// lambda表达式始终用花括号包围，并用 -> 将参数列表和函数主体分离。

// 1. 无参数的情况 ：
//      val/var 变量名 = { 操作的代码 }

// 2. 有参数的情况
//      val/var 变量名 : (参数的类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码 }

//可等价于
// 此种写法：即表达式的返回值类型会根据操作的代码自推导出来。
//      val/var 变量名 = { 参数1 ： 类型，参数2 : 类型, ... -> 操作参数的代码 }

//3. lambda表达式作为函数中的参数的时候，这里举一个例子：
//        fun test(a : Int, 参数名 : (参数1 ： 类型，参数2 : 类型, ... ) -> 表达式返回类型){
//            ...
//        }


// 隐式返回

var lambdaInt = {
    val a = 4
}
var lambdaInt2 = { a: Int ->
    a + 4

}
var lambdaString = { a: Int ->
    var str = ""
    str + a
}

// lambda 显式返回一个值,在{} 前声明一个字面量 。并用字面量返回。
var lambdaReturn = lit@{ aa: Int, ss: String ->
    val a = 4
    if (aa == 6 && ss.equals("abc")) {
        return@lit "haha"
    } else {
        return@lit a
    }
}


//    4: 常见用途：

fun testLambda(v: View) {

    // 1:   lambda和集合一起使用 。
    val languages = listOf("Java", "Kotlin", "Python", "JavaScript")
    languages.filter {
        it.contains("Java")
    }.forEach {
        println(it)
    }

//  2:  替代函数式接口实例
    v.setOnClickListener { view ->
        System.out.println("--onclick--")
    }

    v.setOnClickListener({view-> println("--onclick2--")})


//  3: 接收函数类型变量的函数
    dd {
        i:Int,ss:String->
        false
    }

}
fun dd(string:(Int,String) -> Boolean){

}







