package net.numa08.genrelease.git

import net.numa08.genrelease._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class GitLogParserSpec extends FlatSpec with Matchers{

  "GitLogParser" should "parse line" in {
    val fixesLine = "fix/なんとか機能: どうしようもないクラッシュ問題を対応しました。"
    assert(GitLogParser.parseLine(fixesLine) == Right(Scope("なんとか機能"), Fix("どうしようもないクラッシュ問題を対応しました。")))

    val featureLine = "feat/いつも使う機能: 誰もが驚く世紀の大発明を実施しました。"
    assert(GitLogParser.parseLine(featureLine) == Right(Scope("いつも使う機能"), Feature("誰もが驚く世紀の大発明を実施しました。")))
  }

  "GitLogParse" should "not parse line" in {
    val unValidLine = "val/うわわわ: うけるー"
    assert(GitLogParser.parseLine(unValidLine) == Left(UnKnownTypeException("val is unknown type")))

    val line = "むちゃくちゃおかしい行"
    assert(GitLogParser.parseLine(line).left.get.getMessage == s"$line does not have : ")
  }

  "GitLogParser" should "parse multi line" in {
    val target = """fix/なんとか機能: どうしようもないクラッシュ問題を対応しました。
feat/いつも使う機能: 誰もが驚く世紀の大発明を実施しました
fix/なんとか機能: 悲しみあふれる闇を取り払いました
feat/ときどき使う機能: 驚天動地の新機能を実装しました。
ほげほげ
                 """
    val config = new Configuration {
      override val version: String = "test"
    }

    val (note, err) = GitLogParser(config).parse(target)
    assert(err.head.line == "ほげほげ")

    assert(note.version == "test")
    assert(note.releases == List(
      Features(Map(Scope("ときどき使う機能") -> ArrayBuffer(Feature("驚天動地の新機能を実装しました。")),
        Scope("いつも使う機能") -> ArrayBuffer(Feature("誰もが驚く世紀の大発明を実施しました")))),
      Fixes(Map(Scope("なんとか機能") -> ArrayBuffer(Fix("どうしようもないクラッシュ問題を対応しました。"), Fix("悲しみあふれる闇を取り払いました"))))))
  }
}