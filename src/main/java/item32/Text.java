package item32;

import java.util.Set;

public class Text {
  public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

  // どのようなSet でも渡せるが、EnumSet が明らかに最善
  public void applyStyles(Set<Style> styles) {
    // do something..
  }
}
