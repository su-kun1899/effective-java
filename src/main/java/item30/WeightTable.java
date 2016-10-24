package item30;

public class WeightTable {

  // 地球上での重さを受け取り、全ての惑星上でのその物体の重さを表示する
  public static void showWeight(double earthWeight) {
    double mass = earthWeight / Planet.EARTH.surfaceGravity();
    for (Planet planet : Planet.values()) {
      System.out.printf("Weight on %s is %f%n", planet, planet.surfaceWeight(mass));
    }
  }
}
