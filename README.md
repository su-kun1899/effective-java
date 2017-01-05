# effective-java
study effective java

# 第2章 オブジェクトの生成と消滅

## 項目1 コンストラクタの代わりにstaticファクトリーメソッドを検討する

 - 長所
    - コンストラクタと異なり、名前がある
    - メソッドが呼び出されるごとに新たなオブジェクトを生成する必要がない
    - ~~パラメータ化された型のインスタンス生成の面倒さを低減する~~
 - 短所
    - public/protectedのコンストラクタを持たないクラスのサブクラスを作れない
    - 他のstaticメソッドと区別がつかない
        - ファクトリーメソッドの一般的な命名
            - valueOf : パラメータと同じ値のインスタンスを返す（型変換）
            - of : valueOfの代替
            - getInstance
            - newInstance
            - getType : 返す型が異なる場合、Typeがオブジェクトの型を示す
            - newType

## 項目2 数多くのコンストラクタパラメータに直面したときにはビルダーを検討する

 - コンストラクタのパラメータが多い場合にビルダーパターンが有用
     - 特にオプションが多い場合

## 項目3 privateのコンストラクタかenum型でシングルトン特性を矯正する

 - シングルトンを実装する最善の方法は単一要素のenum型を用いること
   - 簡潔である
   - シリアライズ機構が提供されている

## 項目4 privateのコンストラクタでインスタンス化不可を強制する

 - ユーティリティクラスはprivateコンストラクタでインスタンス化不可にする

## 項目5 不必要なオブジェクトの生成を避ける

 - コストの高い処理をstatic初期化子で回避する
 - 意図しないオートボクシングが発生しないようにする

## 項目6 廃れたオブジェクト参照を取り除く

 - ガベージコレクションの対象にならないオブジェクトから参照を外す
    - スコープから追いやる
    - nullを代入する（例外的：配列の不要要素など）
    - キャッシュエントリー
    - リスナーやコールバック

## 項目7 ファイナライザを避ける

 - ファイナライザは使用しない

# 第3章 すべてのオブジェクトに共通のメソッド

## 項目8 equalsをオーバーライドするときは一般契約に従う

 - 引数が自分自身のオブジェクトへの参照であるかを検査するのに==演算子を使用する
 - 引数が正しい型であるかを検査するのにinstanceof演算子を使用する
 - 引数を正しい型にキャストする（instansofの後）
 - クラスの各フィールドに対して、引数のオブジェクトのフィールドが一致するかを検査する
 - equalsメソッドは対称的・推移的・整合的にする
    - `y.equals(x)` がtrueの場合のみ `x.equals(y)` はtrueを返す
    − `x.equals(y)` と `y.equals(z)` がtrueを返す場合、`x.equals(z)` はtrueを返す
    - 比較に使用される情報が変更されなければ、`x.equals(y)` の複数回呼び出しは一貫してtrueもしくはfalseを返す 
 - equalsをオーバライドする時は常にhashCodeをオーバーライドする（項目9）
 - あまりにも賢くなろうとしない
 - equals宣言中のObjectを他の型で置き換えない

## 項目9 equalsをオーバーライドするときは、常にhashCodeをオーバーライドする
 
 - hashCodeのオーバライドを忘れるとハッシュに基づくコレクションで適切に機能しない
 - パフォーマンスに大きな違いが出るので、equalsが等しくないオブジェクトは別々の整数結果を返すほうがよい
    1. ゼロではない任意の定数をint変数resultに保存する
    1. 意味のある（equalsで考慮される）各フィールドに対するハッシュ値を計算する
    1. `result = 31 * result + c`

## 項目10 toString を常にオーバライドする

 - toString あると使いやすくなる
 - 興味がある情報を <u>すべて</u> 返すべき
 - すべての情報へのアクセス手段（アクセサ）を提供する

