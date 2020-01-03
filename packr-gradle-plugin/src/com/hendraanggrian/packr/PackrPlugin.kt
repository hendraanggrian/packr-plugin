package com.hendraanggrian.packr

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.registering

class PackrPlugin : Plugin<Project> {

    companion object {
        const val GROUP_NAME = "packr"
    }

    override fun apply(project: Project) {
        val ext = project.extensions.create<PackrExtension>(GROUP_NAME, project.name, project.projectDir)
        ext.outputDir = project.buildDir.resolve("releases")
        project.tasks {
            val packWindows32 by registering(PackTask::class) {
                group = GROUP_NAME
                description = "Pack JARs in Windows 32-bit executable."
                extension = ext
                platform = Platform.Windows32
            }
            val packWindows64 by registering(PackTask::class) {
                group = GROUP_NAME
                description = "Pack JARs in Windows 64-bit executable."
                extension = ext
                platform = Platform.Windows64
            }
            val packLinux32 by registering(PackTask::class) {
                group = GROUP_NAME
                description = "Pack JARs in Linux 32-bit executable."
                extension = ext
                platform = Platform.Linux32
            }
            val packLinux64 by registering(PackTask::class) {
                group = GROUP_NAME
                description = "Pack JARs in Linux 64-bit executable."
                extension = ext
                platform = Platform.Linux64
            }
            val packMacOS by registering(PackTask::class) {
                group = GROUP_NAME
                description = "Pack JARs in macOS executable."
                extension = ext
                platform = Platform.MacOS
            }
            register<PackTask>("packAll") {
                description = "Pack JARs for all supported platforms."
                group = GROUP_NAME
                dependsOn(
                    packWindows32.get(),
                    packWindows64.get(),
                    packLinux32.get(),
                    packLinux64.get(),
                    packMacOS.get()
                )
            }
        }
        project.afterEvaluate {
            if (ext.executable == null) {
                ext.executable = project.name
            }
        }
    }
}
