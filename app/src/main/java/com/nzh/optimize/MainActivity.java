package com.nzh.optimize;


import android.app.Activity;
import android.os.Bundle;

import com.nzh.optimize.kotlin.A;
import com.nzh.optimize.kotlin.Test;
import com.nzh.optimize.kotlin.User;
import com.nzh.optimize.kotlin.MutableFunctionKt;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //



    }

    @Override
    protected void onResume() {
        super.onResume();
        // java 调用 kotlin 代码
        // OK ： 调用成员方法
//        A a = new A();
//        a.test("-----java call kotlin----");
        //调用 kotlin 的 普通对象
        A a2 = new A("--param---");
        a2.test();

        // 调用 kotlin 的伴生对象  : 伴生对象只能有一个
        A.MyCompanion.staticMethod("test companion obj");

        // 调用 kotlin 的单例对象
        System.out.println("---kotlin 调用 单例对象 成员：" + A.SingleObj.INSTANCE.getAge());
        System.out.println("---kotlin 单例对象：" + A.SingleObj.INSTANCE);
        System.out.println("---kotlin 调用 单例对象 方法：" + A.SingleObj.INSTANCE.callMethod());


        //javassist kt 版的语法


//        全局函数 Extension	支持
        Test test = new Test();
        test.kotlinTestExtension();

//        Data Class : 类似javabean
        // kotlin 中 使用 data class
        A a3 = new A();
        a3.testDataClass();
        // 在java 中 使用 data class
        User u = new User("a", 3);
        System.out.println(u);
        u.setMoney(12);
        System.out.println(u.getMoney());


//        Operator Overloading  ?


    }
}
