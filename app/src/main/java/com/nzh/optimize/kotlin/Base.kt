package com.nzh.optimize.kotlin


// 基本语法

// val: 定义只读局部变量使用关键字 val 定义。只能为其赋值一次

val a: Int = 1  // 立即赋值
val b = 2   // 自动推断出 `Int` 类型
val c: Int = 9 // 如果没有初始值类型不能省略


// 条件表达式

fun maxOf2(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

// 等同于 maxOf2 方法
fun testIfBlock(a: Int, b: Int) = if (a > b) a else b

//  is : 相当于 java 中的 instanceof

fun testIs(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在这一分支自动转换为 `String`
        return obj.length
    }
    return null
}


// in: 检测元素是否存在于集合中, 搭配if.for 使用

fun testIn() {
    var list = listOf(2, 3, 4, 5, 5, 6)

    if (4 in list) {
        println("----in----")
    }
}

// when 关键字： 代替了switch.可以用if 代替

fun testWhen(obj: Any) {
    when (obj) {
        is String -> {
            println("-obj is String-")
        }
        is Int -> {
            println("-obj is Int-")
        }

        else -> {
            println("-when 代码块必须含有 else 出口-")
        }

    }
}

// for循环：

fun testFor() {

    // 直接使用 i 即可。跟groovy 循环中it 类似
    for (i in 0..100) {

    }
    var length = "abc".length
    for (i in 0..length) {

    }

    var list = listOf(2, 3, 4, 5, 5, 6)

    // foreach
    for (i in list) {
        println("--i=$i----")
    }
    // 根据索引遍历
    for (i in list.indices) {
        println("--i=${list[i]}----")
    }
}

