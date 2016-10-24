package item30;

import java.util.HashMap;
import java.util.Map;

/**
 * 定数固有クラス本体と定数固有データを持つenum 型
 */
public enum Operation {
  PLUS("+") {
    double apply(double x, double y) {
      return x + y;
    }
  },
  MINUS("-") {
    double apply(double x, double y) {
      return x - y;
    }
  },
  TIMES("*") {
    double apply(double x, double y) {
      return x * y;
    }
  },
  DIVIDE("/") {
    double apply(double x, double y) {
      return x / y;
    }
  };
  private final String symbol;

  Operation(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }

  // enum 型にfromString メソッドを実装する
  private static final Map<String, Operation> stringToEnum = new HashMap<>();

  static {
    for (Operation operation : values()) {
      stringToEnum.put(operation.toString(), operation);
    }
  }

  // 文字列に対するOperation を返す。文字列が不正ならnull を返す
  public static Operation fromString(String symbol) {
    return stringToEnum.get(symbol);
  }

  abstract double apply(double x, double y);
}
