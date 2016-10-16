package item26;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {
  private E[] elements;
  private int size;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  // elements 配列はpush(E) からのE インスタンスだけを含む
  // そのことは、型安全性を保証するためには十分であるが、配列の
  // 実行時の方はE[] ではない。常にObject[] である！
  @SuppressWarnings("unchecked")
  public Stack() {
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(E e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public E pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    E result = elements[--size];
    elements[size] = null; // 廃れた参照を取り除く
    return result;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void ensureCapacity() {
    if (elements.length == size) {
      elements = Arrays.copyOf(elements, 2 * size + 1);
    }
  }
}
