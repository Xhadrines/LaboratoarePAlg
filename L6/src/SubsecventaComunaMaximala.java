import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SubsecventaComunaMaximala {
    public static void main(String[] args) {
        try {
            // Deschiderea fisierului de intrare si a celui de iesire
            Scanner scanner = new Scanner(new File("InputL6/input4.txt"));
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

            // Apelarea functiei pentru gasirea celei mai lungi subsecvente comune si tiparirea rezultatului
            int[] lcs = getLCS(a, n, b, m);
            writer.println(lcs.length);
            for (int num : lcs) {
                writer.print(num + " ");
            }

            // Inchiderea resurselor de citire si scriere
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Functie pentru a gasi subsecventa comuna maximala si a o returna
    private static int[] getLCS(int[] a, int n, int[] b, int m) {
        // Initializarea matricei pentru programarea dinamica
        int[][] dp = new int[n + 1][m + 1];

        // Calculul matricei dp care retine lungimile subsecventelor comune
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruirea subsecventei comune maximala pe baza matricei dp
        int len = dp[n][m];
        int[] lcs = new int[len];
        int index = len - 1;
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                lcs[index--] = a[i - 1];
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs;
    }
}
