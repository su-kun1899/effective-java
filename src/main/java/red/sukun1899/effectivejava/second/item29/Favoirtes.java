package red.sukun1899.effectivejava.second.item29;

import java.util.HashMap;
import java.util.Map;

// 型安全な異種コンテナーパターン
public class Favoirtes {
  private Map<Class<?>, Object> favorites = new HashMap<Class<?>, Object>();

  public <T> void putFavorite(Class<T> type, T instance) {
    if (type == null) {
      throw new NullPointerException("type is null");
    }
    // 動的キャストで実行時型安全性を達成する
    favorites.put(type, type.cast(instance));
  }

  public <T> T getFavorite(Class<T> type) {
    return type.cast(favorites.get(type));
  }
}
