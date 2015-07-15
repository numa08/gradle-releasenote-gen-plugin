Gradle Release Note Generator Plugin
======================

Generate symple release notes from git log for gradle project.

Tasks
--------

 - generateReleaseNote
 - generateReleaseMarkdown

Configurations
--------------

 - commitFrom(String)
     - from commit
 - commitTo(String)
 	  - to commit

Sample
------

When `git log` are

```
fix/なんとか機能: どうしようもないクラッシュ問題を対応しました。
feat/いつも使う機能: 誰もが驚く世紀の大発明を実施しました
fix/なんとか機能: 悲しみあふれる闇を取り払いました
feat/ときどき使う機能: 驚天動地の新機能を実装しました。
```

then, will generate

```markdown
## 新機能

[いつも使う機能]

 - 誰もが驚く世紀の大発明を実施しました

[ときどき使う機能]

 - 驚天動地の新機能を実装しました。

## 不具合修正

[なんとか機能]
 - どうしようもないクラッシュ問題を対応しました。
 - 悲しみあふれる闇を取り払いました
```