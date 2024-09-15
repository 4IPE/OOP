import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EquationSerializationDemo {
    public static void main(String[] args) {
        String filename = "equations.txt";

        List<Equation> equations = new ArrayList<>();
        equations.add(new LinearEquation(3, 5));
        equations.add(new QuadraticEquation(1, -4, 3));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Equation eq : equations) {
                oos.writeObject(eq);
            }
            equations.forEach(System.out::println);
            System.out.println("Уравнения были сериализованы в файл.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Equation) {
                    Equation eq = (Equation) obj;
                    eq.display();
                    eq.solve();
                    System.out.println();
                }
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
