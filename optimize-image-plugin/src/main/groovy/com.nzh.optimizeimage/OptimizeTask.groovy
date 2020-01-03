package com.nzh.optimize_image_plugin

import com.nzh.optimizeimage.Utils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction


class OptimizeTask extends DefaultTask {

//    @Input
//    File manifestFile  // manifest.xml 作为此任务的输入，用来获取 app 图标。app图标不做压缩
    @Input
    def minSdk      // 最小sdk版本 ：作为此任务的输入，用来处理转webp时 的版本问题。

    @Input
    File res  // build 目录下 要压缩或者转webp 的 目录


    def webpTool // png 转webp
    def gif2webp // gif 转webp
    def pngquant // png 压缩
    def guetzli  // jpg 压缩
    File resBak

    OptimizeTask() {
        // 给任务设置一个分组
        group = 'optimize_group'

        // 获取 图片压缩和转webp 的工具

        webpTool = Utils.getAndCopyTool(project, "cwebp.exe")
        gif2webp = Utils.getAndCopyTool(project, "gif2webp.exe")
        pngquant = Utils.getAndCopyTool(project, "pngquant.exe")
        guetzli = Utils.getAndCopyTool(project, "guetzli_windows_x86-64.exe")
        println("拷贝png->webp工具：" + webpTool)
        println("拷贝gif->webp工具：" + gif2webp)
        println("拷贝png压缩工具：" + pngquant)
        println("拷贝jpg压缩工具：" + guetzli)

        resBak = new File(project.buildDir.absolutePath + File.separator + "tools" + File.separator + "res" + File.separator)
        if (!resBak.exists()) {
            resBak.mkdirs()
        }
    }

    // 此任务执行的方法
    @TaskAction
    void myRun() {

        if (!new File(webpTool).exists()) {
            return
        }

        // 获取要转webp的图片 或 要压缩的图片。  图片从build目录下拿 的好处：
        // 1不操作工程的原图。
        // 2可以拿到其他lib 的图片，一并处理。

        println("--myRun----")
        long oldSize = 0
        long afterOptimizeSize = 0
        long count = 0
        if (res) {
            res.listFiles().each {
                if (it.name.contains("drawable") || it.name.contains("mipmap")) {
                    // 备份资源目录
                    def currentDir = createBakDir(it.name)

                    it.listFiles().each {
                        count++    // 统计图片个数
                        oldSize = oldSize + it.size() // 原图总大小

                        def name = it.name.substring(it.name.indexOf("."))
                        switch (name) {
                            case Utils.JPEG:
                            case Utils.JPG:  // jpg 图片不再比较 webp 和 压缩图大小。直接转webp.

                                def outputJpg2Webp = convert2webp(currentDir, it)
                                long s = new File(outputJpg2Webp).size()
                                afterOptimizeSize = afterOptimizeSize + s
                                break
                            case Utils.PNG:

                                def tempWebpDest = convert2webp(currentDir, it)
                                def tempPngDest = compressPng(currentDir, it)
//                                println("-------- ：$tempWebpDest")
//                                println("-------- ：$tempPngDest")
                                if (tempPngDest != null && tempWebpDest != null) {
                                    File outputPng2Webp = excludeBiger(tempWebpDest, tempPngDest)
                                    afterOptimizeSize = afterOptimizeSize + outputPng2Webp.size()
                                }
                                break
                            case Utils.GIF: // 直接转为webp

                                def outputGif2Webp = convertGif2webp(currentDir, it)
                                long size = new File(outputGif2Webp).size()
                                afterOptimizeSize = afterOptimizeSize + size
                                break
                        }
                    }

                }
            }

            println("-------------------------")
            println("共处理图片 $count 张，$oldSize 字节")
            println("处理后大小: $afterOptimizeSize 字节")

            cleanTools()

        }
    }

    def cleanTools() {
        if (webpTool) new File(webpTool).delete()
        if (gif2webp) new File(gif2webp).delete()
        if (pngquant) new File(pngquant).delete()
        if (guetzli) new File(guetzli).delete()
    }

    String createBakDir(String dirName) {

        def dir = new File(resBak.absolutePath + File.separator + dirName)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        return dir.absolutePath
    }


    File excludeBiger(String file1DestPath, String file2DestPath) {
        def f1 = new File(file1DestPath)
        def f2 = new File(file2DestPath)


        if (f1.length() > f2.length()) {
            // 删除f1
            f1.delete()
            println("删除了 ：$f1.absolutePath ")
            return f2
        } else {
            // 删除f2
            f2.delete()
            println("删除了 ：$f2.absolutePath ")
            return f1
        }
    }

    /**
     *  将png 转为webp
     * @param destDir webp图片的存放目录
     * @param file 原图片
     * @return 返回转换后的图片的绝对路径
     */
    String convert2webp(String destDir, File file) {
        String name = file.name.substring(0, file.name.lastIndexOf("."))

        def commond = "$webpTool -q 75 $file.absolutePath -o ${destDir}/${name}.webp"

        def exe2Webp = commond.execute()
        exe2Webp.waitForProcessOutput()

        if (exe2Webp.exitValue() == 0) {
            //  println("convert to webp success ：$file.absolutePath ")
            return "${destDir}/${name}.webp"
        } else {
            println("convert to webp faild ：$file.absolutePath ")
            return null
        }

    }

    /**
     * 将gif 转为webp
     * @param destDir webp图片的存放目录
     * @param file 原图片
     * @return
     */
    String convertGif2webp(String destDir, File file) {
        String name = file.name.substring(0, file.name.lastIndexOf("."))

        def commond = "$gif2webp -q 80 $file.absolutePath -o ${destDir}/${name}.webp"

        def exe2Webp = commond.execute()
        exe2Webp.waitForProcessOutput()

        if (exe2Webp.exitValue() == 0) {
            //   println("convert to webp success ：$file.absolutePath ")
            return "${destDir}/${name}.webp"
        } else {
            println("convert to webp faild ：$file.absolutePath ")
        }

    }

    /**
     *  压缩png 图片
     * @param destDir
     * @param file
     * @return 返回压缩后的图片的绝对路径
     */
    String compressPng(String destDir, File file) {
        String name = file.name.substring(0, file.name.lastIndexOf("."))

        def commond = "$pngquant --quality 85 $file.absolutePath -o ${destDir}/${name}.png"

        def compress = commond.execute()
        compress.waitForProcessOutput()

        if (compress.exitValue() == 0) {
            //  println("compress png success ：$file.absolutePath ")
            return "${destDir}/${name}.png"
        } else {
            println("compress png faild ：$file.absolutePath ")
            return null
        }

    }


}