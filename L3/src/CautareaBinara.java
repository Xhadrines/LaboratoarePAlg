import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CautareaBinara {
    public static void main(String[] args) {
        try {
            // Deschide fisierul de intrare pentru citire
            Scanner scanner = new Scanner(new File("InputL3/input2.txt"));
            int n = scanner.nextInt(); // Citirea dimensiunii array-ului
            int[] a = new int[n]; // Initializarea array-ului

            // Citirea elementelor array-ului
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            int x = scanner.nextInt(); // Elementul cautat

            // Deschide fisierul de iesire pentru scriere
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            // Apelarea celor trei functii de cautare si scrierea rezultatului in fisierul de iesire
            int position = cautare(a, n, x);
            // int position = cautareBinar(a, 0, n - 1, x);
            // int position = cautareBinarIterativa(a, n, x);

            writer.println(position); // Scrie rezultatul in fisierul de iesire

            // Inchide scanner-ul si writer-ul
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // Trateaza exceptia in cazul in care fisierul de intrare nu poate fi gasit
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }

    // Functie pentru cautarea secventiala a unui element intr-un array neordonat
    public static int cautare(int[] a, int n, int x) {
        for (int i = 0; i < n; i++) {
            if (a[i] == x) {
                return i; // Returneaza pozitia elementului gasit
            }
        }
        return -1; // Returneaza -1 daca elementul nu este gasit
    }

    // Functie pentru cautarea binara a unui element intr-un array sortat (varianta recursiva)
    public static int cautareBinar(int[] a, int p, int q, int x) {
        if (p > q) {
            return -1; // Returneaza -1 daca elementul nu este gasit in sub-array
        } else {
            int m = (p + q) / 2;
            if (a[m] == x) {
                return m; // Returneaza pozitia elementului gasit
            } else if (a[m] < x) {
                return cautareBinar(a, m + 1, q, x); // Cauta in partea dreapta a sub-array-ului
            } else {
                return cautareBinar(a, p, m - 1, x); // Cauta in partea stanga a sub-array-ului
            }
        }
    }

    // Functie pentru cautarea binara a unui element intr-un array sortat (varianta iterativa)
    public static int cautareBinarIterativa(int[] a, int n, int x) {
        int p = 0; // Pozitia de inceput a sub-array-ului
        int q = n - 1; // Pozitia de sfarsit a sub-array-ului
        while (p <= q) {
            int m = (p + q) / 2; // Calculul pozitiei mijlocii a sub-array-ului
            if (a[m] == x) {
                return m; // Returneaza pozitia elementului gasit
            } else if (a[m] < x) {
                p = m + 1; // Actualizeaza pozitia de inceput pentru cautare in partea dreapta a sub-array-ului
            } else {
                q = m - 1; // Actualizeaza pozitia de sfarsit pentru cautare in partea stanga a sub-array-ului
            }
        }
        return -1; // Returneaza -1 daca elementul nu este gasit in array
    }
}
