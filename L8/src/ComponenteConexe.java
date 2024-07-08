import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ComponenteConexe {
    public static void main(String[] args) {
        try {
            // Deschideti fisierul de intrare si de iesire
            BufferedReader reader = new BufferedReader(new FileReader("InputL8/input4.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Citim numarul de noduri si muchii din prima linie
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken()); // Numarul de noduri
            int m = Integer.parseInt(tokenizer.nextToken()); // Numarul de muchii

            // Initializam o lista de liste pentru a reprezenta graful
            List<List<Integer>> graf = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graf.add(new ArrayList<>());
            }

            // Citim fiecare muchie si actualizam graful
            for (int i = 0; i < m; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(tokenizer.nextToken());
                int v = Integer.parseInt(tokenizer.nextToken());
                // Adaugam u si v ca vecini unul altuia
                graf.get(u).add(v);
                graf.get(v).add(u);
            }

            // Initializam un vector pentru a marca nodurile vizitate
            boolean[] vizitat = new boolean[n];
            int compConexa = 0; // Numarul de componente conexe
            List<List<Integer>> componenteConexe = new ArrayList<>(); // Lista componentelor conexe

            // Parcurgem fiecare nod si aplicam DFS daca acesta nu a fost vizitat inca
            for (int i = 0; i < n; i++) {
                if (!vizitat[i]) {
                    compConexa++; // Incrementam numarul de componente conexe
                    List<Integer> componenta = new ArrayList<>();
                    dfs(graf, vizitat, i, compConexa, componenta); // Aplicam DFS pe nodul curent
                    componenteConexe.add(componenta); // Adaugam componenta conexa gasita la lista de componente conexe
                }
            }

            // Scriem rezultatele in fisierul de iesire
            writer.write(compConexa + "\n"); // Scriem numarul de componente conexe
            for (List<Integer> componente : componenteConexe) {
                for (int nod : componente) {
                    writer.write(nod + " "); // Scriem nodurile din fiecare componenta conexa
                }
                writer.write("\n"); // Adaugam un newline intre componente
            }

            // Inchidem fisierele
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Functie de parcurgere in adancime (DFS)
    private static void dfs(List<List<Integer>> graf, boolean[] vizitat, int nod, int compConexa, List<Integer> componenta) {
        vizitat[nod] = true; // Marcam nodul ca vizitat
        componenta.add(nod); // Adaugam nodul la componenta conexa curenta

        // Parcurgem toti vecinii nodului curent
        for (int vecin : graf.get(nod)) {
            if (!vizitat[vecin]) {
                dfs(graf, vizitat, vecin, compConexa, componenta); // Aplicam DFS pe vecinul nevizitat
            }
        }
    }
}
