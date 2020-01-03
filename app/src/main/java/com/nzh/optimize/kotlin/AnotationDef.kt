package com.nzh.optimize.kotlin

import kotlin.reflect.KClass

// 自定义注解


// 带参数的方法注解
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class MyFunAnotation(val name: String, val age: Int, val clazz: KClass<*>)

//  方法注解
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
@Repeatable     //此配置表示 可以重复使用此注解
annotation class MyFunAnotation2(val name: String)

//  方法注解 ： 数组作为注解参数
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class MyFunAnotation3(val arr: Array<String>,val arr2 : IntArray)
// 方法注解 ： 数组作为注解参数
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class MyFunAnotation4(val arr: Array<KClass<*>>)

//  属性注解
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
annotation class MyFeildAnotation(val id: Int)

// 类
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class MyClsAnotation(val id: String, val clazz: KClass<*>)

// 方法参数类型注解
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.TYPE)
annotation class MyParamTypeAnotation

// 方法参数值 注解
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class MyParamValueAnotation