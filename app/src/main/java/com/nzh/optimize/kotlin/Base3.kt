package com.nzh.optimize.kotlin

//  try/catch :表达式
//  if :表达式

fun testTryCathResult() {
    val result = try {
        var a = 0
        var b = 4 / a
    } catch (e: Exception) {
        throw IllegalStateException(e)
    }

    // 使用 result
}

fun testIfResult(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
}

fun testIf(param: Int): String = if (param > 1) {
    "param > 1"
} else {
    "param <= 1 "
}

fun testWhenResult(color: String): Int {
    var result = when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
    return result
}

fun testWhen(color: String): Int = when (color) {
    "Red" -> 0
    "Green" -> 1
    "Blue" -> 2
    else -> throw IllegalArgumentException("Invalid color param value")
}