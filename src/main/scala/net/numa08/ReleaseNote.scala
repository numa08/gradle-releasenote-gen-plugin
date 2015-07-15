package net.numa08

trait ReleaseNote {

  val features: Seq[Feature]

  val fixes: Seq[Fixes]

  val version: String

}
