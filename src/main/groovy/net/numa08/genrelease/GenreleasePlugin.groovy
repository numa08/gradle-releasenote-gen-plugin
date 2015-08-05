package net.numa08.genrelease

import groovy.io.GroovyPrintStream
import net.numa08.genrelease.git.GitLogParser
import net.numa08.genrelease.markdown.MarkdownConverter
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class GenreleasePlugin implements Plugin<Project> {

    public static final String Property_Name = "releaseNote"
    public static final String TASK_NAME = "genReleaseNote"
    @Override
    void apply(Project project) {
        project.extensions.create(Property_Name, GenreleasePluginExtension)
        def extension = project.extensions."$Property_Name"
        def createdTask = project.task(TASK_NAME) << { Task task ->
            def configuration = new Configuration() {
                @Override
                String version() {
                    return extension.version
                }
            }

            def parser = new GitLogParser(configuration)
            def notes = parser.parse(extension.source)
            if (notes._1() != null) {
                def markdown = new MarkdownConverter().convert(notes._1())
                def output = extension.output != null ? new FileOutputStream(extension.output) : System.out
                def printStream = new GroovyPrintStream(output)
                try {
                    printStream.print(markdown)
                } finally {
                    output.close()
                }
            }
        }

        createdTask.description = "Create release note from your configuration"
    }
}
