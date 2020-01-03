package com.nzh.optimize.kotlin
// 密封类 ： 理解为 java 中的枚举
// 密封类：用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。
// 密封类也可以有子类，但是所有子类都必须在与密封类自身相同的文件中声明。
sealed class Cat
data class Child1(var name:String):Cat()
data class Child2(var name:String,val i:Int):Cat()
data class Child3(var name:String,var list:List<String>):Cat()