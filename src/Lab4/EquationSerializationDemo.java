package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EquationSerializationDemo {
    public static void main(String[] args) {
        String filename = "equations.dat";

        // Создаем список уравнений
        List<Equation> equations = new ArrayList<>();
        equations.add(new LinearEquation(3, 5));
        equations.add(new QuadraticEquation(1, -4, 3));

        // Сериализация объектов в файл
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Equation eq : equations) {
                oos.writeObject(eq);
            }
            System.out.println("Уравнения были сериализованы в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десериализация объектов из файла
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
            // Ожидаемое завершение чтения файла
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
