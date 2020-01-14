
## 工具说明：
    将 android 项目中的图片资源(.png / .jpg /.jpeg/.gif ) 进行转化处理。
    最终到达压缩体积的目的。

               (有损)压缩      转webp        处理结果
    .png        yes          yes        取处理后的最小体积图片
    .jpg        没写         yes        取转化后的webp图片
    .gif        no           yes        取转化后的webp图片

## 效果:

####        原图大小：

        [![](https://github.com/niuzhihua/Optimize/blob/master/image/old/a.png )]

        [![](https://github.com/niuzhihua/Optimize/blob/master/image/new/a.png)]

## 使用条件：
        windows 系统  + android studio 3.5 + gradle 3.5.0

## 使用步骤：
        由于我没有上传jcenter，所有需要你下载到本地使用。
        1、将optimize-image-plugin 插件module 引入工程。
        2、在 android studio 的gradle 视图中 找到  publishPluginPublicationToMavenLocal 任务。
            工程名/optimize-image-plugin/Tasks/publishing/publishPluginPublicationToMavenLocal

            双击这个任务，将插件上传到本地仓库。
        3、 在工程的build.gradle 文件中 添加

               dependencies {
                    classpath 'com.android.tools.build:gradle:3.5.0'
                    classpath 'com.nzh.optimize:optimize:1.0' // 引入插件所在地址
                }

        4、在android application插件所在module（一般是app module ）的build.gradle文件中 使用插件：

            apply plugin: 'com.android.application'
            apply plugin: 'com.nzh.optimize'  // 使用插件

        5、运行你的工程到手机或模拟器上 即可。
            处理后的图片 放在当前 (andoid.application )module 的build 目录下：
             app/build/tools/
