package net.numa08.genrelease.git

import net.numa08.genrelease.{Feature, Fix}
import org.scalatest.{Matchers, FlatSpec}

class GitLogParserSpec extends FlatSpec with Matchers{

  "GitLogParser" should "parse line" in {
    val fixesLine = "fix/なんとか機能: どうしようもないクラッシュ問題を対応しました。"
    GitLogParser.parseLine(fixesLine) == Right(Fix("なんとか機能", "どうしようもないクラッシュ問題を対応しました。"))

    val featureLine = "feat/いつも使う機能: 誰もが驚く世紀の大発明を実施しました。"
    GitLogParser.parseLine(featureLine) == Right(Feature("いつも使う機能", "誰もが驚く世紀の大発明を実施しました。"))
  }

}
