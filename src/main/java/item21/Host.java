package item21;

import java.io.Serializable;
import java.util.Comparator;

// 具象戦略を提供する
class Host {
  private static class StringLengthComparator implements Comparator<String>, Serializable {
    public int compare(String s1, String s2) {
      return s1.length() - s2.length();
    }
  }

  // 返されたコンパレータはシリアライズ可能
  public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StringLengthComparator();
}
