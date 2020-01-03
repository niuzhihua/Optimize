package com.nzh.optimize.kotlin

// 使用自定义注解

@MyClsAnotation(id = "abc", clazz = Hello::class)
class TestAnotation constructor(var name: String) {

    @MyFeildAnotation(id = 666)
    var money: Double = 0.0


    @MyFunAnotation(name = "abcd", age = 20, clazz = Hello::class)
    @MyFunAnotation2(name = "a") // 可以重复的注解
    @MyFunAnotation2(name = "b")
    @MyFunAnotation2(name = "c")
    fun sayHello() {
        print(" hello $name   ")
    }

    // 数组作为注解参数
    @MyFunAnotation3(arr = ["1", "2", "3"], arr2 = [1, 2, 3])
    fun hi() {
        print(" hei $name   ")
    }

    // 数组作为注解参数
    @MyFunAnotation4(arr = [Hello::class, Test::class, Delegate::class])
    fun hei() {
        print(" hei $name   ")

    }

    fun aaa(@MyParamValueAnotation value: @MyParamTypeAnotation Int) {

    }
}