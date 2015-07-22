package net.numa08.genrelease

trait Parser {

  def parse(source: String): (ReleaseNote, Seq[ParseError])

}

case class ParseError(line: String, tr: Throwable) extends Exception