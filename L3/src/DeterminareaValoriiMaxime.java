import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeterminareaValoriiMaxime {
    public static void main(String[] args) {
        try {
            // Deschide fisierul de intrare pentru citire
            Scanner scanner = new Scanner(new File("InputL3/input1.txt"));
            int n = scanner.nextInt();
            int[] a = new int[n];

            // Citirea elementelor array-ului din fisier
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Deschide fisierul de iesire pentru scriere
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            // Apelul functiei pentru determinarea valorii maxime
            int max = determinaMaxim(a, 0, n - 1);
            writer.println(max); // Scrierea valorii maxime in fisierul de iesire

            // Inchiderea scanner-ului si a writer-ului
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // Tratarea exceptiei in cazul in care fisierul nu poate fi gasit
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }

    // Functie pentru determinarea valorii maxime dintr-un array folosind divizare si cucerire
    public static int determinaMaxim(int[] a, int p, int q) {
        if (p == q) { // Daca suntem pe un singur element, acesta este maximul
            return a[p];
        } else {
            int m = (p + q) / 2; // Determinarea mijlocului array-ului
            int max1 = determinaMaxim(a, p, m); // Determinarea maximului in prima jumatate a array-ului
            int max2 = determinaMaxim(a, m + 1, q); // Determinarea maximului in a doua jumatate a array-ului
            return Math.max(max1, max2); // Returnarea maximului dintre cele doua jumatati
        }
    }
}
