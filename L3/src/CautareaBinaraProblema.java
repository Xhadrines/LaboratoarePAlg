import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CautareaBinaraProblema {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("InputL3/input5.txt"))) {
            int n = scanner.nextInt(); // Citeste dimensiunea setului de numere

            int[] set = new int[n]; // Initializeaza un array pentru setul de numere

            // Citirea si stocarea numerelor in set
            for (int i = 0; i < n; i++) {
                set[i] = scanner.nextInt();
            }

            // Sortarea array-ului pentru a putea aplica cautarea binara
            Arrays.sort(set);

            int searchValue = scanner.nextInt(); // Citeste valoarea cautata

            // Masurarea timpului inceput pentru cautarea binara
            long start = System.nanoTime();
            int binarySearchResult = binarySearch(set, searchValue); // Aplica cautarea binara
            long end = System.nanoTime(); // Masurarea timpului de sfarsit pentru cautarea binara
            long binarySearchTime = end - start; // Calculul timpului total pentru cautarea binara

            // Scrierea rezultatelor in fisierul de iesire
            try (PrintWriter writer = new PrintWriter(new File("output.txt"))) {
                writer.println("Valoare cautata: " + searchValue);
                writer.println("Cautare binara: Pozitie - " + binarySearchResult + ", Timp - " + binarySearchTime + " nanosecunde");
            } catch (FileNotFoundException e) {
                System.out.println("Fisierul de iesire nu a putut fi creat: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul de intrare nu a fost gasit: " + e.getMessage());
        }
    }

    // Functia care realizeaza cautarea binara intr-un array sortat
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Returneaza pozitia elementului gasit
            } else if (arr[mid] < target) {
                left = mid + 1; // Cautare in partea dreapta a array-ului
            } else {
                right = mid - 1; // Cautare in partea stanga a array-ului
            }
        }

        return -1; // Returneaza -1 daca elementul nu este gasit
    }
}
