package net.numa08.genrelease.git

import net.numa08.genrelease._

import scala.util.control.Exception._

class GitLogParser extends Parser {

  override def parse(source: String): Either[Throwable, ReleaseNote] = ???

}

object GitLogParser {

  def parseLine(line: String): Either[Throwable, ReleaseType] = allCatch either {
    def split(s: String, d: String): (String, String) = s.split(d).array match {
      case Array(x, y, _*) => (x, y)
      case _ => throw new RuntimeException(s"$s does not have $d")
    }

    val (pre, subject) = split(line, ": ")
    val (_type, scope) = split(pre, "/")
    ReleaseType(_type, scope, subject)
  }
}