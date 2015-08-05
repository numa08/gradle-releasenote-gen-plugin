package net.numa08.genrelease

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
        final GenreleasePluginExtension extension = project.extensions."$Property_Name"
        final Task createdTask = project.task(TASK_NAME) << { Task task ->
            final Configuration configuration = new Configuration() {
                @Override
                String version() {
                    return extension.version()
                }
            }

            var parser = new GitLogParser(configuration)
            var notes = parser.parse(extension.source())
            if (notes._1() != null) {
                var markdown = new MarkdownConverter().convert(notes._1())
                println(markdown)
            }
        }

        createdTask.description = "Create release note from your configuration"
    }
}
