import java.util.ArrayList;
import java.util.List;

public class EquationDemo {
    public static void main(String[] args) {
        List<Equation> equations = new ArrayList<>();

        LinearEquation linearEq = new LinearEquation(2, -3);
        QuadraticEquation quadraticEq = new QuadraticEquation(1, -3, 2);

        equations.add(linearEq);
        equations.add(quadraticEq);

        for (Equation eq : equations) {
            eq.display();
            eq.solve();
            System.out.println();
        }
    }
}
