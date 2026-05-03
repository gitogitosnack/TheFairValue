# DOMA4019 エラー対処ガイド（VS Code）

## エラーの概要

```
[DOMA4019] The file "META-INF/org/example/web/dao/XxxDao/selectAll.sql" is not found in the classpath.
```

Doma の `@Select` などのアノテーションが付いた DAO メソッドに対応する SQL ファイルが、
アノテーションプロセッサの実行時に見つからない場合に発生する。

---

## なぜ VS Code でだけ起きるのか

Maven（`mvn compile`）でビルドする場合、コンパイル前にリソースのコピーフェーズが走り、
`src/main/resources` の内容が `target/classes` にコピーされる。
そのため Doma のアノテーションプロセッサは SQL ファイルをクラスパス経由で見つけられる。

VS Code の Java Language Server は**インクリメンタルコンパイル**を行う。
この際、アノテーションプロセッサは Maven のリソースコピーフェーズを経ずに実行されるため、
`src/main/resources` がクラスパスに含まれず、SQL ファイルを見つけられない。

```
Maven ビルド：  resources:copy → compile（APT 実行）  ← SQL ファイルがクラスパスにある
VS Code JLS：              compile（APT 実行）         ← SQL ファイルがクラスパスにない ← DOMA4019
```

---

## 根本的な修正方法（pom.xml に設定を追加）

`maven-compiler-plugin` に `-Adoma.resources.dir` オプションを追加する。
これにより Doma のアノテーションプロセッサがクラスパスではなく**絶対パス**で SQL ファイルを探すようになる。

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>org.seasar.doma</groupId>
                <artifactId>doma-processor</artifactId>
                <version>2.53.1</version>
            </path>
        </annotationProcessorPaths>
        <compilerArgs>
            <arg>-Adoma.resources.dir=${project.basedir}/src/main/resources</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

設定追加後は VS Code で **「Update Project Configuration」** を実行してプロジェクトを再読み込みする。

---

## それでも消えない場合のチェックリスト

### 1. SQL ファイルが存在するか確認

SQL ファイルのパスは DAO の完全修飾クラス名に対応する。

| DAO クラス | 期待される SQL ファイルパス |
|---|---|
| `org.example.web.dao.XxxDao` のメソッド `selectAll` | `src/main/resources/META-INF/org/example/web/dao/XxxDao/selectAll.sql` |

ファイルが存在しない場合は作成する（次のセクション参照）。

### 2. VS Code のプロジェクト設定を再ロード

コマンドパレット（`Ctrl+Shift+P`）から以下を順に試す。

1. `Java: Force Java Compilation` → `Full`
2. `Java: Clean Java Language Server Workspace`（ワークスペースを再ビルド）

### 3. Maven でビルドして確認

```bash
mvn clean compile
```

Maven ビルドでもエラーが出る場合はコードまたは SQL ファイルに問題がある。
VS Code のみでエラーが出る場合は Language Server の設定・キャッシュの問題。

---

## SQL ファイルが存在しない場合の作成手順

### ファイルの配置場所

```
src/main/resources/META-INF/{パッケージパス}/{Dao名}/{メソッド名}.sql
```

### 基本的な selectAll.sql テンプレート

```sql
select
  /*%expand*/*
from
  テーブル名
```

`/*%expand*/` は Doma の式で、エンティティのすべてのカラムを展開する。

### パラメータを使う select の例（selectById.sql）

```sql
select
  /*%expand*/*
from
  テーブル名
where
  id = /* id */0
```

`/* パラメータ名 */デフォルト値` が Doma のバインド変数構文。

---

## このプロジェクトで行った対応（2026-05-04）

**症状：** `AnalysisIndicatorDao.java` の `selectAll()` に DOMA4019 が表示された。

**原因：** `pom.xml` に `-Adoma.resources.dir` の設定がなく、VS Code のアノテーションプロセッサが SQL ファイルを見つけられなかった。SQL ファイル自体（`selectAll.sql`）は存在しており、`mvn clean compile` は正常終了していた。

**対処：** `pom.xml` の `maven-compiler-plugin` に `<compilerArgs>` を追加した。
