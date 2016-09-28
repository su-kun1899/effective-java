# effective-java
study effective java

# オブジェクトの生成と消滅

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
