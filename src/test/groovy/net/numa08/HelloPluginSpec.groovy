package net.numa08

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class HelloPluginSpec extends Specification {

    def "apply() should load the plugin"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.with {
            apply plugin: 'net.numa08.hello'
        }

        then:
        project.plugins.hasPlugin(HelloPlugin)
    }

}
