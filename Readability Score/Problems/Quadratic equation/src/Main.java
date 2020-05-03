import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double delta = Math.pow(b, 2) - 4 * a * c;
        double deltaRoot = Math.sqrt(delta);

        double root1 = (-b - deltaRoot) / (2 * a);
        double root2 = (-b + deltaRoot) / (2 * a);

        if (root1 < root2) {
            System.out.printf("%f %f", root1, root2);
        } else {
            System.out.printf("%f %f", root2, root1);
        }

    }
}