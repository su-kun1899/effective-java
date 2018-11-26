package red.sukun1899.effectivejava.second.item18;

import java.util.Map;

// 骨格実装
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

  // 基本操作
  public abstract K getKey();

  public abstract V getValue();

  // 変更可能なマップでのエントリーは、このメソッドをオーバライドしなければならない
  public V setValue(V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Map.Entry)) {
      return false;
    }
    Map.Entry<?, ?> arg = (Map.Entry<?, ?>) obj;
    return equals(getKey(), arg.getKey()) &&
        equals(getValue(), arg.getValue());
  }

  private static boolean equals(Object obj1, Object obj2) {
    return obj1 == null ? obj2 == null : obj1.equals(obj2);
  }

  // Map.Entry.hashCode の一般契約を実装
  @Override
  public int hashCode() {
    return hashCode(getKey()) ^ hashCode(getValue());
  }

  private static int hashCode(Object obj) {
    return obj == null ? 0 : obj.hashCode();
  }
}
