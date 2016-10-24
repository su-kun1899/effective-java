package item32;

public enum Ensemble {
  SOLO(1),
  DUET(2),
  TRIO(3),
  QUALTET(4),
  QUINTET(5),
  SEXTET(6),
  SEPTET(7),
  OCTET(8),
  NONET(9),
  DECTET(10);

  private final int numberOfMusicians;

  Ensemble(int size) {
    this.numberOfMusicians = size;
  }

  public int numberOfMusicians() {
    return numberOfMusicians;
  }
}
