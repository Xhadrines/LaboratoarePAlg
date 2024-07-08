import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CelMaiPopular {
    public static void main(String[] args) throws IOException {
        // Deschideti fisierul de intrare si de iesire
        File inputFile = new File("InputL8/input9.txt");
        File outputFile = new File("output.txt");
        Scanner scanner = new Scanner(inputFile);
        PrintWriter writer = new PrintWriter(outputFile);

        // Citim numarul de persoane din fisierul de intrare
        int n = scanner.nextInt();
        scanner.nextLine();

        // Initializam un Map pentru a tine evidenta popularitatii fiecarei persoane
        Map<Integer, Integer> popularityCount = new HashMap<>();

        // Initializam numarul de aparitii pentru fiecare persoana la 0
        for (int i = 1; i <= n; i++) {
            popularityCount.put(i, 0);
        }

        // Citim relatiile de popularitate si numaram aparitiile fiecarei persoane
        for (int i = 1; i <= n; i++) {
            String[] tokens = scanner.nextLine().split(" ");
            for (String token : tokens) {
                int popularPerson = Integer.parseInt(token);
                popularityCount.put(popularPerson, popularityCount.getOrDefault(popularPerson, 0) + 1);
            }
        }

        // Inchidem scanner-ul pentru fisierul de intrare
        scanner.close();

        // Gasim numarul maxim de aparitii
        int maxCount = Collections.max(popularityCount.values());

        // Gasim toate persoanele cu cele mai multe aparitii
        List<Integer> mostPopularPeople = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : popularityCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostPopularPeople.add(entry.getKey());
            }
        }

        // Scriem rezultatul in fisierul de iesire
        for (int person : mostPopularPeople) {
            writer.print(person + " ");
        }
        writer.println(); // Adaugam un newline la final

        // Inchidem writer-ul pentru fisierul de iesire
        writer.close();
    }
}
