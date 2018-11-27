package red.sukun1899.effectivejava.second.item30;

/**
 * 戦略enum パターン
 */
public enum PayrollDay {
  MONDAY(PayType.WEEKDAY),
  TUESDAY(PayType.WEEKDAY),
  WEDNESDAY(PayType.WEEKDAY),
  THURSDAY(PayType.WEEKDAY),
  FRIDAY(PayType.WEEKDAY),
  SATURDAY(PayType.WEEKEND),
  SUNDAY(PayType.WEEKEND);
  private final PayType payType;

  PayrollDay(PayType payType) {
    this.payType = payType;
  }

  public double pay(double hoursWorked, double payRate) {
    return payType.pay(hoursWorked, payRate);
  }

  // 戦略enum 型
  private enum PayType {
    WEEKDAY {
      @Override
      double overtimePay(double hours, double payRate) {
        return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
      }
    },
    WEEKEND {
      @Override
      double overtimePay(double hours, double payRate) {
        return hours * payRate / 2;
      }
    };
    private static final int HOURS_PER_SHIFT = 8;

    abstract double overtimePay(double hours, double payRate);

    double pay(double hoursWorked, double payRate) {
      double basePay = hoursWorked * payRate;
      return basePay + overtimePay(hoursWorked, payRate);
    }
  }
}