package com.nzh.optimize.kotlin

import java.util.ArrayList


class A {
    var name = "default"  // 普通成员
    val TYPE = "TYPE"     // 普通常量成员

    // 用init关键字 定义 初始化代码块：按照定义的顺序执行
    init {
        this.name = name + "_"
        System.out.println("--java code:init---")
        println("---kotlin code:init-------")
    }

    // 用 constructor关键字定义 构造函数
    constructor() {
        println("---kotlin 无参构造函数-------")
    }

    constructor(str: String) {
        this.name = str
        println("---kotlin 带参数构造函数 ：$str-------")
    }

    // 方法定义
    // 格式 ：fun 方法名(参数名:类型=默认值):返回值类型
    fun test(str: String = "abc", a: Int = 88): List<String> {

        return ArrayList()
    }

    // 无返回值 格式 ：fun 方法名(参数名:类型)
    fun test(str: String, a: Int, c: Int) {


    }

    fun test() {
        println("---test------")
        println(String.javaClass.name)

    }

    fun test(str: String) {
        println("str:$str")

    }

    // 数据类 测试
    fun testDataClass() {
        var user = User("jerry", 10)
        var user2 = user.copy("tome")
        println("-->user:$user")
        println("-->user2:$user2")

        var stu = DataClassTest.Stu("tom", 8)
        println("-->stu:$stu")
    }

    // 密封类 测试????
    fun testSealedClass() {


    }

    // 半生对象 ：类似 java中的static，还是有区别的。
    companion object MyCompanion {

        fun staticMethod(str: String) {
            println("---伴生对象MyCompanion：$str")
        }

        fun staticMethod() {
            println("---伴生对象MyCompanion")

        }

    }

    object SingleObj {

        var age = 7
        val gender = "male"
        fun callMethod(): Int {

            return this.age
        }

    }


}