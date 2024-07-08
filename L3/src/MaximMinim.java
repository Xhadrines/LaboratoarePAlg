import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MaximMinim {
    public static void main(String[] args) {
        try {
            // Citim numerele din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL3/input4.txt"));
            int n = scanner.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Deschidem fisierul de iesire pentru scriere
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            // Calculam valoarea maxima si minima
            int maxValue = getMaxValue(a);
            int minValue = getMinValue(a);

            // Scriem rezultatele in fisierul de iesire
            writer.println("Valoarea maxima din multime este: " + maxValue);
            writer.println("Valoarea minima din multime este: " + minValue);

            // Inchidem scanner-ul si writer-ul
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // Afisam un mesaj de eroare in cazul in care fisierul de intrare nu este gasit
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }

    // Metoda pentru a gasi valoarea maxima dintr-o lista de numere
    public static int getMaxValue(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int num : a) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // Metoda pentru a gasi valoarea minima dintr-o lista de numere
    public static int getMinValue(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int num : a) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}
