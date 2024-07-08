import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VerificareaVocabularului {
    public static void main(String[] args) {
        try {
            // Citirea datelor de intrare din fisier
            Scanner scanner = new Scanner(new File("InputL6/input5.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            // Citirea numarului de cuvinte din vocabular si initializarea trie-ului
            int n = scanner.nextInt();
            Trie trie = new Trie();
            for (int i = 0; i < n; i++) {
                String word = scanner.next();
                trie.insert(word);
            }

            // Citirea numarului de cuvinte de verificat
            int m = scanner.nextInt();
            scanner.nextLine(); // Consumarea newline-ului
            for (int i = 0; i < m; i++) {
                String word = scanner.nextLine().trim(); // Citirea cuvantului si eliminarea spatiilor suplimentare
                String correctedWord = correctWord(word, trie); // Corectarea cuvantului
                writer.println(correctedWord); // Scrierea cuvantului corectat in fisier
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Functie pentru a corecta un cuvant
    private static String correctWord(String word, Trie trie) {
        if (trie.search(word)) { // Daca cuvantul este deja in vocabular, nu este nevoie de corectare
            return word;
        }

        // Incercam sa corectam cuvantul prin adaugarea unui singur caracter in diferite pozitii
        for (int i = 0; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i + 1);
            // Pentru fiecare caracter posibil intre 'a' si 'z', incercam sa cream un cuvant nou
            for (char ch = 'a'; ch <= 'z'; ch++) {
                String newWord = prefix + ch + suffix;
                if (trie.search(newWord)) { // Daca cuvantul corectat este in vocabular, il returnam
                    return newWord;
                }
            }
        }

        return word; // Daca nu am gasit nicio corectare, returnam cuvantul original
    }
}

// Clasa care reprezinta un nod in trie
class TrieNode {
    TrieNode[] children = new TrieNode[26]; // Array-ul de copii, unul pentru fiecare litera a alfabetului
    boolean isEndOfWord; // Indicator pentru a marca sfarsitul unui cuvant in trie

    TrieNode() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

// Clasa trie care contine operatiile de inserare si cautare a cuvintelor
class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Functie pentru a insera un cuvant in trie
    void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a'; // Calcularea indexului pentru caracterul curent
            if (current.children[index] == null) {
                current.children[index] = new TrieNode(); // Crearea unui nod nou daca nu exista
            }
            current = current.children[index]; // Mutarea la urmatorul nivel in trie
        }
        current.isEndOfWord = true; // Marcarea sfarsitului cuvantului
    }

    // Functie pentru a cauta un cuvant in trie
    boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a'; // Calcularea indexului pentru caracterul curent
            if (current.children[index] == null) {
                return false; // Daca nodul nu exista, cuvantul nu este in trie
            }
            current = current.children[index]; // Mutarea la urmatorul nivel in trie
        }
        return current != null && current.isEndOfWord; // Verificarea daca s-a ajuns la sfarsitul unui cuvant
    }
}