## 項目11 clone を注意して実装する

 - Cloneable インターフェースは使い勝手が悪い
 - 代替手段を用意するか、複製を提供しないほうが賢明
 - コピーコンストラクタかコピーファクトリーを提供するのがうまい方法
    - `public Yum(Yum yum)`
    - `public static Yum newInstance(Yum yum)`

## 項目12 Comparable の実装を検討する

 - 自然な順序がある場合実装を検討する
 - compareTo メソッドの一般契約
    - sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) を保証する
    - (x.compareTo(y) > 0 && y.compareTo(z) > 0) は x.compareTo(z) > 0 を保証する
    − x.compareTo(y) == 0 が sgn(x.compareTo(z)) == sgn(y.compareTo(z)) を保証する
 - compareTo は符号だけで指定されるが、int のオーバーフローに注意が必要

# 第4章 クラスとインターフェース

## 項目13 クラスとメンバーへのアクセス可能性を最小限にする

 - アクセススコープは可能な限り小さくする
 - publicのクラスはpublicのフィールドを持つべきではない

## 項目14 public のクラスでは、public のフィールドではなく、アクセッサーメソッドを使う

 - publicのクラスは決して可変のフィールドを公開するべきではない

## 項目15 可変性を最小限にする

 - クラスを不変にする規則
   - オブジェクトの状態を変更するメソッドを提供しない（setter）
   - クラスが拡張できないようにする
      - final クラスにする
      - コンストラクタをprivateにして、staticファクトリーを提供する（多くの場合こちらが最善）
   - すべてのフィールドをfinal にする
   - すべてのフィールドをprivate にする
   - 可変オブジェクトフィールドへの参照を独占する
      - 防御的コピーを使う
 - 不変にできなくても可変性はできるだけ制限する
 - やむを得ない理由がない限りすべてのフィールドをfinalにする

## 項目16 継承よりコンポジションを選ぶ

 - 継承はカプセル化を破る
 - サブクラスとスーパークラス間に本当のサブタイプ関係がある場合にだけ継承は適切
 - 適切な場合でも、拡張される設計になっていなければ、もろさへとつながる
 - コンポジションと転送を使う
    - ラッパークラスは頑強で強力
    - デコレータや移譲と呼ばれることもある

## 項目17 継承のために設計および文書化する、でなければ継承を禁止する

 - 継承のためにクラスを設計することは、そのクラスにかなりの制限を課す
 - 安全にサブクラス化するための設計と文書化をされていないクラスでのサブクラスの禁止

## 項目18 抽象クラスよりインターフェースを選ぶ

 - インターフェースはミックスインを定義するのには最適
 - インターフェースは階層を持たない型フレームワークを構築することが可能
 - インターフェースはラッパークラスを使えば安全で強力な機能エンハンスが可能
 - 発展しやすさが柔軟性や能力よりも重要だと考えられる場合は抽象クラスを利用する
     - 抽象骨格実装クラスをインターフェースごとに提供するとインターフェースと抽象クラスの長所を組み合わせられる
        - 疑似多重継承
        - インターフェースを発展させるよりは抽象クラスを発展させるほうが容易

## 項目19 型を定義するためだけにインターフェースを使用する

 - インターフェースは型を定義するためだけに使用すべき
 - 定数を提供するために使用するべきではない
    - 既存のクラスやインターフェースに強く結びつくならそのクラスやインターフェースに追加する
    - 列挙型が最善ならばenum型で提供する
    - インスタンス化不可能なユーティリティクラスで提供する

## 項目20 タグ付けクラスよりクラス階層を選ぶ

 - タグ付けクラスが適切な場合はほとんどない
    - フィールドがfinal にできない
    - 特性を追加したらswitch文にcaseを追加しなければならない
    - 冗長で、誤りやすく、非効率
 - サブタイプを使う
    - 継承、抽象クラスの利用なので、スコープに注意

