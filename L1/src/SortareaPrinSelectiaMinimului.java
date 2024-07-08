import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SortareaPrinSelectiaMinimului {
    public static void main(String[] args) {
        // Deschide fisierul de intrare pentru citire
        File inputFile = new File("InputL1/input6.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            // Citeste numarul de elemente din fisier
            int n = scanner.nextInt();
            // Initializeaza un array pentru a stoca elementele
            int[] a = new int[n];
            // Citeste elementele si le stocheaza in array
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Apeleaza functia de sortare prin selectia minimului
            SORTEAZA_SELECTIE_MIN(a, n);

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

    // Functie care gaseste indexul minimului intr-un array intre doua pozitii date
    public static int CAUTA_MIN(int[] a, int left, int right) {
        int indexMin = left;
        // Parcurge array-ul pentru a gasi indexul minimului intre pozitiile specificate
        for (int i = left + 1; i <= right; i++) {
            if (a[i] < a[indexMin]) {
                indexMin = i;
            }
        }
        return indexMin;
    }

    // Functie care sorteaza un array folosind selectia minimului
    public static void SORTEAZA_SELECTIE_MIN(int[] a, int n) {
        // Parcurge array-ul pentru a selecta minimul si a-l plasa pe pozitia corecta
        for (int i = 0; i < n - 1; i++) {
            int indexMin = CAUTA_MIN(a, i, n - 1);
            // Daca minimul nu este deja pe pozitia i, schimba locul minimului cu elementul de la pozitia i
            if (i != indexMin) {
                int temp = a[i];
                a[i] = a[indexMin];
                a[indexMin] = temp;
            }
        }
    }
}
