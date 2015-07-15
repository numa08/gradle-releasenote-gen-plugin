package net.numa08

trait ReleaseType{

  val scope: String
  val subjects: Seq[String]

}

case class Feature(scope: String, subjects: Seq[String]) extends ReleaseType
case class Fixes(scope: String, subjects: Seq[String]) extends ReleaseType
