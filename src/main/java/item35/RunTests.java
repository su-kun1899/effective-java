package item35;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * マーカーアノテーションを処理するプログラム
 */
public class RunTests {

  public static void execute(String[] args) throws ClassNotFoundException {
    int tests = 0;
    int passed = 0;
    Class<?> testClass = Class.forName(args[0]);
    for (Method method : testClass.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Test.class)) {
        tests++;
        try {
          method.invoke(null);
          passed++;
        } catch (InvocationTargetException e) {
          Throwable cause = e.getCause();
          System.out.println(method + " failed: " + cause);

          Class<? extends Exception> execType
              = method.getAnnotation(ExceptionTest.class).value();
          if (execType.isInstance(cause)) {
            passed++;
          }
        } catch (Exception e) {
          System.out.println("INVALID @Test: " + method);

        }
      }
    }
    System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
  }
}
