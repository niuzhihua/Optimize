package com.nzh.optimizeimage

import org.apache.commons.io.FileUtils
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.Project

class Utils {

    static final String PNG = ".png"
    static final String _9_PNG = ".9.png"
    static final String JPG = ".jpg"
    static final String JPEG = ".jpeg"
    static final String GIF = ".gif"
    static final String WEBP = ".webp"

    /**
     *  拷贝 (压缩png，转webp工具) 到build 目录下
     * @param name 工具名称   xxx.exe
     * @param project
     * @return path . 返回拷贝工具后的绝对路径
     */
    static String getAndCopyTool(Project project, String name) {
        if (!Os.isFamily(Os.FAMILY_WINDOWS)) {
            println("此插件目前只支持 windows系统 ")
            return
        }

        // 拷贝到此目录 : project.buildDir/tools/

        def toolPath = project.buildDir.absolutePath + File.separator + "tools" + File.separator

        def toolFile = new File(toolPath + name)

        // 存在 则判断工具是否 是可执行的
        if (toolFile.exists() && toolFile.canExecute()) {
            return toolFile.absolutePath
        }

        // 不存在 ，则拷贝工具
        if (!toolFile.exists()) {
            if (!toolFile.parentFile.exists()) {
                toolFile.parentFile.mkdirs()
            }


            // 拷贝工具
            new FileOutputStream(toolFile).withStream {
                def inputstream = Utils.class.getResource("/pc_tools/$name").openStream()
                it.write(inputstream.bytes)
                it.close()
                inputstream.close()
            }
        }
        // 存在 则判断工具是否 是可执行的
        if (toolFile.exists() && toolFile.canExecute()) {
            return toolFile.absolutePath
        }

        throw new IllegalArgumentException(" 拷贝图片处理工具 $name 失败")
    }


    /**
     * 将字符的首字母大写
     * @param s
     * @return
     */
    public static String firstUpCast(String s) {

        if (s == null || s.length() == 0) {
            return ""
        }
        return "" + Character.toUpperCase(s.charAt(0)) + s.subSequence(1, s.length())

    }


}