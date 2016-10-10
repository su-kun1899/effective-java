package item20;

abstract class Figure {
  abstract double area();
}

// 円のサブタイプ
class Circle extends Figure {

  final double radius;

  Circle(double radius) {
    this.radius = radius;
  }

  double area() {
    return Math.PI * (radius * radius);
  }
}

// 長方形のサブタイプ
class Rectangle extends Figure {

  final double length;
  final double width;

  Rectangle(double length, double width) {
    this.length = length;
    this.width = width;
  }

  double area() {
    return length * width;
  }
}

// サブタイプの階層化（正方形）
class Square extends Rectangle {
  Square(double length, double width) {
    super(length, width);
  }
}