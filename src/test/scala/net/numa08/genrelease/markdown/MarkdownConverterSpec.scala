package net.numa08.genrelease.markdown

import net.numa08.genrelease.{Feature, Fix, ReleaseNote}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class MarkdownConverterSpec extends FlatSpec with Matchers {

  "MarkdownExporter" should "export markdown" in {
    val note = new ReleaseNote {
      override val fixes: Seq[Fix] = List(
        Fix("なんとか機能", "もうクラッシュなんてしない"),
        Fix("ほげ機能", "今度こそ大丈夫"),
        Fix("なんとか機能", "もう大丈夫")
      )

      override val features: Seq[Feature] = List(
        Feature("むっちゃ使う機能", "驚天動地の最新機能"),
        Feature("微妙な機能", "いい感じやで"),
        Feature("むっちゃ使う機能", "めっちゃ使いやすくなった")
      )
      override val version: String = "test"
    }

    val markdown = new MarkdownConverter().convert(note)
    println(markdown)

  }
}
