package net.numa08.genrelease

trait ReleaseType{

  val scope: String
  val subject: String

}

case class Feature(scope: String, subject: String) extends ReleaseType
case class Fix(scope: String, subject: String) extends ReleaseType

object ReleaseType {

  def apply(t: String, scope: String, subject: String): ReleaseType = t match {
    case "fix" => Fix(scope, subject)
    case "feat" => Feature(scope, subject)
    case _ => throw UnKnownTypeException(s"$t is unknown type")
  }
}

case class UnKnownTypeException(message: String) extends RuntimeException(message)