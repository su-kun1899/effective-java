package red.sukun1899.effectivejava.second.item5;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Person {

  private Date birthDate;

  /**
   * ベビーブームの開始と終わりの日付
   */
  private static final Date BOOM_START;
  private static final Date BOOM_END;

  // コストの高いオブジェクトの生成
  static {
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
    BOOM_START = gmtCal.getTime();
    gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
    BOOM_END = gmtCal.getTime();
  }

  public boolean isBabyBoomer() {
    return birthDate.compareTo(BOOM_START) >= 0 &&
        birthDate.compareTo(BOOM_END) < 0;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
}
