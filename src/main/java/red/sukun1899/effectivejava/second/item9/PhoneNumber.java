package red.sukun1899.effectivejava.second.item9;

public class PhoneNumber {
  private final short areaCode;
  private final short prefix;
  private final short lineNumber;

  public PhoneNumber(int areaCode, int prefix, int lineNumber) {
    rangeCheck(areaCode, 999, "area code");
    rangeCheck(prefix, 999, "prefix");
    rangeCheck(lineNumber, 999, "lineNumber");

    this.areaCode = (short) areaCode;
    this.prefix = (short) prefix;
    this.lineNumber = (short) lineNumber;
  }

  private static void rangeCheck(int arg, int max, String name) {
    if (arg < 0 || arg > max) {
      throw new IllegalArgumentException(name + ": " + arg);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof PhoneNumber)) {
      return false;
    }

    PhoneNumber phoneNumber = (PhoneNumber) obj;
    return phoneNumber.lineNumber == lineNumber
        && phoneNumber.prefix == prefix
        && phoneNumber.areaCode == areaCode;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + areaCode;
    result = 31 * result + prefix;
    result = 31 * result + lineNumber;

    return result;
  }
}
