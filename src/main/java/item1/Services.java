package item1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

// サービス登録とアクセスのためのインスタンス化不可能クラス
public class Services {

  // インスタンス化を抑制
  private Services() {
  }

  // サービス名をサービスと対応付ける
  private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();
  public static final String DEFAULT_PROVIDER_NAME = "<def>";

  // プロバイダー登録API
  public static void registerDefaultProvider(Provider provider) {
    registerProvider(DEFAULT_PROVIDER_NAME, provider);
  }
  public static void registerProvider(String name, Provider provider) {
    providers.put(name, provider);
  }

  // サービスアクセスAPI
  public static Service newInstance() {
    return newInstance(DEFAULT_PROVIDER_NAME);
  }
  public static Service newInstance(String name) {
    Provider provider = providers.get(name);
    if (provider == null) {
      throw new IllegalArgumentException("No provider registered with name: " + name);
    }
    return provider.newService();
  }
}
