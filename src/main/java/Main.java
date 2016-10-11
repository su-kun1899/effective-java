import java.util.Arrays;
import java.util.Comparator;

import item2.NutritionFacts;

public class Main {

  public static void main(String[] args) {

  }

  public void doItem2() {
    NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
        .calories(100)
        .sodium(35)
        .carbohydrate(27)
        .build();
  }

  private void doItem21() {
    String[] strings = {"hoge", "huga", "piyo"};
    Arrays.sort(strings, new Comparator<String>() {
      public int compare(String s1, String s2) {
        return s1.length() - s2.length();
      }
    });
  }
}
