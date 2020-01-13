package transform

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.apache.commons.codec.digest.DigestUtils

class MyTransform2 extends Transform {

    MyTransform2() {
        super()
    }

    @Override
    String getName() {
        return "MyTransform2"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    // https://github.com/fengluoye2012/CustomGradleTest/blob/e2a57eacfda4776460b0cd377720996def54c261/customplugin/src/main/groovy/com/test/customplugin/MyClassTransform.groovy

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        println("---my transform start---")

        Collection<TransformInput> inputs = transformInvocation.getInputs()
        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider()
        boolean incremental = transformInvocation.isIncremental()
        Collection<TransformInput> referencedInputs = transformInvocation.getReferencedInputs()

        //遍历文件夹
        inputs.each { TransformInput input ->
            //遍历文件夹
            input.directoryInputs.each { DirectoryInput directoryInput ->
                //注入 代码
               // MyInjects.inject(directoryInput.file.absolutePath, mProject)

                //获取output目录
                def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)

                //将input 的目录复制到output指定目录
                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            //遍历jar 文件 对jar 不操作，但是要输出到out路径
            input.jarInputs.each { JarInput jarInput ->
                //重命名输出文件（同目录copyFile 会出现冲突）
                def jarName = jarInput.name

                String md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                def dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }

        println("---my transform end ---")


    }
}