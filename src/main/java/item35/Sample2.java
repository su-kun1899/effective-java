package item35;

/**
 * パラメータを持つアノテーションを含むプログラム
 */
public class Sample2 {
  @ExceptionTest(ArithmeticException.class)
  public static void method1() {
    //テストは成功すべき
    int i = 0;
    i = i / i;
  }

  @ExceptionTest(ArithmeticException.class)
  public static void method2() {
    //テストは失敗すべき
    int[] a = new int[0];
    int i = a[1];
  }

  @ExceptionTest(ArithmeticException.class)
  public static void method5() {
    // テストは失敗すべき
  }
}
