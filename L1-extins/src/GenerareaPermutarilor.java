import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GenerareaPermutarilor {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("InputL1-extins/input3.txt"));
             PrintWriter writer = new PrintWriter(new File("output.txt"))) {
            // Citeste numarul de elemente din fisier
            int n = scanner.nextInt();
            // Initializeaza un vector pentru a retine permutarile
            int[] stiva = new int[n];
            // Genereaza permutarile si le scrie in fisierul de iesire
            generarePermutari(n, 0, stiva, writer);
        } catch (FileNotFoundException e) {
            // Prinde si afiseaza exceptia in cazul in care apare o eroare la citirea/scrierea fisierului
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }

    // Functie recursiva pentru generarea permutarilor
    public static void generarePermutari(int n, int k, int[] stiva, PrintWriter writer) {
        boolean as;
        boolean ev;
        while (k >= 0) {
            ev = true;
            do {
                as = true;
                if (stiva[k] < n) {
                    stiva[k]++;
                    ev = true;
                    for (int i = 0; i < k; i++) {
                        if (stiva[i] == stiva[k]) {
                            ev = false;
                            break;
                        }
                    }
                } else {
                    as = false;
                }
            } while (as && !ev);
            if (as) {
                if (k == n - 1) {
                    // Daca am ajuns la o permutare completa, o scriem in fisierul de iesire
                    for (int i = 0; i < n; i++) {
                        writer.print(stiva[i] + " ");
                    }
                    writer.println();
                } else {
                    k++;
                    stiva[k] = 0;
                }
            } else {
                k--;
            }
        }
    }
}
