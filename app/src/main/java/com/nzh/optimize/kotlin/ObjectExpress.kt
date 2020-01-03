package com.nzh.optimize.kotlin

// 对象表达式 :

open class ClassA(x: Int) {
    open var name: Int = x

    fun hello(str:String){

    }
}

interface InterfaceB {
    fun say(str:String)
}



fun foo() {
    // 使用对象表达式定义一个 匿名对象
    var student = object {
        var name: String = "jerry"
        var age: Int = 20
    }



    //  使用对象表达式定义一个 实例对象
    val obj: ClassA = object : ClassA(1), InterfaceB {
        override fun say(str: String) {
            println("say() implemented")
        }

    }

    //
    var obj2 = object :ClassA(3){}

    var obj3 = object :InterfaceB{
        override fun say(str: String) {
            println("say() implemented")
        }

    }


}