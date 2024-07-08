import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class InmultireaMatricilor {
    public static void main(String[] args) {
        // Deschide fisierul de intrare pentru citire
        File inputFile = new File("InputL1/input3.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            // Citeste dimensiunile matricii A (n x m)
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            // Initializeaza matricea A si o populeaza cu datele din fisier
            int[][] A = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    A[i][j] = scanner.nextInt();
                }
            }

            // Citeste dimensiunile matricii B (m2 x p)
            int m2 = scanner.nextInt();
            int p = scanner.nextInt();

            // Initializeaza matricea B si o populeaza cu datele din fisier
            int[][] B = new int[m2][p];
            for (int i = 0; i < m2; i++) {
                for (int j = 0; j < p; j++) {
                    B[i][j] = scanner.nextInt();
                }
            }

            // Verifica daca dimensiunile matricelor permit inmultirea
            if (m != m2) {
                System.out.println("Dimensiunile matricei nu sunt valide pentru inmultire!");
                return;
            }

            // Initializeaza matricea rezultat C si efectueaza inmultirea matricelor A si B
            int[][] C = new int[n][p];
            InmultireMatrici(A, n, m, B, p, C);

            // Deschide fisierul de iesire pentru scriere
            File outputFile = new File("output.txt");
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                // Scrie matricea rezultat in fisierul de iesire
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < p; j++) {
                        writer.print(C[i][j] + " ");
                    }
                    writer.println();
                }
            }
        } catch (FileNotFoundException e) {
            // Prinde si afiseaza exceptia in cazul in care apare o eroare la citirea/scrierea fisierului
            System.out.println("Eroare la citirea/scrierea fisierului: " + e.getMessage());
        }
    }

    // Functie care efectueaza inmultirea a doua matrici si stocheaza rezultatul intr-o matrice C
    public static void InmultireMatrici(int[][] A, int n, int m, int[][] B, int p, int[][] C) {
        // Parcurge fiecare element din matricea rezultat C
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                // Initializeaza elementul curent din C cu 0 si efectueaza suma produselor elementelor corespunzatoare din A si B
                C[i][j] = 0;
                for (int k = 0; k < m; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}
