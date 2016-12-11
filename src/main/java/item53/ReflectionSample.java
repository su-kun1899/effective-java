package item53;

import java.util.Arrays;
import java.util.Set;

// インターフェースアクセスでのリフレクションによるインスタンス化
public class ReflectionSample {

  public static void execute(String className) {
    // クラス名をClassオブジェクトへ変換する
    Class<?> clazz = null;
    try {
      clazz = Class.forName(className);
    } catch (ClassNotFoundException e) {
      System.err.println("Class not found.");
      // コマンドラインのユーティリティを除き利用は適切でない
      System.exit(1);
    }

    // クラスのインスタンス化
    Set<String> set = null;
    try {
      set = (Set<String>) clazz.newInstance();
    } catch (InstantiationException e) {
      System.err.println("Class not instantiable.");
      System.exit(1);
    } catch (IllegalAccessException e) {
      System.err.println("Class not Accessible.");
      System.exit(1);
    }

    // セットを使用する
    set.addAll(Arrays.asList("hoge", "fuga", "piyo"));
    System.out.println(set);
  }
}
