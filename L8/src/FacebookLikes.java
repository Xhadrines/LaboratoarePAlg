import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FacebookLikes {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("InputL8/input3.txt")); // Deschide fisierul pentru citire
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt")); // Deschide fisierul pentru scriere

            int numTests = Integer.parseInt(reader.readLine()); // Citeste numarul de teste

            for (int test = 0; test < numTests; test++) { // Parcurge fiecare test
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int numPrieteni = Integer.parseInt(tokenizer.nextToken()); // Citeste numarul de prieteni
                int numRelatii = Integer.parseInt(tokenizer.nextToken()); // Citeste numarul de relatii
                int numLikesDirecte = Integer.parseInt(tokenizer.nextToken()); // Citeste numarul de like-uri directe

                List<List<Integer>> prietenii = new ArrayList<>(); // Initializeaza lista de relatii intre prieteni
                for (int i = 0; i < numPrieteni; i++) {
                    prietenii.add(new ArrayList<>()); // Initializeaza lista pentru fiecare prieten
                }

                for (int i = 0; i < numRelatii; i++) { // Construieste graful de relatii
                    tokenizer = new StringTokenizer(reader.readLine());
                    int prieten1 = Integer.parseInt(tokenizer.nextToken());
                    int prieten2 = Integer.parseInt(tokenizer.nextToken());
                    prietenii.get(prieten1).add(prieten2); // Adauga prietenul 2 la lista de prieteni a prietenului 1
                    prietenii.get(prieten2).add(prieten1); // Adauga prietenul 1 la lista de prieteni a prietenului 2 (graful este neorientat)
                }

                int[] likes = new int[numPrieteni]; // Vector pentru numarul de like-uri pentru fiecare prieten
                boolean[] visited = new boolean[numPrieteni]; // Vector pentru a tine evidenta prietenilor vizitati in DFS
                for (int i = 0; i < numPrieteni; i++) { // Parcurge fiecare prieten
                    if (!visited[i]) { // Daca prietenul nu a fost vizitat
                        dfs(prietenii, likes, visited, i, numLikesDirecte); // Aplica DFS pentru a calcula like-urile pentru fiecare prieten
                    }
                }

                for (int i = 0; i < numPrieteni; i++) { // Parcurge fiecare prieten
                    writer.write(likes[i] + " "); // Scrie numarul de like-uri pentru fiecare prieten in fisierul de iesire
                }
                writer.write("\n"); // Adauga un newline dupa fiecare set de like-uri pentru un test
            }

            reader.close(); // Inchide fisierul de citire
            writer.close(); // Inchide fisierul de scriere
        } catch (IOException e) { // Trateaza exceptiile de IO
            e.printStackTrace(); // Afiseaza urmele stivei pentru depanare
        }
    }

    // Metoda pentru parcurgerea in adancime (DFS)
    private static void dfs(List<List<Integer>> prietenii, int[] likes, boolean[] visited, int nod, int numLikes) {
        visited[nod] = true; // Marcheaza nodul ca vizitat
        likes[nod] += numLikes; // Adauga like-urile pentru nod

        for (int prieten : prietenii.get(nod)) { // Parcurge fiecare prieten al nodului
            if (!visited[prieten]) { // Daca prietenul nu a fost vizitat
                dfs(prietenii, likes, visited, prieten, numLikes / 2); // Aplica DFS pentru prieten
            }
        }
    }
}
