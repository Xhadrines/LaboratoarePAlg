import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OperatiiCuMultimi {
    public static void main(String[] args) {
        try {
            // Citim din fisierul de intrare si deschidem fisierul de iesire
            Scanner scanner = new Scanner(new File("InputL4/input4.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            // Citim dimensiunea si elementele primei multimi si le adaugam intr-un set
            int n = scanner.nextInt();
            Set<Integer> setA = new HashSet<>();
            for (int i = 0; i < n; i++) {
                setA.add(scanner.nextInt());
            }

            // Citim dimensiunea si elementele celei de-a doua multimi si le adaugam intr-un set
            int m = scanner.nextInt();
            Set<Integer> setB = new HashSet<>();
            for (int i = 0; i < m; i++) {
                setB.add(scanner.nextInt());
            }

            scanner.close(); // Inchidem scanner-ul pentru fisierul de intrare

            // Calculam reuniunea, intersectia si diferenta dintre cele doua multimi
            Set<Integer> reun = new HashSet<>(setA);
            reun.addAll(setB);

            Set<Integer> intersect = new HashSet<>(setA);
            intersect.retainAll(setB);

            Set<Integer> difference = new HashSet<>(setA);
            difference.removeAll(setB);

            // Scriem reuniunea, intersectia si diferenta in fisierul de iesire
            writer.println("Reuniune: " + reun);
            writer.println("Intersectie: " + intersect);
            writer.println("Diferenta: " + difference);
            writer.close(); // Inchidem writer-ul pentru fisierul de iesire

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afisam stack trace-ul in cazul in care fisierul de intrare nu este gasit
        }
    }
}
