import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class QuickSortAleator {
    public static void main(String[] args) {
        try {
            // Citim din fisierul de intrare si deschidem fisierul de iesire
            Scanner scanner = new Scanner(new File("InputL4/input5.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            // Citim dimensiunea si elementele vectorului din fisierul de intrare
            int n = scanner.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }
            scanner.close(); // Inchidem scanner-ul pentru fisierul de intrare

            // Sortam vectorul utilizand algoritmul QuickSort
            quickSort(array, 0, n - 1);

            // Scriem vectorul sortat in fisierul de iesire
            for (int i = 0; i < n; i++) {
                writer.print(array[i] + " ");
            }
            writer.close(); // Inchidem writer-ul pentru fisierul de iesire

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afisam stack trace-ul in cazul in care fisierul de intrare nu este gasit
        }
    }

    // Implementarea algoritmului QuickSort
    public static void quickSort(int[] array, int p, int q) {
        if (p < q) {
            int pivotIndex = randomPartition(array, p, q);
            quickSort(array, p, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, q);
        }
    }

    // Partitionarea aleatoare a vectorului si alegerea pivotului
    public static int randomPartition(int[] array, int p, int q) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(q - p + 1) + p;
        int temp = array[q];
        array[q] = array[randomIndex];
        array[randomIndex] = temp;
        return partition(array, p, q);
    }

    // Partitionarea vectorului utilizand pivotul
    public static int partition(int[] array, int p, int q) {
        int pivot = array[q];
        int i = p - 1;
        for (int j = p; j < q; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[q];
        array[q] = temp;
        return i + 1;
    }
}
