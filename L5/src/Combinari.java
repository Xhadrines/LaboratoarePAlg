import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Combinari {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("InputL5/input1.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            while (scanner.hasNextInt()) {
                // Citeste n si k din fisier
                int n = scanner.nextInt();
                int k = scanner.nextInt();

                // Calculeaza combinarile si le scrie in fisierul de iesire
                long combinari = combinari(n, k);
                writer.println(combinari);
            }

            scanner.close();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afiseaza urmele de eroare in caz de fisierul de intrare nu este gasit
        }
    }

    // Functia pentru calcularea factorialului unui numar
    public static long Factorial(int n) {
        long p = 1;
        for (int i = 1; i <= n; i++) {
            p *= i;
        }
        return p;
    }

    // Functia pentru calcularea combinarilor folosind factorialul
    public static long combinari(int n, int k) {
        return Factorial(n) / (Factorial(k) * Factorial(n - k));
        // return combinari2(n, k);
        // return combinari3(n, k);
        // return combinari4(n, k);
    }

    // Functia pentru calcularea combinarilor prin metoda recursiva
    public static long combinari2(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        return combinari2(n - 1, k) + combinari2(n - 1, k - 1);
    }

    // Functia pentru calcularea combinarilor folosind un tabel de programare dinamica
    public static long combinari3(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        if (k == n - 1) {
            return n;
        }

        // Initializarea unui tabel pentru stocarea combinarilor
        long[][] c = new long[n + 1][n + 1];
        c[0][0] = 1;
        c[1][0] = 1;
        c[1][1] = 1;

        // Calcularea combinarilor
        for (int i = 2; i <= n; i++) {
            c[i][0] = 1;
            c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }

        return c[n][k];
    }

    // Functia pentru calcularea combinarilor folosind doua siruri de numere
    public static long combinari4(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        if (k == n - 1) {
            return n;
        }

        int[] ant = new int[k + 1];
        int[] curent = new int[k + 1];
        ant[0] = 1;
        ant[1] = 1;

        for (int i = 2; i <= n; i++) {
            curent[0] = 1;
            if (i <= k) {
                curent[i] = 1;
            }
            for (int j = 1; j <= Math.min(i - 1, k); j++) {
                curent[j] = ant[j] + ant[j - 1];
            }

            for (int j = 1; j <= k; j++) {
                ant[j] = curent[j];
            }
        }

        return curent[k];
    }
}
