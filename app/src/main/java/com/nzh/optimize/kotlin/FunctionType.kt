package com.nzh.optimize.kotlin

import android.view.View


// 1、函数类型 ：跟变量一样是一种类型。
//      用来表示一类函数，这类函数的参数列表和返回值类型 与 函数类型定义的一样。
//      定义格式： (参数列表) -> 返回类型

// 1.1、类型别名：用typealias 定义：
//      1：给现有类型起别名
typealias MyList = List<String>
typealias Object = Any
typealias FileTable<K> = MutableMap<K, MutableList<String>>
//      2：给函数类型起别名 : 参数名可以省略
typealias fun1 =   () -> Unit
typealias fun11 =  (Int,Int) -> Int
typealias fun2 = (i: Int, str: String) -> List<String>
//      3:
typealias fun30 = String.(i:Int)->String
typealias fun31 = User.(i:Int)->String
typealias fun32 = List<String>.(i:Int)->String

//  1.2 :使用函数引用要用::，不能加()，因为加()就变成调用函数了，而不是使用函数引用
var fun1Bak = :: onclick
//  1.3 : 函数类型作为 函数的形参

fun getChild(parent: View, f : (i:Int, str:String)-> View){

}

fun getChild2(parent:String,param : fun11){
    // 注意使用 参数调用
    param(3,4)
}
//  1.4 : 函数类型作为返回值

fun getMathFunc(str: String): (Int, Int)-> Int {
    // 定义一个计算加法的局部函数
    fun add(a: Int, b: Int) : Int {
        return a + b
    }
    fun max(a: Int, b: Int) :Int {
        println("---max---")
        return 888
    }
    when(str){
        "add" -> return ::add
        "max" -> return ::max
    }

    return ::add
}
//  2、函数类型实例化

//        方式1 ：使用现有的函数来实例化： funImpl 函数就是 fun2 的实例

//        方式2 ：使用lambda表达式实例化

fun getMathFunc2(type: String): (Int, Int)-> Int {
    when(type) {
        "add" -> return {a: Int, b: Int -> a + b}
        "max" -> return {a: Int, b: Int -> a - b}
        else -> return {a: Int, b: Int -> a * b}
    }
}
//  3、函数类型实例调用

fun testCallFun(){
    //方式1: 得到add 函数 的引用 ，并调用
    var result = getMathFunc("add")
    var result1 = result(2,3)
    println("--函数类型实例调用:$result1")

    //方式2: 通过别名直接调用
    var result2 = fun1Bak(2)   // 等同 onclick(2)
    println("--函数类型实例调用:$result2")

    // 方式3：
    getChild2("abc",::fun11Impl)
}

fun funImpl(i:Int,str:String) : ArrayList<String>{

    var list = ArrayList<String>()
    list.add(str)
    return list
}

fun onclick(a:Int):String{
    return "abc"
}

fun fun11Impl(i:Int ,i2:Int):Int{
    println("--call fun11Impl--")
    return  i * i2
}

fun test(){
    // 搞懂 fun30
    // 搞懂高级函数的使用

}
