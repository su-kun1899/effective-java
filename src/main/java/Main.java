import java.util.Arrays;
import java.util.Comparator;

import item2.NutritionFacts;
import item26.Stack;
import item29.Favoirtes;

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

  // ジェネリックStack を使用する小さなプログラム
  private void doItem26(String[] args) {
    Stack<String> stack = new Stack<String>();
    for (String arg : args) {
      stack.push(arg);
    }
    while (!stack.isEmpty()) {
      System.out.println(stack.pop().toUpperCase());
    }
  }

  private void doItem29(String[] args) {
    Favoirtes favoirtes = new Favoirtes();
    favoirtes.putFavorite(String.class, "Java");
    favoirtes.putFavorite(Integer.class, 11);
    favoirtes.putFavorite(Class.class, Favoirtes.class);
    String favoriteString = favoirtes.getFavorite(String.class);
    int favoriteInteger = favoirtes.getFavorite(Integer.class);
    Class favoriteClass = favoirtes.getFavorite(Class.class);

    System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
  }
}
