package net.numa08.genrelease.git

import net.numa08.genrelease._

import scala.util.control.Exception._

case class GitLogParser(configuration: Configuration) extends Parser {

  override def parse(source: String): (ReleaseNote, Seq[ParseError]) = {
    val lines = source.split("\n")
    val parses = lines.zip(lines.map(GitLogParser.parseLine))

    (new ReleaseNote {
      override val releases: Seq[Release] = List(
        Release(parses.collect { case (_, Right((s, f: Feature))) => (s, f) }.toSeq.groupBy { case (s, _) => s }.map { case (s, seq) => (s, seq.map { case (_, f) => f }) }),
        Release(parses.collect { case (_, Right((s, f: Fix))) => (s, f) }.toSeq.groupBy { case (s, _) => s }.map { case (s, seq) => (s, seq.map { case (_, f) => f }) })
      )
      override val version: String = configuration.version
    }, parses.collect { case (l, Left(e)) => ParseError(l, e) })
  }

}

object GitLogParser {

  def parseLine(line: String): Either[Throwable, (Scope, ReleaseType)] = allCatch either {
    def split(s: String, d: String): (String, String) = s.split(d).array match {
      case Array(x, y, _*) => (x, y)
      case _ => throw new RuntimeException(s"$s does not have $d")
    }

    val (pre, subject) = split(line, ": ")
    val (_type, scope) = split(pre, "/")
    (Scope(scope), ReleaseType(_type, subject))
  }
}