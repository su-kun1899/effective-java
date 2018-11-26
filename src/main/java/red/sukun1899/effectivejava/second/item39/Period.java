package red.sukun1899.effectivejava.second.item39;

import java.util.Date;

/**
 * 期間クラス
 * <p>Dateはlongで扱うほうが適切かもしれない
 */
public final class Period {
  private final Date start;
  private final Date end;

  /**
   * @param start 期間の開始
   * @param end 期間の終わりで、開始より前であってはならない
   * @throws IllegalArgumentException startがendの後の場合
   * @throws NullPointerException startかendがnullの場合
   */
  public Period(Date start, Date end) {
    // パラメータの防御的コピーをする
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());

    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException(this.start + " after " + this.end);
    }
  }

  public Date start() {
    // 内部データの防御的コピー
    return new Date(start.getTime());
  }

  public Date end() {
    // 内部データの防御的コピー
    return new Date(end.getTime());
  }
}
