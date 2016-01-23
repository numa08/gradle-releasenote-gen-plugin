package net.numa08.genrelease.markdown

import net.numa08.genrelease._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.Stream.Empty

@RunWith(classOf[JUnitRunner])
class MarkdownConverterSpec extends FlatSpec with Matchers {

  "MarkdownExporter" should "export markdown" in {
    val note = new ReleaseNote {
      override val version: String = "test"

      override val releases: Seq[Release] = List(
        Release(
          Map((Scope("むっちゃ使う機能"), Feature("驚天動地の最新機能") :: Feature("めっちゃ使いやすくなった") :: Nil),
            (Scope("微妙な機能"), Feature("いい感じやで") :: Nil))
        ),
        Release(
          Map((Scope("なんとか機能"), Fix("もうクラッシュなんてしない") :: Fix("もう大丈夫") :: Nil),
            (Scope("ほげ機能"), Fix("今度こそ大丈夫") :: Nil))
        )
      )
    }

    val markdown = new MarkdownConverter().convert(note)
    println(markdown)

  }

  "Empty message" should "export empty markdown" in {
    val note = new ReleaseNote {
      override val releases: Seq[Release] = Empty
      override val version: String = "test"
    }

    val markdown = new MarkdownConverter().convert(note)
    assert(markdown.isEmpty)
  }
}
