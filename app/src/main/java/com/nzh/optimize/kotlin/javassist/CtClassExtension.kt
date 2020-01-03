package com.nzh.optimize.kotlin.javassist

import javassist.CtClass
import javassist.CtConstructor
import javassist.CtMethod

private val clinitMap: MutableMap<Int, CtMethod> = HashMap()

private val constructorMap: MutableMap<Int, List<CtMethod>> = HashMap()

val CtClass.hash: Int
    get() {
        return System.identityHashCode(this)
    }

val CtClass.staticInitializer: CtMethod?
    get() {
        if (hash in clinitMap) {
            return clinitMap[hash]!!
        } else {
            this.declaredBehaviors.forEach {
                if (it is CtConstructor && it.name == "<clinit>") {
                    val method = CtMethod.make(it.methodInfo, it.declaringClass)
                    clinitMap[hash] = method
                    return method
                }
            }
        }
        return null
    }

val CtClass.allMethods: List<CtMethod>

    get() {
        val list: MutableList<CtMethod> = ArrayList()
        staticInitializer?.let { list.add(it) }
        list.addAll(inits)
        list.addAll(methods)
        return list
    }

val CtClass.inits: List<CtMethod>
    get() {
        return if (hash in constructorMap) {
            constructorMap[hash]!!
        } else {
            val list: MutableList<CtMethod> = ArrayList()
            this.constructors.forEach {
                list.add(CtMethod.make(it.methodInfo, this))
            }
            constructorMap[hash] = list
            list
        }
    }