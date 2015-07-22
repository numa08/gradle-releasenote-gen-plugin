package net.numa08.genrelease.git

import net.numa08.genrelease._

import scala.util.control.Exception._

case class GitLogParser(configuration: Configuration) extends Parser {

  override def parse(source: String): (ReleaseNote, Seq[ParseError]) = {
    val lines = source.split("\n")
    val parses = lines.zip(lines.map(GitLogParser.parseLine))

    (new ReleaseNote {
      override val fixes: Seq[Fix] = parses.collect { case (_, Right(f: Fix)) => f }
      override val features: Seq[Feature] = parses.collect { case (_, Right(f: Feature)) => f }
      override val version: String = configuration.version
    }, parses.collect { case (l, Left(e)) => ParseError(l, e) })
  }

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