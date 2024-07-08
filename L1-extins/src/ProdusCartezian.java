import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProdusCartezian {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fișierul de intrare
            Scanner scanner = new Scanner(new File("InputL1-extins/input9.txt"));
            int n = scanner.nextInt(); // Numărul de mulțimi
            int[] m = new int[n]; // Vectorul care reține numărul de elemente din fiecare mulțime
            int[][] A = new int[n][]; // Matricea care reține elementele fiecărei mulțimi

            // Citirea elementelor pentru fiecare mulțime
            for (int i = 0; i < n; i++) {
                m[i] = scanner.nextInt(); // Numărul de elemente din mulțimea curentă
                A[i] = new int[m[i]]; // Inițializarea vectorului de elemente pentru mulțimea curentă
                for (int j = 0; j < m[i]; j++) {
                    A[i][j] = scanner.nextInt(); // Citirea fiecărui element al mulțimii
                }
            }

            // Deschiderea fișierului de ieșire pentru scriere
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            // Apelarea funcției pentru a genera produsul cartezian și a scrie rezultatul în fișierul de ieșire
            produsCartezian(n, m, A, new int[n], 0, writer);

            // Închiderea scanner-ului și a writer-ului
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // Tratarea excepției în cazul în care fișierul de intrare nu poate fi găsit
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }

    // Funcție recursivă pentru generarea produsului cartezian
    public static void produsCartezian(int n, int[] m, int[][] A, int[] stiva, int k, PrintWriter writer) {
        // Verificarea dacă am ajuns la finalul unei permutări complete
        if (k == n) {
            // Scrierea elementelor permutării în fișierul de ieșire
            for (int i = 0; i < n; i++) {
                writer.print(A[i][stiva[i]] + " ");
            }
            writer.println();
        } else {
            // Generarea următoarei permutări
            for (int i = 0; i < m[k]; i++) {
                stiva[k] = i; // Adăugarea elementului curent la permutare
                produsCartezian(n, m, A, stiva, k + 1, writer); // Apel recursiv pentru generarea permutării următoare
            }
        }
    }
}
