import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SortareaPrinInterclasare {
    public static void main(String[] args) {
        try {
            // Citim din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL4/input1.txt"));
            int n = scanner.nextInt();
            int[] a = new int[n];

            // Citim vectorul a din fisierul de intrare
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            scanner.close(); // Inchidem scanner-ul pentru fisierul de intrare

            // Sortam vectorul utilizand algoritmul de interclasare
            sortareInterclasare(a, 0, n - 1);

            // Scriem vectorul sortat in fisierul de iesire
            PrintWriter writer = new PrintWriter("output.txt");
            for (int i = 0; i < n; i++) {
                writer.print(a[i] + " ");
            }
            writer.close(); // Inchidem writer-ul pentru fisierul de iesire
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de intrare nu a fost gasit.");
            e.printStackTrace(); // Afisam stack trace-ul in cazul in care fisierul de intrare nu este gasit
        }
    }

    // Metoda recursiva pentru sortarea prin interclasare
    static void sortareInterclasare(int[] arr, int p, int q) {
        if (p < q) {
            int m = (p + q) / 2;
            sortareInterclasare(arr, p, m);
            sortareInterclasare(arr, m + 1, q);
            interclaseaza(arr, p, m, q);
        }
    }

    // Metoda pentru interclasarea a doua subvectori
    static void interclaseaza(int[] arr, int p, int m, int q) {
        int n1 = m - p + 1;
        int n2 = q - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copiem elementele in subvectorii temporari
        for (int i = 0; i < n1; ++i)
            L[i] = arr[p + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = p;
        // Interclasam subvectorii temporari
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copiem elementele ramase din subvectorul stang (daca exista)
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copiem elementele ramase din subvectorul drept (daca exista)
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
