package item2;

// ビルダーパターン
public class NutritionFacts {
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  public int getServingSize() {
    return servingSize;
  }

  public int getServings() {
    return servings;
  }

  public int getCalories() {
    return calories;
  }

  public int getFat() {
    return fat;
  }

  public int getSodium() {
    return sodium;
  }

  public int getCarbohydrate() {
    return carbohydrate;
  }

  public static class Builder implements item2.Builder<NutritionFacts> {
    // 必須パラメータ
    private final int servingSize;
    private final int servings;

    // 任意パラメータ
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Builder fat(int val) {
      calories = val;
      return this;
    }

    public Builder sodium(int val) {
      calories = val;
      return this;
    }

    public Builder carbohydrate(int val) {
      calories = val;
      return this;
    }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    this.servingSize = builder.servingSize;
    this.servings = builder.servings;
    this.calories = builder.calories;
    this.fat = builder.fat;
    this.sodium = builder.sodium;
    this.carbohydrate = builder.carbohydrate;
  }
}