## 項目21 戦略を表現するために関数オブジェクトを使用する

 - Startegyパターンの実装に関数ポインタを使用する
 - 関数インターフェースに対しては無名クラスを使用してインスタンス化する
    - Java8 からはlambda 記法があるのでそちらを使うのがBetter
 - 繰り返し使用する場合にはprivate static なメンバークラスとpublic static final のフィールドで提供する

## 項目22 非static のメンバークラスよりstatic のメンバークラスを選ぶ

 - ネストしたクラスには4種類ある
    - 一つのメソッドにおさまる
        - 一箇所からのみインスタンス生成する必要があり、特徴づける型がある
            - 無名クラス
        - そうでなければ
            - ローカルクラス（最も使用されない）
    - 一つのメソッドにおさまらない
        - エンクロージングインスタンスへの参照が必要
            - 非static なメンバークラス
        - 不要
            - static なメンバークラス

# 第5章 ジェネリックス

## 項目23 新たなコードで原型を使用しない

 - 原型を使用することは実行時例外となる可能性がある
    - 互換性のためだけにあるので新しいコードでは使用しない
 - Set<Object> は任意の型のオブジェクトを含むことが可能
 - Set<?> は何らかの不明な型のオブジェクトだけを含むことが可能

## 項目24 無検査警告を取り除く

 - 無検査警告は実行時例外の可能性を示している。無視しない
 - 取り除けない場合で型安全を示せるのならば `@SuppressWarnings("unchecked")` で警告を抑制する
    - できるだけ最小限のスコープにする
    - コメントに理由を残す

## 項目25 配列よりリストを選ぶ

 - ジェネリックスはコンパイル時の型安全を保証する

## 項目26 ジェネリック型を使用する

 - キャストよりも安全で使いやすい
 - 新たな型はクライアント側でキャストが不要なよう設計する

## 項目27 ジェネリックメソッドを使用する

 - ジェネリック型と同様に、安全で使いやすい

## 項目28 API の柔軟性向上のためにワイルドカードを使用する

 - ワイルドカードはAPI を柔軟にする
 - 基本原則はPECS
    - Producer -extends, Consumer -super
    - すべての比較可能なものとコンパレータは、Consumer である

## 項目29 型安全な異種コンテナーを検討する

 - コンテナーではなく、キーに対して型パラメータを指定する手法がある
 - キーにはクラスオブジェクトを使用できる
 - 境界型トークンを使えば制限を設けられる
    - `public <T extends Annotation> T getAnnotation(Class<T> annotationType);`

## 項目30 int 定数の代わりにenum を使用する

 - 固定数の定数が必要な場合、常にenum を使用すべき
 - int 定数よりもはるかに読みやすく、安全であり、強力
 - データを関連付けたり、データによって振る舞いが変わるメソッドを提供できる
 - switch を行うenum よりは、定数固有メソッドを選ぶ
 - 複数の enum 定数が共通の振る舞いを共有するのであれば、戦略enum パターンを検討する

## 項目31 序数の代わりにインスタンスフィールドを使用する

 - ordinal は使用しないのが最善
 - 序数からenum に関連付けられる値を導くようなことはしない

## 項目32 ビットフィールドの代わりにEnumSet を使用する

 - 列挙型が集合の中で利用されると言うだけで、ビットフィールドで表現する理由はない
 - EnumSetは 簡潔性とパフォーマンスをenum 型の利点と組み合わせて提供する

## 項目33 序数インデックスの代わりにEnumMap を使用する

 - 配列をインデックスるためにEnum.ordinal を使用すべきではない
 - EnumMap を使う
 - 多次元は `EnumMap<..., EnumMap<...>>` を使用する

## 項目34 拡張可能な enum をインターフェースで模倣する

 - 基本の enum に伴うインターフェースを実装させて模倣可能にする
 - コードの重複が多くなる場合はヘルパークラスか static のヘルパーメソッドにカプセル化する

