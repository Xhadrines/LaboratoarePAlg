import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class InteligentaVsGreutateaElefantilor {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("InputL5/input3.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            int T = scanner.nextInt();
            for (int t = 0; t < T; t++) {
                int n = scanner.nextInt();
                Elephant[] elephants = new Elephant[n];
                for (int i = 0; i < n; i++) {
                    int weight = scanner.nextInt();
                    int iq = scanner.nextInt();
                    elephants[i] = new Elephant(weight, iq);
                }

                // Sorteaza elefantii in functie de greutate
                Arrays.sort(elephants, Comparator.comparingInt((Elephant e) -> e.weight));

                // Calculeaza LIS pentru greutate
                int[] lisWeights = LIS(elephants);

                // Sorteaza elefantii in functie de IQ
                Arrays.sort(elephants, Comparator.comparingInt((Elephant e) -> -e.iq));

                // Calculeaza LIS pentru IQ
                int[] lisIQ = LIS(elephants);

                // Alege valoarea minima dintre LIS-uri pentru a obtine numarul maxim de elefanti
                int maxElephants = Math.min(lisWeights[n - 1], lisIQ[n - 1]);

                writer.println(maxElephants);
            }

            scanner.close();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afiseaza urmele de eroare in caz de fisierul de intrare nu este gasit
        }
    }

    // Functia pentru calcularea LIS (Longest Increasing Subsequence)
    public static int[] LIS(Elephant[] elephants) {
        int n = elephants.length;
        int[] lis = new int[n];
        lis[0] = 1;

        for (int i = 1; i < n; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (elephants[i].iq < elephants[j].iq && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        Arrays.sort(lis); // Sorteaza LIS-ul pentru a obtine lungimea maxima
        return lis;
    }

    // Clasa pentru reprezentarea elefantilor
    static class Elephant {
        int weight;
        int iq;

        Elephant(int weight, int iq) {
            this.weight = weight;
            this.iq = iq;
        }
    }
}
