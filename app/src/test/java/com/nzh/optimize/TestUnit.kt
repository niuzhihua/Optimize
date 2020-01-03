package com.nzh.optimize

import com.nzh.optimize.kotlin.javassist.allMethods
import javassist.*
import javassist.tools.reflect.Compiler
import org.junit.Test
import java.lang.Exception

class KtCodeTest {

    var ktCodePath = "D:/android_studio_workspace/bicai/Optimize/app/build/tmp/kotlin-classes/debug"
    //    var ktCodePath = "D:\\android_studio_workspace\\bicai\\Optimize\\app\\build\\intermediates\\javac\\debug\\classes"

    @Test
    fun testKtcode() {

        var classPool = ClassPool() // 需要添加android.jar
        classPool.appendClassPath(ktCodePath) // 添加业务类

        var ctClassHello = classPool.get("com.nzh.optimize.kotlin.Hello")
        try {
            // 添加 属性字段
            var newField = CtField(CtClass.intType, "tom", ctClassHello)
            ctClassHello.addField(newField)

            // 添加方法
            var newMethod = CtNewMethod.make("public void newMethod(){}", ctClassHello)
            ctClassHello.addMethod(newMethod)

            // 修改方法
            //  val ctMethod = ctClass!!.allMethods.find { it.name == methodName }
            var ctMethod_funA = ctClassHello.getDeclaredMethod("funA")

            // 查找方法
            val findMethod =  ctClassHello.allMethods.find { it.signature.contains("(Ljava/lang/String;I)") }
            println("---->findMethod: ${findMethod!!.name} ")

            // 查找属性 :根据名称找类型
            var ctField = ctClassHello.declaredFields.find { it.name.equals("list") }
            println("类型:${ctField!!.signature}")


            var out = "D:\\android_studio_workspace\\bicai\\Optimize\\app\\build\\tools"
            ctClassHello.debugWriteFile(out)
            ctClassHello.defrost()

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}