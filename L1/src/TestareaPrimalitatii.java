import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestareaPrimalitatii {
    public static void main(String[] args) {
        // Deschide fisierul de intrare pentru citire
        File inputFile = new File("InputL1/input4.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            // Deschide fisierul de iesire pentru scriere
            File outputFile = new File("output.txt");
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                // Parcurge fiecare numar din fisierul de intrare
                while (scanner.hasNextInt()) {
                    // Citeste un numar si determina daca este prim sau compus
                    int n = scanner.nextInt();
                    String rezultat = PRIM(n);
                    // Scrie rezultatul in fisierul de iesire
                    writer.println(rezultat);
                }
            }
        } catch (FileNotFoundException e) {
            // Prinde si afiseaza exceptia in cazul in care apare o eroare la citirea/scrierea fisierului
            System.out.println("Eroare la citirea/scrierea fisierului: " + e.getMessage());
        }
    }

    // Functie care determina daca un numar este prim sau compus
    public static String PRIM(int n) {
        if (n == 2) {
            return "PRIM";
        }
        // Verifica daca numarul este mai mic decat 2 sau este par
        if (n < 2 || n % 2 == 0) {
            return "COMPUS";
        }
        // Calculeaza radacina patrata a numarului
        int r = (int) Math.sqrt(n);
        // Parcurge numerele impare pana la radacina patrata a numarului pentru a verifica divizibilitatea
        for (int i = 3; i <= r; i += 2) {
            if (n % i == 0) {
                return "COMPUS";
            }
        }
        return "PRIM"; // Daca nu se gasesc divizori, numarul este prim
    }
}
