# マスタ一覧ページ設計ドキュメント

このドキュメントは、TheFairValue プロジェクトのマスタ一覧ページ（国）のリクエストからレスポンスまでのデータ処理フローをまとめたものです。
また、同じ構造のマスタ一覧ページを再現するためのファイル、クラス、メソッド、DBテーブル構成も記載します。

---

## 1. 既存マスタ一覧ページの対象

| ページ | URL | テンプレート | コントローラ | サービス | DAO | Entity | DBテーブル |
|---|---|---|---|---|---|---|---|
| 国マスタ | `/countries` | `country-list/country-list.html` | `CountryController` | `CountryService` / `CountryServiceImpl` | `CountryDao` | `CountryEntity` | `countries` |

---

## 2. 共通のリクエスト→レスポンスフロー

### 2.1 GET リクエストを受け取る

1. ブラウザから `GET /countries` などの URL へアクセス
2. Spring MVC が `CountryController` などの `@RequestMapping` にマッチ

```java
// 例: シンプルな Controller の GET ハンドラ
@Controller
@RequestMapping("/countries")
public class CountryController {
  private final CountryService countryService;
  public CountryController(CountryService countryService) { this.countryService = countryService; }
  @GetMapping("")
  public String display(Model model) {
    model.addAttribute("items", countryService.initialDispAll());
    return "country-list/country-list"; // Thymeleaf テンプレート名
  }
}
```

### 2.2 コントローラで処理

- `Controller` は `ModelAndView` を作成
- サービス層を呼び出してデータを取得
- 取得した DTO リストをモデルに追加
- ビュー名を設定してレスポンス HTML を返す

```java
// 例: ModelAndView を使う場合の書き方
@Controller
@RequestMapping("/countries")
public class CountryController {
  private final CountryService countryService;
  public CountryController(CountryService countryService) { this.countryService = countryService; }
  @GetMapping("")
  public ModelAndView display(ModelAndView mav) {
    mav.setViewName("country-list/country-list");
    mav.addObject("items", countryService.initialDispAll());
    return mav;
  }
}
```

### 2.3 サービス層で変換

- `initialDispAll()` などのメソッドで DAO の `selectAll()` を呼ぶ
- Entity を Response DTO に変換
- `List<...ResponseDto>` を返す

```java
// 例: Service 内で Entity を DTO に変換（for 文版）
@Service
@Transactional
public class CountryServiceImpl implements CountryService {
  private final CountryDao countryDao;
  public CountryServiceImpl(CountryDao countryDao) { this.countryDao = countryDao; }

  @Override
  public List<CountryResponseDto> initialDispAll() {
    List<CountryResponseDto> list = new ArrayList<>();
    for (var entity : countryDao.selectAll()) {
      list.add(new CountryResponseDto(
        entity.getId(),
        entity.getCode(),
        entity.getName()
      ));
    }
    return list;
  }
}
```

### 2.4 DAO で DB 参照

- Doma の `@Dao` インタフェースを利用
- `@Select` アノテーション付きメソッドで全件取得
- 返却値は `List<...Entity>`

```java
// 例: Doma を使った DAO インタフェース
@Dao
@ConfigAutowireable
public interface CountryDao {
  @Select
  List<CountryEntity> selectAll();
}
```

### 2.5 DB テーブルからデータ取得

- `countries`のテーブルから SELECT
- Doma が Entity にマッピング

```sql
-- 例: テーブル定義
CREATE TABLE countries (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  code VARCHAR(255)
);
```

```java
// 例: 単純な Entity 定義（JPA 風、実際は Doma の注釈を使うことが多い）
@Entity
@Table(name = "countries")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    // getters/setters
}
```

### 2.6 View で表示

- Thymeleaf テンプレートが `items` をループ表示
- HTML テーブルとしてレンダリング

```html
<!-- 例: Thymeleaf での表示 -->
<table>
  <tr th:each="item : ${items}">
    <td th:text="${item.id}">ID</td>
    <td th:text="${item.code}">Code</td>
    <td th:text="${item.name}">Name</td>
  </tr>
</table>
```

---

## 3. 管理フローとクラス対応

以下は `Country` を例にした具体的なフローです。ほかのマスタも同じ構造です。

### 3.1 `Country` の完全フロー

- URL: `/countries`
- Controller: `src/main/java/org/example/web/stock/country/controller/CountryController.java`
- Service: `src/main/java/org/example/web/stock/country/service/CountryService.java`
- Service 実装: `src/main/java/org/example/web/stock/country/service/CountryServiceImpl.java`
- Domain DTO: `src/main/java/org/example/web/stock/country/domain/CountryResponseDto.java`
- DAO: `src/main/java/org/example/web/dao/CountryDao.java`
- Entity: `src/main/java/org/example/web/entity/CountryEntity.java`
- Template: `src/main/resources/templates/country-list/country-list.html`

#### 処理メソッド

- `CountryController.display(ModelAndView mav)`
  - ビュー `country-list/country-list` を返す
  - `countryService.initialDispAll()` を呼び出し、`items` を追加

- `CountryServiceImpl.initialDispAll()`
  - `countryDao.selectAll()` で全件取得
  - Entity から `CountryResponseDto` へ変換