## 項目35 命名パターンよりアノテーションを選ぶ

 - 命名パターンには短所が多い
    - 誤字のリスク
    - 適切な要素だけに使用される保証がない
    - パラメータ値を関連付けるよい方法がない
 - アノテーションあるのでそれを使う

## 項目36 常にOverrideアノテーションを使用する

 - オーバライドしているメソッド宣言にはすべてOverrideアノテーションを使用する
    - コンパイラやIDEが誤りを発見してくれる
 - 具象クラスの場合コンパイルエラーになるので必要はない
    - しかし付けても害はない

## 項目37 型を定義するためにマーカーインタフェースを使用する

 - マーカーインタフェースはメソッドを宣言していないインターフェース
   - 型が定義される
 - マーカーアノテーションはdefaultを用意することで拡張がしやすい
   - インターフェースを変更するコストは高い
 - マーカーインタフェースとマーカーアノテーションどちらが適切かは時間をかけて考える
    - クラスやインターフェース以外のプログラム要素をマークするならマーカーアノテーション
    - マークしたオブジェクトをだけを受け付けるメソッドを書きたければマーカーインタフェース
 - 型を定義したいならインターフェースを使用する

# 第7章 メソッド

## 項目38 パラメータの正当性を検査する

 - パラメータの制約は文書化すべき
 - メソッドのはじめで明示的に検査することで制約を矯正する
 - 制約は本来なら少ないほうがいい

## 項目39 必要な場合には、防御的にコピーする

 - クライアントから得たり、返す可変の要素があるなら、防御的にコピーする
 - コピーのコストが高い場合、クライアントが信頼できるなら代わりにドキュメンテーションする

## 項目40 メソッドのシグニチャを注意深く設計する

 - メソッド名を注意深く選ぶ
 - 便利なメソッドを提供しすぎない
 - 長いパラメータのリストは避ける(4個以下を目標にする)
    - メソッドを複数のメソッドに分割して各メソッドはパラメータのサブセットを受け取るようにする
    - パラメータが何らかの実体を保持しているならヘルパークラス(staticなインナークラスの場合が多い)を作成する
    - ビルダーパターンを使用する
 - パラメータ型に関しては、クラスよりインターフェースを選ぶ
 - booleanパラメータより２つの要素を持つenum型を使用する

## 項目41 オーバーロードを注意して使用する

 - 同じ数のパラメータを持つシグニチャでメソッドをオーバーロードすることは控えるべき
 - コンストラクタなどで不可避の場合はキャストによって同じ組み合わせが渡せる事態は避ける

## 項目42 可変長引数を注意して使用する

 - 1個以上の引数が必要な場合、通常のパラメータと残りを可変長にする
 - 可変長引数は乱用すべきではない

## 項目43 nullではなく、空配列か空コレクションを返す

 - nullを返すことはクライアント側やメソッド自身を複雑にする
 - (C言語と違って)長さゼロが返せるのでnullを返すべき理由はない

## 項目44 すべての公開API要素に対してドキュメントコメントを書く

 - APIを文書化するにはドキュメンテーションコメントが最善で効果的
 - すべての公開API要素に対して必須だと見なされるべき
 - 標準規約に従ったスタイルにする
 - HTMLメタ文字のエスケープを忘れない

# 第8章 プログラミング一般

## 項目45 ローカル変数のスコープを最小限にする

 - ローカル変数は使用するときに宣言する
 - 初期化子を含んでいるべきである
 - whileループよりforループを選ぶ
 - メソッドを小さくして焦点をハッキリさせる
    - 一つの処理に対して一つのメソッドにする

## 項目46 従来のforループよりfor-eachループを選ぶ

 - 明瞭性とバグ予防に関してfor-eachは優れている
 - パフォーマンス上のペナルティもない
 - 下記の場合はfor-eachループが使えない
    - フィルタリング
    - 変換
    - 並列イテレーション
 - Java7以降ならStreamApiを第一の選択肢にすべきだろうか

