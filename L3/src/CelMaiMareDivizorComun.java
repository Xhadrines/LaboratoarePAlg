import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CelMaiMareDivizorComun {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("InputL3/input6.txt"))) {
            int n = scanner.nextInt();

            int[] set = new int[n];

            // Citirea numerelor din fisier si stocarea lor intr-un array
            for (int i = 0; i < n; i++) {
                set[i] = scanner.nextInt();
            }

            // Calcularea CMMD pentru setul de numere
            int gcd = calculateGCD(set);

            // Scrierea rezultatului in fisierul de iesire
            try (PrintWriter writer = new PrintWriter(new File("output.txt"))) {
                writer.println(gcd);
            } catch (FileNotFoundException e) {
                System.out.println("Fisierul de iesire nu a putut fi creat: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de intrare nu a fost gasit: " + e.getMessage());
        }
    }

    // Functie pentru calcularea CMMD a unui set de numere
    public static int calculateGCD(int[] arr) {
        if (arr.length == 0) {
            return 0; // Daca setul este gol, returneaza 0
        }

        int gcd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = calculateGCD(gcd, arr[i]); // Calculeaza CMMD pentru fiecare pereche de numere
        }
        return gcd;
    }

    // Functie pentru calcularea CMMD a doua numere folosind algoritmul lui Euclid
    public static int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a; // Returneaza CMMD
    }
}
