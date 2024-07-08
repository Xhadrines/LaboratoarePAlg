import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ElementulSuma2 {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL4/input6.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            int n = scanner.nextInt(); // Citim dimensiunea vectorilor
            int[] a = new int[n]; // Initializam primul vector
            int[] b = new int[n]; // Initializam al doilea vector

            // Citirea elementelor primului vector
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            
            // Citirea elementelor celui de-al doilea vector
            for (int i = 0; i < n; i++) {
                b[i] = scanner.nextInt();
            }

            int x = scanner.nextInt(); // Citim suma cautata
            scanner.close(); // Inchidem scanner-ul pentru fisierul de intrare

            Arrays.sort(b); // Sortam al doilea vector pentru a putea efectua cautarea binara

            boolean found = false; // Variabila pentru a tine evidenta daca s-a gasit perechea sau nu
            for (int i = 0; i < n; i++) {
                int complement = x - a[i]; // Calculam complementul fiecarui element din primul vector
                int index = Arrays.binarySearch(b, complement); // Cautam complementul in al doilea vector
                if (index >= 0) { // Daca complementul este gasit in al doilea vector
                    writer.println(a[i] + " " + b[index]); // Scriem perechea in fisierul de iesire
                    found = true; // Setam found la true pentru a indica ca am gasit o pereche
                    break; // Ne oprim din cautare
                }
            }

            if (!found) { // Daca nu s-a gasit nicio pereche
                writer.println("-1"); // Scriem -1 in fisierul de iesire
            }

            writer.close(); // Inchidem writer-ul pentru fisierul de iesire

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afisam stack trace-ul in cazul in care fisierul de intrare nu este gasit
        }
    }
}