## 項目47 ライブラリーを知り、ライブラリーを使う

 - 標準ライブラリーには書いた専門家と、使用した人々の経験が詰まっている
 - 主要リリースごとにライブラリに追加される機能を知っておく
 - `java.lang` , `java.util` の内容と、ある程度 `java.io` の内容を知っておくべき
 - ライブラリーのコードは平均的開発者が専念できるよりもはるかに多くの配慮がなされる
    - 共通だと思えることをする場合、ライブラリーに既にないか調べる

## 項目48 正確な答えが必要ならば、floatとdoubleを避ける

 - 正確な答えを必要とする計算にfloatやdoubleを使用しない
 - BigDecimalかintかlongを使う
 - 9桁を超えないならint、18桁を超えないならlong、それ以上ならBigDecimal
 - 丸めが必要ならBigDecimal
    - ただしコストと不便さがある

## 項目49 ボクシングされた基本データより基本データ型を選ぶ

 - ボクシングされた基本データ型に `==` 演算子を適用するのはほとんどの場合誤り
 - 選択できる場合には、基本データ型を選ぶ
    - 単純で早い
    - アンボクシングの際にNullPointerExceptionをスローする可能性
    - 無駄なオブジェクト生成によるパフォーマンス問題
 - ボクシングされた基本データ型を使用すべき時とは
    - コレクション内の要素・キー・値
    - パラメータされた型の型パラメータ

## 項目50 他の型が適切な場所では、文字列を避ける

 - 文字列を使うのは、データが本質的に本当に文字である場合だけ
 - 適切な値型があるなら、基本データ型であろうがオブジェクト参照であろうがそれを使う
    - なければ適切な値型を書くべき

## 項目51 文字列結合のパフォーマンスに用心する

 - 文字列結合には演算子でなく `StringBuilder` の `append` メソッドを使用する
 - 代わりの方法としては、文字配列を使用するか、1つずつ処理する

## 項目52 インターフェースでオブジェクトを参照する

 - 適切なインターフェースが存在するなら、すべてインターフェース型を使用して宣言すべき
 - インターフェースがなければ、クラス改装中で必要な機能を提供しているもっとも上位のクラスを使用する

## 項目53 リフレクションよりインターフェースを選ぶ

 - リフレクションの短所
    - 型検査の恩恵をすべて失う
    - コードがぎこちなく、冗長になる
    - パフォーマンスが悪くなる
 - できるだけ、オブジェクトのインスタンス化( `Class.newInstance` )のみの使用にとどめる
 - できるだけ、インターフェースやスーパークラスを使用してオブジェクトにアクセスする

## 項目54 ネイティブメソッドを注意して使用する

 - ネイティブコードは可能な限り使わないようにする

## 項目55 注意して最適化する

 - 速いプログラムより良いプログラムを書く努力をする
 - パフォーマンスは結果として得られる
 - 最適化の規則（M. A. Jackson）
    - 最適化するな
    - (専門家のみに対して)まだ最適化するな

## 項目56 一般的に受け入れられている命名規約を守る

 - パッケージ名
    - 組織のインターネットドメインで始まる
    - 階層は8文字以下であるべき
    - 意味を持った省略形は推奨される
    - `com.google.inject` , `org.joda.time.format`
 - 型パラメータ
    - 任意の型: `T`
       - 一連の任意の型: `T` `U` `V` / `T1` `T2` `T3`
    - コレクションの要素型: `E`
    - マップのキーと値: `K` `V`
    - 例外: `X`
 - インターフェース
    - `able` や `ible` で終わる形容詞でも命名される
    - `Runnable` `Iteratable` `Accessible`
 - booleanを返すメソッド名
    - `is` 、まれに `has` で始まる
 - 機能や属性を返すメソッド
    - 名詞か名詞句、あるいはgetで始まる動詞句
    - Java Beansならばgetをつける
    - setterが存在するならgetをつける
    - 名詞の方が読みやすい場合もある `car.speed()`
 - 型変換のメソッド
    - toType: `toString` `toArray`
    - asType: `asList`
    - typeValue: `intValue`
    - staticファクトリーメソッド: `valueOf` `of` `getInstance` `newInstance`
       - getType, newType (項目1)

