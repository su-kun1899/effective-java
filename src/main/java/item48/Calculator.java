package item48;

import java.math.BigDecimal;

/**
 * ポケットに1ドルあって、
 * 10セント、20セント、30セントといった具合に1ドルまで値段がついているキャンディを
 * 1個ずつ買えるまで買う場合、何個買えてお釣りはいくらか？
 *
 * Created by su-kun1899 on 2016/11/28.
 */
public class Calculator {

  // 不完全 - 金額計算に浮動小数点を使用している
  public void withDouble() {
    double funds = 1.00;
    int itemsBought = 0;

    for (double price = .10; funds >= price; price += .10) {
      funds -= price;
      itemsBought++;
    }

    System.out.println(itemsBought + " items Bought");
    System.out.println("Change: $" + funds);
  }

  // 不便さとコストを気にしないならBigDecimal
  public void withBigDecimal() {
    final BigDecimal TEN_CENTS = new BigDecimal(".10");

    int itemsBought = 0;
    BigDecimal funds = new BigDecimal("1.00");

    for (BigDecimal price = TEN_CENTS; funds.compareTo(price) > +0; price.add(TEN_CENTS)) {
      itemsBought++;
      funds = funds.subtract(price);
    }

    System.out.println(itemsBought + " items Bought");
    System.out.println("Money left over: $" + funds);
  }

  // intあるいはlongを使って自分でどこが小数点かを把握する
  public void withInt() {
    int itemsBought = 0;
    int funds = 100;

    for (int price = 10; funds >= price; price += 10) {
      funds -= price;
      itemsBought++;
    }

    System.out.println(itemsBought + " items Bought");
    System.out.println("Money left over: $" + funds + " cents");
  }
}
