import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JoculFazan {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("InputL5/input7.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            int n = scanner.nextInt();
            scanner.nextLine();

            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = scanner.nextLine();
            }

            ArrayList<String> longestSequence = findLongestSequence(words);

            writer.println(longestSequence.size());
            for (String word : longestSequence) {
                writer.println(word);
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> findLongestSequence(String[] words) {
        HashMap<String, ArrayList<String>> adjacencyMap = new HashMap<>();
        ArrayList<String> longestSequence = new ArrayList<>();

        // Crearea unui graf de adiacenta pentru cuvinte bazat pe prefixe si sufixe
        for (String word : words) {
            String prefix = word.substring(0, 2);
            adjacencyMap.putIfAbsent(prefix, new ArrayList<>());
            adjacencyMap.get(prefix).add(word);
        }

        // Parcurgerea fiecarui cuvant pentru a incepe cautarea secventei
        for (String word : words) {
            ArrayList<String> sequence = new ArrayList<>();
            sequence.add(word);
            findNextWord(word.substring(word.length() - 2), adjacencyMap, sequence, longestSequence);
        }

        return longestSequence;
    }

    private static void findNextWord(String suffix, HashMap<String, ArrayList<String>> adjacencyMap, ArrayList<String> sequence, ArrayList<String> longestSequence) {
        // Verificarea daca exista cuvinte care incep cu sufixul dat
        if (adjacencyMap.containsKey(suffix)) {
            for (String nextWord : adjacencyMap.get(suffix)) {
                // Daca cuvantul urmator nu a fost deja folosit, continuam cautarea
                if (!sequence.contains(nextWord)) {
                    ArrayList<String> newSequence = new ArrayList<>(sequence);
                    newSequence.add(nextWord);
                    // Cautam urmatorul cuvant recursiv
                    findNextWord(nextWord.substring(nextWord.length() - 2), adjacencyMap, newSequence, longestSequence);
                }
            }
        }

        // Verificam daca secventa curenta este cea mai lunga
        if (sequence.size() > longestSequence.size()) {
            longestSequence.clear();
            longestSequence.addAll(sequence);
        }
    }
}
