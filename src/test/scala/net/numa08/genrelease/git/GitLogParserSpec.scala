package net.numa08.genrelease.git

import net.numa08.genrelease.{Feature, Fix, UnKnownTypeException}
import org.scalatest.{FlatSpec, Matchers}

class GitLogParserSpec extends FlatSpec with Matchers{

  "GitLogParser" should "parse line" in {
    val fixesLine = "fix/なんとか機能: どうしようもないクラッシュ問題を対応しました。"
    assert(GitLogParser.parseLine(fixesLine) == Right(Fix("なんとか機能", "どうしようもないクラッシュ問題を対応しました。")))

    val featureLine = "feat/いつも使う機能: 誰もが驚く世紀の大発明を実施しました。"
    assert(GitLogParser.parseLine(featureLine) == Right(Feature("いつも使う機能", "誰もが驚く世紀の大発明を実施しました。")))
  }

  "GitLogParse" should "not parse line" in {
    val unValidLine = "val/うわわわ: うけるー"
    assert(GitLogParser.parseLine(unValidLine) == Left(UnKnownTypeException("val is unknown type")))

    val line = "むちゃくちゃおかしい行"
    assert(GitLogParser.parseLine(line).left.get.getMessage == s"$line does not have : ")
  }
}