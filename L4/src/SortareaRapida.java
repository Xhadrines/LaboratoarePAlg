import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SortareaRapida {
    public static void main(String[] args) {
        try {
            // Citirea din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL4/input2.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            int n = scanner.nextInt();
            int[] arr = new int[n];

            // Citirea vectorului din fisierul de intrare
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            scanner.close(); // Inchidem scanner-ul pentru fisierul de intrare

            // Apelul functiei de sortare rapida
            qSort(arr, 0, n - 1);

            // Scrierea rezultatului in fisierul de iesire
            for (int i = 0; i < n; i++) {
                writer.print(arr[i] + " ");
            }
            writer.close(); // Inchidem writer-ul pentru fisierul de iesire

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afisam stack trace-ul in cazul in care fisierul de intrare nu este gasit
        }
    }

    // Implementarea algoritmului de sortare rapida (Quick Sort)
    static void qSort(int[] a, int p, int q) {
        int i = p;
        int j = q;
        int x = a[(i + j) / 2]; // Alegerea pivotului

        // Partitionarea vectorului in jurul pivotului
        do {
            while (a[i] < x) i++; // Cautam un element mai mare decat pivotul in partea stanga
            while (x < a[j]) j--; // Cautam un element mai mic decat pivotul in partea dreapta

            if (i <= j) { // Daca inca nu am parcurs intregul vector
                // Schimbam elementele pentru a le plasa in partea corecta a vectorului
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        // Sortam recursiv subvectorii
        if (p < j) qSort(a, p, j); // Sortam subvectorul stang
        if (i < q) qSort(a, i, q); // Sortam subvectorul drept
    }
}
