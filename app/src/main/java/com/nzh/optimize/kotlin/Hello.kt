@file :JvmName("Hello")

package com.nzh.optimize.kotlin

import java.text.SimpleDateFormat
import java.util.ArrayList


class Hello {

    var a = 0

    var str = "test"

    var list :List<String>? = ArrayList()

    private fun funB() {

    }


    fun funA(a: String, b: Int): List<String>? {

        return null
    }
    fun funA() {

    }
    fun funA(boolean: Boolean) {

    }


    fun a3(sb: StringBuilder) {
        var value = JniHelper.jniTest("aaa")

        var local = "local variable"
        var local2 = 3;
        var list = ArrayList<String>()
        var str = String()
        var format = SimpleDateFormat("")
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum() {
        var a = 1
        var b = 2.0f
        var c = 2.000
        var d = 'a'
        var e = "abc"
        var f = false
        var h = -10000000

        var a1 = A()
        var a2: A


    }


}