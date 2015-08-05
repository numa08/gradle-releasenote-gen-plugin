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

    def "extention properties should load"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.with {
            apply plugin: 'net.numa08.genrelease'
            releaseNote {
                version = "0.1"
                source = """fix/なんとか機能: どうしようもないクラッシュ問題を対応しました。
feat/いつも使う機能: 誰もが驚く世紀の大発明を実施しました
fix/なんとか機能: 悲しみあふれる闇を取り払いました
feat/ときどき使う機能: 驚天動地の新機能を実装しました。
ほげほげ
                 """
            }
        }

        then:
        project.tasks.findByName("genReleaseNote") != null
    }
}
