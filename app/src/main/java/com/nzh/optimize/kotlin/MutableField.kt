package com.nzh.optimize.kotlin

// 扩展属性 : 扩展属性不能有初始化器

// 格式 1 ： val 在那个类型上扩展.属性名 : 属性类型  get(){ return 值 }
val String.lastIndex: Int
    get() {
        return this.length - 1
    }


// 格式 2 ： val 在那个类型上扩展.属性名 : 属性类型  get() =   值
val String.lastIndex2: Int get() = this.length - 1

