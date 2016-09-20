# effective-java
study effective java

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
