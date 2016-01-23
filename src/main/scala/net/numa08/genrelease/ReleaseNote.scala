package net.numa08.genrelease

trait ReleaseNote {
  val releases: Seq[Release]

  val version: String
 }
