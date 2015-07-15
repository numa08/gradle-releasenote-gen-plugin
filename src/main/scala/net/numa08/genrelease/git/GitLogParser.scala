package net.numa08.genrelease.git

import net.numa08.genrelease.{Fix, ReleaseType, ReleaseNote, Parser}

class GitLogParser extends Parser {

  override def parse(source: String): Either[Throwable, ReleaseNote] = ???

}

object GitLogParser {

  def parseLine(line: String): Either[Throwable, ReleaseType] = ???

}