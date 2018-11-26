package red.sukun1899.effectivejava.second.item15;

/**
 * 複素数を表すクラス
 *
 * Created by su-kun1899 on 2016/10/07.
 */
public class Complex {

  private final double realPart;
  private final double imaginaryPart;

  // コンストラクタ代わりにstatic ファクトリーメソッドを持つ
  private Complex(double realPart, double imaginaryPart) {
    this.realPart = realPart;
    this.imaginaryPart = imaginaryPart;
  }

  public static Complex valueOf(double realPart, double imaginaryPart){
    return new Complex(realPart, imaginaryPart);
  }

  // setter は提供しない
  public double getRealPart() {
    return realPart;
  }

  public double getImaginaryPart() {
    return imaginaryPart;
  }

  public Complex add(Complex complex) {
    return new Complex(
        getRealPart() + complex.getRealPart(),
        getImaginaryPart() + complex.getImaginaryPart()
    );
  }

  public Complex substract(Complex complex) {
    return new Complex(
        getRealPart() - complex.getRealPart(),
        getImaginaryPart() - complex.getImaginaryPart()
    );
  }

  public Complex multiply(Complex complex) {
    return new Complex(
        getRealPart() * complex.getRealPart() - getImaginaryPart() * complex.getImaginaryPart(),
        getRealPart() * complex.getImaginaryPart() + getRealPart() * complex.getRealPart()
    );
  }

  public Complex divide(Complex complex) {
    double tmp = complex.getRealPart() * complex.getRealPart() + complex.getImaginaryPart() * complex.getImaginaryPart();
    return new Complex(
        (getRealPart() * complex.getRealPart() + getImaginaryPart() * complex.getImaginaryPart()) / tmp,
        (getImaginaryPart() * complex.getRealPart() - getRealPart() * complex.getImaginaryPart()) / tmp
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Complex)) {
      return false;
    }
    Complex complex = (Complex) obj;

    return Double.compare(getRealPart(), complex.getRealPart()) == 0
        && Double.compare(getImaginaryPart(), complex.getImaginaryPart()) == 0;
  }

  @Override
  public int hashCode() {
    int result = 17 + hashDouble(getRealPart());
    result = 31 * result + hashDouble(getImaginaryPart());
    return result;
  }

  private static int hashDouble(double val) {
    long longBits = Double.doubleToLongBits(val);
    return (int) (longBits ^ (longBits >>> 32));
  }

  @Override
  public String toString() {
    return "(" + getRealPart() + " + " + getImaginaryPart() + "i)";
  }
}
