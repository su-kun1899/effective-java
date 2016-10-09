package item16;

import java.util.Collection;
import java.util.Set;

/**
 * ラッパークラス（継承の代わりにコンポジションを使用している）
 *
 * Created by su-kun1899 on 2016/10/10.
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

  private int addCount = 0;

  public InstrumentedSet(Set<E> set) {
    super(set);
  }

  @Override
  public boolean add(E e) {
    addCount++;
    return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    addCount += c.size();
    return super.addAll(c);
  }

  public int getAddCount() {
    return addCount;
  }
}