```java
// 例: 各処理のサンプル実装まとめ
// CountryController.display
@GetMapping("")
public ModelAndView display(ModelAndView mav) {
    mav.setViewName("country-list/country-list");
    mav.addObject("items", countryService.initialDispAll());
    return mav;
}

// CountryServiceImpl.initialDispAll は上記の for 版を参照

// CountryDao.selectAll の例は 2.4 の DAO セクション参照
```

- `CountryDao.selectAll()`
  - Doma の `@Select` で `countries` テーブル全件を取得

#### DB テーブル推定

```sql
CREATE TABLE countries (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  code VARCHAR(255)
);
```

#### テンプレート内のデータ参照

- `th:each="item : ${items}"`
- `th:text="${item.id}"`
- `th:text="${item.code}"`
- `th:text="${item.name}"`

---

## 4. 各マスタの具体的なクラス一覧

### 4.1 国マスタ

- Controller
  - `src/main/java/org/example/web/stock/country/controller/CountryController.java`
- Service
  - `src/main/java/org/example/web/stock/country/service/CountryService.java`
  - `src/main/java/org/example/web/stock/country/service/CountryServiceImpl.java`
- DTO
  - `src/main/java/org/example/web/stock/country/domain/CountryResponseDto.java`
- DAO
  - `src/main/java/org/example/web/dao/CountryDao.java`
- Entity
  - `src/main/java/org/example/web/entity/CountryEntity.java`
- Template
  - `src/main/resources/templates/country-list/country-list.html`

---

## 5. マスタ一覧ページを再現する手順

### 5.1 1. テンプレートの作成

1. `src/main/resources/templates/{page}-list/{page}-list.html` を作成
2. ページ構成は以下を含む
   - `search-section`（検索、クリア、Excel出力ボタン）
   - `list-section`（テーブル、`th:each` で `items` をループ）
   - 必要に応じて `+ 新規登録` ボタン
3. 共通レイアウトは既存ページを参考にする

### 5.2 2. Controller の作成

1. `src/main/java/org/example/web/stock/{page}/controller/{Page}Controller.java`
2. `@Controller` と `@RequestMapping("/{page-plural}")` を付与
3. `@GetMapping("")` で `ModelAndView` を返す
4. サービスから `initialDispAll()` を呼び出し、`mav.addObject("items", data)` を設定

### 5.3 3. Service / ServiceImpl の作成

1. `src/main/java/org/example/web/stock/{page}/service/{Page}Service.java`
   - `List<{Page}ResponseDto> initialDispAll();`
2. `src/main/java/org/example/web/stock/{page}/service/{Page}ServiceImpl.java`
   - `@Service` / `@Transactional`
   - `private final {Page}Dao {page}Dao;`
   - `initialDispAll()` で `selectAll()` を呼び DTO に変換

### 5.4 4. DTO 作成

1. `src/main/java/org/example/web/stock/{page}/domain/{Page}ResponseDto.java`
2. `id`, 表示に必要なフィールドを `final` に持つ
3. `getter` を用意する

### 5.5 5. DAO 作成

1. `src/main/java/org/example/web/dao/{Page}Dao.java`
2. Doma `@Dao`, `@ConfigAutowireable`
3. `@Select List<{Page}Entity> selectAll();`
4. 追加で `@Select Optional<{Page}Entity> selectById(...)` などを定義可能
5. CRUD を追加する場合は `@Insert`, `@Update`, `@Delete` を記載

### 5.6 6. Entity / DB テーブル作成

1. `src/main/java/org/example/web/entity/{Page}Entity.java`
2. `@Entity(immutable = false)`, `@Table(name = "...")`
3. カラムごとに `@Column(name = "...")`
4. `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY)` を設定

### 5.7 7. DB テーブル設計

#### 5.7.1 countries

```sql
CREATE TABLE countries (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  code VARCHAR(255)
);
```
---

## 6. 現状の実装差分と未完了機能

### 6.1 現在ある UI のみの機能

- 検索ボタン
- クリアボタン
- Excel出力ボタン
- `+ 新規登録` ボタン（マスタ一覧の多くはモーダル実装がない）

### 6.2 `stock-list` で存在するが不完全な実装

- `stock-list.js` に編集・削除・新規登録の Ajax 処理がある
- `StockListRestController` に `insert`, `update`, `delete` がある
- ただし `stock-list.html` の編集/削除リンクは `data-code` のみで `data-id` が設定されていないため、現状では ID 取得に不整合がある可能性が高い

### 6.3 マスタ一覧ページに不足している実装

- REST API などの CRUD エンドポイント
- 検索条件を受け取るバックエンドロジック
- Excel 出力用のバックエンド処理
- 新規 / 編集モーダル UI のサーバ連携

---

## 7. 再現時のまとめ

1. 画面テンプレートを作る
2. MVC の Controller で URL を受ける
3. Service で DTO に変換する
4. DAO で DB から Entity を取得する
5. Entity と DB テーブルを 1:1 で対応させる
6. View で `items` を `th:each` し、テーブル表示する

この構造を踏襲すれば、マスタ一覧ページを再現できます。

---
