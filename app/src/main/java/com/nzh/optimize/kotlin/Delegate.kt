package com.nzh.optimize.kotlin

// 委托 、委托属性

//  委托 ：

    interface IBase{
        fun print()
    }

    class IBaseImpl(var name:String) :IBase{
        override fun print() {
            println(name)
        }
    }

    class Delegate (iBase:IBase):IBase by iBase

    fun testDelegate() {
        val b = IBaseImpl("tom")
        Delegate(b).print()  // 输出 tom

    }