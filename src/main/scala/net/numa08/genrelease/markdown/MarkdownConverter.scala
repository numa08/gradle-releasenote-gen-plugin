package net.numa08.genrelease.markdown

import net.numa08.genrelease.{NoteConverter, ReleaseNote, ReleaseType}

class MarkdownConverter extends NoteConverter {

  override def convert(note: ReleaseNote): String = {
    val features = note.features.groupBy(_.scope)
    val fixes = note.fixes.groupBy(_.scope)

    def makeMarkdown(keys: Iterable[String], notes: Map[String, Seq[ReleaseType]]): String = {
      (for (key <- keys) yield {
        val indexes = notes(key).map(_.subject).mkString(" - ", "\n - ", "")
        s"""
           |### $key
            |
            |$indexes
            |""".stripMargin
      }).mkString("")
    }

    val featuresNote = if (features.keys.isEmpty) ""
    else {
      s"""
         |## 新機能
         |${makeMarkdown(features.keys, features)}
      """.stripMargin
    }
    val fixesNote = if (fixes.keys.isEmpty) ""
    else {
      s"""
         |## 不具合修正
         |${makeMarkdown(fixes.keys, fixes)}
       """.stripMargin
    }

    featuresNote + fixesNote
  }

}
