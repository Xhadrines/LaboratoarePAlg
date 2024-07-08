import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeterminareaValoriiMaxime {
    public static void main(String[] args) {
        // Deschide fisierul de intrare pentru citire
        File inputFile = new File("InputL1-extins/input2.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            // Citeste numarul de elemente din fisier
            int n = scanner.nextInt();
            // Initializeaza un array de dimensiunea n pentru a stoca elementele
            int[] a = new int[n];
            // Citeste elementele si le stocheaza in array
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Apeleaza functia care determina valoarea maxima si o salveaza intr-o variabila
            int maxValue = DeterminaMaxim(a, n);

            // Deschide fisierul de iesire pentru scriere
            File outputFile = new File("output.txt");
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                // Scrie valoarea maxima in fisierul de iesire
                writer.println(maxValue);
            }
        } catch (FileNotFoundException e) {
            // Prinde si afiseaza exceptia in cazul in care apare o eroare la citirea/scrierea fisierului
            System.out.println("Eroare la citirea/scrierea fisierului: " + e.getMessage());
        }
    }

    // Functie care determina valoarea maxima dintr-un array
    public static int DeterminaMaxim(int[] a, int n) {
        // Initializeaza valoarea maxima cu primul element din array
        int max = a[0];
        // Parcurge array-ul pentru a gasi valoarea maxima
        for (int i = 1; i < n; i++) {
            // Verifica daca elementul curent este mai mare decat valoarea maxima gasita pana acum
            if (max < a[i]) {
                // Daca da, actualizeaza valoarea maxima
                max = a[i];
            }
        }
        // Returneaza valoarea maxima gasita
        return max;
    }
}
