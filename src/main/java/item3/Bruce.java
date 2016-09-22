package item3;

// staticファクトリーメソッドによるシングルトン
public class Bruce {
  private static final Bruce INSTANCE = new Bruce();

  private Bruce() {
  }

  public static Bruce getInstance() {
    return INSTANCE;
  }

  public void bornToRun() {
    // do something
  }
}
