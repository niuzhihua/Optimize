package com.nzh.optimizeimage

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import transform.MyTransform2

class OptimizePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        if (!project.plugins.hasPlugin(AppPlugin.class)) {
            println(" 插件只能在 android application 插件所在module使用")
            return
        }
        def android = project.extensions.getByType(AppExtension.class)

//        android.registerTransform(new MyTransform2())
//
//
//        if(true){
//            return
//        }

        project.afterEvaluate(new Action<Project>() {
            @Override
            void execute(Project p2) {

                def myTask = project.tasks.create("myOptimizerTask", com.nzh.optimize_image_plugin.OptimizeTask) {
                    minSdk = "21"
                    // manifestFile = baseVariant.outputs.first().processManifestProvider.get().aaptFriendlyManifestOutputFile

                    def resOld = project.projectDir.absolutePath + File.separator + "src" + File.separator + "main" + File.separator + "res"
                    res = new File(resOld)
                }

                // 遍历 applicationVariants
                android.applicationVariants.all(new Action<ApplicationVariant>() {
                    @Override
                    void execute(ApplicationVariant applicationVariant) {
                        println("---------------Variant---------")

                        String varintName = applicationVariant.getName()
                        varintName = Utils.firstUpCast(varintName)

                        // processDebugManifest
                        Task javacTask = project.getTasks().findByName("process" + varintName + "Manifest")
                        javacTask.dependsOn myTask
                    }
                })
            }
        })

    }
}