class Shape {
    public double area() {
        return 0;
    }
}

class Triangle extends Shape {
    double height;
    double base;

    @java.lang.Override
    public double area() {
        return height * base / 2;
    }
}

class Circle extends Shape {
    double radius;

    @java.lang.Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}

class Square extends Shape {
    double side;

    @java.lang.Override
    public double area() {
        return Math.pow(side, 2);
    }
}

class Rectangle extends Shape {
    double width;
    double height;

    @java.lang.Override
    public double area() {
        return width * height;
    }
}