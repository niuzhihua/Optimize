package com.nzh.optimize.kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View

//类:
//  1、构造函数 ：如果主构造函数没有任何注解或者可见性修饰符，可以省略 constructor 关键字
//            如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，
//            并且这些修饰符在它前面

//  class Customer public @Inject constructor(name: String) { /*……*/ }

//  下面Person 等同于 : class Person(firstName: String) { /*……*/ }

    // 也可以这样写
//  class Person(val firstName: String, val lastName: String, var age: Int) {  }

// 2、init 初始化块： 初始化块按照它们出现在类体中的顺序执行
//         初始化块中的代码 会成为主构造函数的一部分。
//         所有 初始化块与属性初始化器 中的代码都会在 次构造函数体 之前执行。即使 没有主构造函数

// 3、次构造函数：用 constructor声明 次构造函数,必须直接或者间接调用主构造函数

// 3.1、创建类的实例：像调用普通函数一样调用构造函数 即可。
        val worker = Worker("tom")

open class Worker constructor(name: String) {  // 主构造函数

    open val CONFIG :Int = 1

    val customerKey = name.toUpperCase()
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }
    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    // 次构造函数 ：直接 调用主构造函数
    constructor(age:Int):this("abc"){

    }
    // 次构造函数 ：间接 调用主构造函数
    constructor(age:Int,money :Double):this(age){

    }

     open fun draw() {
          println("---Worker-draw---")
      }
}


// 4、继承 、覆盖方法、覆盖属性
        // 可以用一个 var 属性覆盖一个 val 属性，但反之则不行。
        // 因为一个 val 属性本质上声明了一个 get 方法， 而将其覆盖为 var
        // 只是在子类中额外声明一个 set 方法。

    class WorkerSub :Worker{
        var subName :String = ""
        constructor(subName:String) : super(subName ) {
            this.subName = subName
        }

        // 覆盖方法
        // final: 表示 禁止下一级覆盖。到此为止。
        final override fun draw(){
            super.draw()
            println("---WorkerSub-draw---")

        }


//         override val CONFIG :Int = 2  // 覆盖属性
         override var CONFIG :Int = 2   // 覆盖属性

    }

    class MyView : View {
        constructor(ctx: Context) : super(ctx)

        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
    }


    fun testExtend(){
        var worker:Worker = WorkerSub("tom")
        worker.draw()
    }

// 内部类: 标记为 inner 的嵌套类能够访问其外部类的成员。
// 内部类会带有一个对外部类的对象的引用 .

class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }

    inner class Inner {
        fun foo() = bar
    }
}

fun testClass() {
    val demo = Outer.Nested().foo() // == 2
    val demo2 = Outer().Inner().foo() // == 1
}
