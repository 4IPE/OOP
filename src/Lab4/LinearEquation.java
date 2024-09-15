package Lab4;

import java.io.Serial;
import java.util.Scanner;


class LinearEquation extends Equation {
    @Serial
    private static final long serialVersionUID = 1L;
    private double a, b;

    public LinearEquation() {
        this.a = 0;
        this.b = 0;
    }

    public LinearEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void setCoefficients(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double[] getCoefficients() {
        return new double[]{a, b};
    }

    @Override
    public void solve() {
        if (a == 0) {
            if (b == 0) {
                System.out.println("Уравнение имеет бесконечно много решений.");
            } else {
                System.out.println("Уравнение не имеет решений.");
            }
        } else {
            double x = -b / a;
            System.out.println("Решение линейного уравнения: x = " + x);
        }
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите коэффициенты для линейного уравнения (Ax + B = 0):");
        System.out.print("A: ");
        a = scanner.nextDouble();
        System.out.print("B: ");
        b = scanner.nextDouble();
    }

    @Override
    public void display() {
        System.out.println("Линейное уравнение: " + a + "x + " + b + " = 0");
    }
}
