import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SortareaPrinMetodaBulelor {
    public static void main(String[] args) {
        // Deschide fisierul de intrare pentru citire
        File inputFile = new File("InputL1/input5.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            // Citeste numarul de elemente din fisier
            int n = scanner.nextInt();
            // Initializeaza un array pentru a stoca elementele
            int[] a = new int[n];
            // Citeste elementele si le stocheaza in array
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Apeleaza functia de sortare prin metoda bulelor
            SORTEAZA_BULE(a, n);

            // Deschide fisierul de iesire pentru scriere
            File outputFile = new File("output.txt");
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                // Scrie elementele sortate in fisierul de iesire
                for (int i = 0; i < n; i++) {
                    writer.print(a[i] + " ");
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            // Prinde si afiseaza exceptia in cazul in care apare o eroare la citirea/scrierea fisierului
            System.out.println("Eroare la citirea/scrierea fisierului: " + e.getMessage());
        }
    }

    // Functie care sorteaza un array folosind metoda bulelor
    public static void SORTEAZA_BULE(int[] a, int n) {
        boolean sortat;
        // Executa bucla atata timp cat array-ul nu este sortat
        do {
            sortat = true;
            // Parcurge array-ul si schimba locul elementelor vecine daca nu sunt in ordine
            for (int i = 0; i < n - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    sortat = false; // Seteaza sortat pe false pentru a indica ca inca nu este sortat complet
                }
            }
        } while (!sortat); // Repeta bucla pana cand array-ul este complet sortat
    }
}
