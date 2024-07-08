import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Inversiuni {
    public static void main(String[] args) {
        try {
            // Citim din fisierul de intrare si deschidem fisierul de iesire
            Scanner scanner = new Scanner(new File("InputL4/input3.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            int n = scanner.nextInt(); // Citim dimensiunea vectorului
            int[] arr = new int[n]; // Initializam vectorul
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt(); // Citim elementele vectorului
            }
            scanner.close(); // Inchidem scanner-ul pentru fisierul de intrare

            // Calculam numarul de inversiuni folosind metoda divide et impera si numarul de inversiuni folosind metoda clasica
            ArrayList<Pair> inversiuni = nrInversiuni(arr, n);
            int nrInversiuni = nrInversiuni2(arr, 0, n - 1);

            // Scriem numarul total de inversiuni si fiecare pereche de inversiuni in fisierul de iesire
            writer.println(nrInversiuni);
            writer.println(inversiuni.size());
            for (Pair pair : inversiuni) {
                writer.println(pair.i + " " + pair.j);
            }

            writer.close(); // Inchidem writer-ul pentru fisierul de iesire

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afisam stack trace-ul in cazul in care fisierul de intrare nu este gasit
        }
    }

    // Metoda care calculeaza numarul de inversiuni folosind metoda clasica
    static ArrayList<Pair> nrInversiuni(int[] a, int n) {
        ArrayList<Pair> inversiuni = new ArrayList<>(); // Initializam o lista de perechi de inversiuni
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) { // Daca gasim o inversiune, o adaugam la lista
                    inversiuni.add(new Pair(i, j));
                }
            }
        }
        return inversiuni; // Returnam lista de inversiuni
    }

    // Clasa Pair pentru a stoca o pereche de indici pentru inversiuni
    static class Pair {
        int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // Metoda care calculeaza numarul de inversiuni folosind metoda divide et impera
    static int nrInversiuni2(int[] a, int p, int q) {
        if (p == q) {
            return 0;
        } else {

            int m = (p + q) / 2;

            int nr1 = nrInversiuni2(a, p, m);
            int nr2 = nrInversiuni2(a, m + 1, q);

            int nr12 = interclaseaza(a, p, m, q); // Interclasam cele doua jumatati si calculam numarul de inversiuni
            return nr1 + nr2 + nr12;
        }
    }

    // Metoda care interclaseaza doua subsiruri si calculeaza numarul de inversiuni
    static int interclaseaza(int[] a, int p, int m, int q) {
        int[] leftArr = new int[m - p + 1];
        int[] rightArr = new int[q - m];
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = a[p + i];
        }
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = a[m + 1 + i];
        }

        int i = 0, j = 0, k = p, nrInversiuni = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
                nrInversiuni += (m - p + 1) - i; // Numarul de inversiuni este calculat aici
            }
        }

        while (i < leftArr.length) {
            a[k++] = leftArr[i++];
        }

        while (j < rightArr.length) {
            a[k++] = rightArr[j++];
        }

        return nrInversiuni; // Returnam numarul total de inversiuni
    }
}
