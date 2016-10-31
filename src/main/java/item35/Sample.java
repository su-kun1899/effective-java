package item35;

/**
 * マーカーアノテーションを含むプログラム
 */
public class Sample {
  @Test
  public static void method1() {
    // テストは成功すべき
  }

  public static void method2() {
  }

  @Test
  public static void method3() {
    // テストは失敗すべき
    throw new RuntimeException("Boom");
  }

  public static void method4() {
  }

  @Test
  public void method5() {
    // 不正な使用: staticでないメソッド
  }

  public static void method6() {
  }

  @Test
  public static void method7() {
    // テストは失敗すべき
    throw new RuntimeException("Crash");
  }

  public static void method8() {
  }
}
