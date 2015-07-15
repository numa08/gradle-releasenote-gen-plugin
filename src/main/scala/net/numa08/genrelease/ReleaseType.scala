package net.numa08.genrelease

trait ReleaseType{

  val scope: String
  val subject: String

}

case class Feature(scope: String, subject: String) extends ReleaseType
case class Fix(scope: String, subject: String) extends ReleaseType
