import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Excursii1 {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare si deschiderea fisierului de iesire
            Scanner scanner = new Scanner(new File("InputL6/input3.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            int T = scanner.nextInt();
            for (int t = 0; t < T; t++) {
                int n = scanner.nextInt();

                // Citirea rutelor de excursie pentru fiecare oras
                String[][] cities = new String[n][];
                scanner.nextLine(); // Consumam newline
                for (int i = 0; i < n; i++) {
                    cities[i] = scanner.nextLine().split(" ");
                }

                // Determinarea numarului maxim de orase comune intre rutele de excursie
                int maxCommonCities = getMaxCommonCities(cities, n);
                writer.println(maxCommonCities);
            }

            // Inchiderea fluxurilor de date
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Functia pentru determinarea numarului maxim de orase comune intre rutele de excursie
    private static int getMaxCommonCities(String[][] cities, int n) {
        int maxCommonCities = 0;

        // Iteram prin toate perechile de rute de excursie si calculam numarul maxim de orase comune
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int commonCities = getLCSLength(cities[i], cities[j]);
                maxCommonCities = Math.max(maxCommonCities, commonCities);
            }
        }

        return maxCommonCities;
    }

    // Functia pentru calcularea lungimii celei mai lungi subsecvente comune intre doua rute de excursie
    private static int getLCSLength(String[] A, String[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];

        // Calculam lungimea celei mai lungi subsecvente comune folosind un algoritm de programare dinamica
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1].equals(B[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
