package red.sukun1899.effectivejava.second;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import red.sukun1899.effectivejava.second.item2.NutritionFacts;
import red.sukun1899.effectivejava.second.item26.Stack;
import red.sukun1899.effectivejava.second.item29.Favoirtes;
import red.sukun1899.effectivejava.second.item32.Text;
import red.sukun1899.effectivejava.second.item33.Herb;

import static red.sukun1899.effectivejava.second.item32.Text.*;

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

  private void doItem32() {
    Text text = new Text();
    text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
  }

  private void doItem33() {
    Herb[] garden = {
        new Herb("hoge", Herb.Type.ANNUAL),
        new Herb("huga", Herb.Type.BIENNIAL),
        new Herb("piyo", Herb.Type.PERENNIAL)
    };

    // データをenum と関連付けるために EnumMap を使用する
    Map<Herb.Type, Set<Herb>> herbsByType = new EnumMap<>(Herb.Type.class);
    for (Herb.Type type : Herb.Type.values()) {
      herbsByType.put(type, new HashSet<>());
    }
    for (Herb herb : garden) {
      herbsByType.get(herb.getType()).add(herb);
    }
  }
}
