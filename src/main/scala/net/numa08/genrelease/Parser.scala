package net.numa08.genrelease

trait Parser {

  def parse(source: String): Either[Throwable, ReleaseNote]

}
