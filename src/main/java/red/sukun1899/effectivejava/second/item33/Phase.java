package red.sukun1899.effectivejava.second.item33;

import java.util.EnumMap;
import java.util.Map;

/**
 * データと enum の組を関連付けるためにネストした EnumMap を使用
 */
public enum Phase {
  SOLID, LIQUID, GAS,;

  public enum Transition {
    MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
    BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
    SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),;

    private final Phase src;
    private final Phase dst;

    Transition(Phase src, Phase dst) {
      this.src = src;
      this.dst = dst;
    }

    // 相転移マップを初期化
    private static final Map<Phase, Map<Phase, Transition>> map
        = new EnumMap<Phase, Map<Phase, Transition>>(Phase.class);

    static {
      for (Phase phase : Phase.values()) {
        map.put(phase, new EnumMap<>(Phase.class));
      }
      for (Transition transition : Transition.values()) {
        map.get(transition.src).put(transition.dst, transition);
      }
    }

    public static Transition from(Phase src, Phase dest) {
      return map.get(src).get(dest);
    }
  }
}
