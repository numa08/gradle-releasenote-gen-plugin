package net.numa08.genrelease

case class Scope(scope: String)

sealed trait ReleaseType {
  val subject: String
}


case class Feature(subject: String) extends ReleaseType

case class Fix(subject: String) extends ReleaseType

object ReleaseType {

  def apply(t: String, subject: String): ReleaseType = t match {
    case "fix" => Fix(subject)
    case "feat" => Feature(subject)
    case _ => throw UnKnownTypeException(s"$t is unknown type")
  }

}

sealed trait Release {}

case class Features(releases: Map[Scope, Seq[Feature]]) extends Release

case class Fixes(releases: Map[Scope, Seq[Fix]]) extends Release

case object Empty extends Release

object Release {

  def apply[T <: ReleaseType](r: Map[Scope, Seq[T]]): Release = if (r.isEmpty) {
    Empty
  } else {
    r.values.head.head match {
      case Feature(_) => Features(r.asInstanceOf[Map[Scope, Seq[Feature]]])
      case Fix(_) => Fixes(r.asInstanceOf[Map[Scope, Seq[Fix]]])
    }
  }

}

case class UnKnownTypeException(message: String) extends RuntimeException(message)