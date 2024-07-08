import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SubsecventaComunaDeLungimeMaxima {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL6/input1.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            // Citirea lungimii si a primului sir de numere
            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Citirea lungimii si a celui de-al doilea sir de numere
            int m = scanner.nextInt();
            int[] b = new int[m];
            for (int i = 0; i < m; i++) {
                b[i] = scanner.nextInt();
            }

            // Apelarea functiei pentru gasirea lungimii celei mai lungi subsecvente comune si tiparirea rezultatului
            int[][] lcs = LCS(a, n, b, m);
            printLCS(a, n, b, m, lcs, writer);

            // Inchiderea resurselor de citire si scriere
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Functia pentru gasirea matricei LCS
    private static int[][] LCS(int[] a, int n, int[] b, int m) {
        // Initializarea matricei LCS
        int[][] lcs = new int[n][m];

        // Completati matricea in mod eficient
        lcs[0][0] = (a[0] == b[0]) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            lcs[i][0] = (a[i] == b[0]) ? 1 : lcs[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            lcs[0][j] = (a[0] == b[j]) ? 1 : lcs[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }

        return lcs;
    }

    // Functia pentru tiparirea subsecventei comune
    private static void printLCS(int[] a, int n, int[] b, int m, int[][] lcs, PrintWriter writer) {
        // Calculul si tiparirea lungimii subsecventei comune
        writer.println("Lungimea maxima: " + lcs[n - 1][m - 1]);
        writer.print("Subsecventa este: ");
        
        // Initializarea indicilor pentru a parcurge matricea
        int i = n - 1, j = m - 1;
        
        // Parcurgerea matricei pentru a reconstrui subsecventa comuna
        while (i >= 0 && j >= 0) {
            if (a[i] == b[j]) {
                // Daca elementele sunt egale, sunt parte a subsecventei comune
                writer.print(a[i] + " ");
                i--;
                j--;
            } else if (i - 1 >= 0 && lcs[i][j] == lcs[i - 1][j]) {
                // Altfel, se trece la celalalt sir de numere, in functie de valoarea din matrice
                i--;
            } else {
                j--;
            }
        }
    }
}
