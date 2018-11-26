package red.sukun1899.effectivejava.second.item25;

import java.util.ArrayList;
import java.util.List;

class Sample {

  static <E> E reduce(List<E> list, Function<E> f, E initVal) {
    List<E> snapshot;
    synchronized (list) {
      snapshot = new ArrayList<E>(list);
    }
    E result = initVal;
    for (E e : snapshot) {
      result = f.apply(result, e);
    }

    return result;
  }
}
