package net.numa08.genrelease

trait ReleaseNote {

   val features: Seq[Feature]

   val fixes: Seq[Fix]

   val version: String

 }
