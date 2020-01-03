package com.nzh.optimize.kotlin

// 扩展函数：跟kotlin定义方法相同，需要什么方法就定义什么方法，
// 唯一的区别就是在方法名的前面可以加上"类型."

//类型可以省略，即任意类型
//fun 要扩展的的类的类型.方法名(参数...):返回值{

//}


// 两个数相加，任意类型,任意地方 。都可以调用
fun add(a: Int, b: Int): Int {
    return a + b
}

// 在String 上扩展。只有String类型可以调用
fun String.getLastChar(str: String): String {
    return str?.substring(str.length - 2)
}
fun String.getLastChar(): String {
    return this?.substring(this.length - 2)
}