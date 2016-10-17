package item27;

import java.util.HashSet;
import java.util.Set;

public class Sample {
  //ジェネリックメソッド
  public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
    Set<E> result = new HashSet<E>(s1);
    result.addAll(s2);

    return result;
  }
}
