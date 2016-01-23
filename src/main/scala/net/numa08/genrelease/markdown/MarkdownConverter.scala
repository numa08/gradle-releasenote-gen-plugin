package net.numa08.genrelease.markdown

import net.numa08.genrelease._

class MarkdownConverter extends NoteConverter {

  override def convert(note: ReleaseNote): String = note.releases.map(makeMarkdown).mkString

  def make(release: Map[Scope, Seq[ReleaseType]]): String = {
    (for (k <- release.keys) yield {
      val i = release(k).map(_.subject).mkString(" - ", "\n - ", "")
      s"""
         |### ${k.scope}
         |$i
       """.stripMargin
    }).mkString
  }

  def makeMarkdown(release: Release): String = release match {
    case Empty => ""
    case Features(r) =>
      s"""
         |## 新機能
         |${make(r)}
       """.stripMargin
    case Fixes(r) =>
      s"""
         |## 不具合修正
         |${make(r)}
       """.stripMargin
  }
}