# 第9章 例外

## 項目57 例外的状態にだけ例外を使用する

 - 例外的条件に対してのみ例外を使用する
 - 例外の使用を強制するようなAPIを書かない
 - 状態検査メソッドか、意味のある戻り値で判別させる
    - 状態検査の方が不適切な使用を見つけやすい `Iterator#hasNext`
    - 非同期で状態遷移する可能性がある場合意味のある戻り値が必要になるかもしれない
       - パフォーマンスの問題など

## 項目58 回復可能な状態にはチェックされる例外を、プログラミングエラーには実行時例外を使用する

 - 呼び出し側が適切に回復できる状況ならチェックされる例外を使用する
 - プログラミングエラーを示すには実行時例外を使用する
    - 殆どの場合事前条件違反
 - ErrorはJVMが使用するのに予約されているという慣例がある
    - 実装する非チェック例外はRuntimeExceptionをサブクラス化すべき
 - 例外は任意のメソッドを定義できるオブジェクトである
    - 例外をスローさせた状態に関する追加情報を提供するようにする

## 項目59 チェックされる例外を不必要に使用するのを避ける

 - チェックされる例外は下記の場合を満たす場合にのみ使用が正当化される
    - APIの適切な使用では例外状態を防ぐことができない
    - API使用者が例外捕捉時に何らかの有用な処理ができる
 - 状態検査メソッドによるリファクタリングが適切かもしれない(項目57)

## 項目60 標準例外を使用する

 - 既存の例外を再利用する利点
    - APIを学んで使用するのが容易
    - 見慣れているので、プログラムが読みやすくなる
    - 例外クラスが少ないことで使用メモリ量とクラスのロード時間の削減
 - IllegalArgumentException: パラメータ値が不適切
 - IllegalStateException: メソッド呼び出しに対してオブジェクト状態が不正
 - NullPointerException: パラメータ値が禁止されているnull
 - IndexOutOfBoundsException: インデックスパラメータ値が範囲外
 - ConcurrentModificationException: 禁止されているオブジェクトの並行した変更を検出
 - UnsupportedOperationException: オブジェクトがメソッドをサポートしていない

## 項目61 抽象概念に適した例外をスローする

 - 抽象概念の異なる例外をそのまま上位に伝搬させない
 - 例外翻訳
    - 上位レイヤは下位レベルの例外をキャッチして上位レイヤの抽象概念で説明可能な例外をスロー
 - 例外連鎖
    - 下位レベルの例外を上位レベルのメソッドから取り出せるようにする `Throwable#getCause`
    - コンストラクタに原因を渡す
 - 例外翻訳は乱用するべきではない
    - 上位レベルが下位レベルのメソッド成功を検査して保証するようにする
    - それができないなら上位レベルが処理して呼び出し元から隔離する

## 項目62 各メソッドがスローするすべての例外を文書化する

 - 非チェック例外も含め、スルーする可能性のあるすべての例外を文書化する
 - 具象メソッドだけでなく抽象メソッドに対しても文書化する
 - throws節は個別の例外で宣言する
 - チェックされない例外にthrows節を提供しない
 - Javadocの `@throws` タグを利用する

## 項目63 詳細メッセージにエラー記録情報を含める

 - 例外の文字列表現は、例外の原因となったすべてのパラメータとフィールドの値を含んでいるべき
 - 文字列表現に適切なエラー記録情報を含めるコンストラクタを用意するのも方法の一つ
 - エラー記録情報に対するアクセッサーを提供することも検討すべき(項目58)