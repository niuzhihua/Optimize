package com.nzh.optimize.kotlin

//  作用域函数 :在一个对象上执行一个代码块
//      共有以下五种：let、run、with、apply 以及 also
//        区别：
//              一、引用上下文对象的方式
//                  1: run、with 、 apply 通过关键字 this 引用上下文对象。
//                  2：let 及 also 将上下文对象作为 lambda 表达式参数,使用it引用上下文对象。
//              二、返回值
//                  1: apply 及 also 返回上下文对象(对象本身)。
//                  2: let、run 及 with 返回 lambda 表达式结果.


// 常见用途： 在调用链的结果上调用一个或多个函数。
fun testLet() {

    val numbers = mutableListOf("one", "two", "three", "four", "five")
    numbers.map { it.length }.filter { it > 3 }.let {
        println(it)
        // 如果需要可以调用更多函数
    }

    User("Alice", 20).let {
        println("-->原对象$it")
        it.money = 66
        it.age = 18
        println("-->使用let修改后:$it")
    }
}

// 常见用途 ：对象配置。操作改变对象属性。
fun testApply() {

    User("Alice", 20).run {
        println("-->原对象$this")
        this.money = 66
        this.age = 18
        money = 88   // 可以省略this
        println("-->使用let修改后:$this")
    }

}

// 常见用途：run 和 with 做同样的事情( 用来调用上下文对象上的函数 )，但是调用方式和 let 一样
fun testRun() {

    User("Alice", 20).run {
        println("-->原对象$this")
        this.money = 66
        this.age = 18
        money = 88   // 可以省略this
        println("-->使用let修改后:$this")
    }

}


// 常见用途： 用来调用上下文对象上的函数
fun testWith() {
    val numbers = mutableListOf("one", "two", "three")
    with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }

    var user = User("Alice", 20)
    with(user) {
        this.money = 55
        this.age = 60
        name = "tom"
    }
}

// 常见用途： 不会改变上下文对象的操作，可使用 also。
// 通常，你可以在不破坏程序逻辑的情况下从调用链中删除 also 的调用
fun testAlso() {

    val numbers = mutableListOf("one", "two", "three")
    numbers.add("four")
            .also { println("The list elements before adding new one: $it") }

    User("Alice", 20).also {
        print(it)
    }
}

// 常见用途： takeIf 表达式内 提供一个条件 来 匹配此对象。成功则返回此对象。否则返回 null。
fun testTakeIf() {
    // 将名字含有 “li” 的对象 的 age 改为 21 .
    User("Alice", 20).takeIf {
        it.name.contains("li")
    }?.let {
        // 注意 必须用 ？. 安全调用,因为可能为空。
        it.age = 21
    }.also {
        println(it)
    }
}

