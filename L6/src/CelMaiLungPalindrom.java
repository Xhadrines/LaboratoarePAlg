import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CelMaiLungPalindrom {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL6/input2.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            int n = scanner.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Apelarea functiei pentru gasirea celei mai lungi subsecvente palindromice
            celMaiLungPalindrom(a, n, writer);

            // Inchiderea fluxurilor de date
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // Tratarea cazului in care fisierul de intrare nu poate fi gasit
            e.printStackTrace();
        }
    }

    // Functia pentru gasirea celei mai lungi subsecvente palindromice
    private static void celMaiLungPalindrom(int[] a, int n, PrintWriter writer) {
        // Crearea unei secvente inverse pentru a folosi algoritmul de gasire a celei mai lungi subsecvente comune
        int[] aInversat = new int[n];
        for (int i = 0; i < n; i++) {
            aInversat[i] = a[n - 1 - i];
        }

        // Apelarea functiei pentru gasirea celei mai lungi subsecvente comune
        int[][] lcs = LCS(a, n, aInversat, n);
        // Afisarea subsecventei palindromice
        printLCS(a, n, aInversat, n, lcs, writer);
    }

    // Functia pentru gasirea celei mai lungi subsecvente comune
    private static int[][] LCS(int[] a, int n, int[] b, int m) {
        int[][] lcs = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    lcs[i][j] = 0;
                } else if (a[i - 1] == b[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }

        return lcs;
    }

    // Functia pentru afisarea subsecventei palindromice
    private static void printLCS(int[] a, int n, int[] b, int m, int[][] lcs, PrintWriter writer) {
        int length = lcs[n][m];
        writer.println("Lungimea maxima a subsecventei palindromice: " + length);
        writer.print("Subsecventa este: ");

        int[] subsecventa = new int[length];
        int i = n, j = m;
        int index = length - 1;
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                subsecventa[index] = a[i - 1];
                i--;
                j--;
                index--;
            } else if (lcs[i - 1][j] > lcs[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        for (int element : subsecventa) {
            writer.print(element + " ");
        }
    }
}
