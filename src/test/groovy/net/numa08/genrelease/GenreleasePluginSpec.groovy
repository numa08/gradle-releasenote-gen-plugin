package net.numa08.genrelease

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class GenreleasePluginSpec extends Specification {

    def "apply() should load the plugin"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.with {
            apply plugin: 'net.numa08.genrelease'
        }

        then:
        project.plugins.hasPlugin(GenreleasePlugin)
    }

}
