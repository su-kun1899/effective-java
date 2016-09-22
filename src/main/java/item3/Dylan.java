package item3;

// public finalのフィールドによるシングルトン
public class Dylan {
  public static final Dylan INSTANCE = new Dylan();

  private Dylan() {
  }

  public void likeARollingStone() {
    // do something
  }
}
