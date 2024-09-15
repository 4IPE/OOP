import java.io.Serial;
import java.util.Scanner;

class QuadraticEquation extends Equation {
    @Serial
    private static final long serialVersionUID = 1L;
    private double a, b, c;

    @Override
    public String toString() {
        return "QuadraticEquation{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    public QuadraticEquation() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setCoefficients(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double[] getCoefficients() {
        return new double[]{a, b, c};
    }

    @Override
    public void solve() {
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Решения квадратного уравнения: x1 = " + x1 + ", x2 = " + x2);
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            System.out.println("Решение квадратного уравнения: x = " + x);
        } else {
            System.out.println("Квадратное уравнение не имеет действительных корней.");
        }
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите коэффициенты для квадратного уравнения (Ax^2 + Bx + C = 0):");
        System.out.print("A: ");
        a = scanner.nextDouble();
        System.out.print("B: ");
        b = scanner.nextDouble();
        System.out.print("C: ");
        c = scanner.nextDouble();
    }

    @Override
    public void display() {
        System.out.println("Квадратное уравнение: " + a + "x^2 + " + b + "x + " + c + " = 0");
    }
}
