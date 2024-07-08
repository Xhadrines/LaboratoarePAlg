import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ElementulSuma {
    public static void main(String[] args) {
        try {
            // Se deschide fisierul de intrare pentru citire
            Scanner scanner = new Scanner(new File("InputL3/input3.txt"));
            int n = scanner.nextInt();
            int[] a = new int[n];

            // Se citesc elementele array-ului din fisier
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Se citeste valoarea sumei cautate
            int x = scanner.nextInt();

            // Se deschide fisierul de iesire pentru scriere
            PrintWriter writer = new PrintWriter(new File("output.txt"));
            
            // Se apeleaza metoda pentru a gasi perechea de elemente cu suma egala cu x
            suma(a, n, x, writer);

            // Se sorteaza array-ul pentru a putea utiliza cautarea binara
            Arrays.sort(a);

            // Se apeleaza metoda pentru a gasi perechea de elemente folosind cautarea binara
            sumaDoua(a, n, x, writer);

            // Se inchid fluxurile de citire si scriere
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit: " + e.getMessage());
        }
    }

    // Metoda care gaseste perechea de elemente ale caror suma este x
    public static void suma(int[] a, int n, int x, PrintWriter writer) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] + a[j] == x) {
                    writer.println(a[i] + " " + a[j]);
                    return;
                }
            }
        }
        writer.println("-1");
    }

    // Metoda care gaseste perechea de elemente folosind cautarea binara
    public static void sumaDoua(int[] a, int n, int x, PrintWriter writer) {
        for (int i = 0; i < n - 1; i++) {
            // Se cauta complementul lui a[i] in array folosind cautarea binara
            int j = cautareBinar(a, 0, n - 1, x - a[i]);
            // Daca complementul este gasit si nu este acelasi cu elementul curent, se scrie perechea in fisier
            if (j != -1 && j != i) {
                writer.println(a[i] + " " + a[j]);
                return;
            }
        }
        writer.println("-1");
    }

    // Metoda de cautare binara
    public static int cautareBinar(int[] a, int p, int q, int x) {
        while (p <= q) {
            int m = (p + q) / 2;
            if (a[m] == x) {
                return m;
            } else if (a[m] < x) {
                p = m + 1;
            } else {
                q = m - 1;
            }
        }
        return -1;
    }
}
