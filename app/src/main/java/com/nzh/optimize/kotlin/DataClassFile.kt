package com.nzh.optimize.kotlin

// kt 文件： File类型的kt文件，不是Class类型，
// 不是Interface类型，不是枚举类型，不是Object类型，就是File类型

// 数据类 ：文件中存放
// 格式1： data class 类名(var/val 参数名:参数类型){
//
// }
// 格式2： data class 类名(var/val 参数名:参数类型) // 参数列表至少有一个
data class User2(var name: String, var age: Int)

data class User(var name: String, var age: Int) {
    var money = 10
}

data class Customer(val name: String, val email: String)