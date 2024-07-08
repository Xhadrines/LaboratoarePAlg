import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DfBfCuMatriceDeAdiacenta {
    public static void main(String[] args) {
        try {
            // Deschiderea fisierelor de intrare si de iesire
            BufferedReader reader = new BufferedReader(new FileReader("InputL8/input6.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Citirea numarului de noduri si muchii
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]); // Numarul de noduri
            int m = Integer.parseInt(line[1]); // Numarul de muchii

            // Initializarea matricei de adiacenta
            @SuppressWarnings("unchecked") // Suprima avertizarile de tip "unchecked" generate de compilator pentru operatiile cu colectii generice
            List<Integer>[] graf = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graf[i] = new ArrayList<>();
            }

            // Citirea muchiilor grafului
            for (int i = 0; i < m; i++) {
                line = reader.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                graf[u].add(v);
                graf[v].add(u); // Graful este neorientat
            }

            int start = Integer.parseInt(reader.readLine()); // Nodul de start

            // Parcurgerea in adancime (DFS)
            writer.write("Parcurgere DFS:\n");
            boolean[] vizitatDFS = new boolean[n];
            dfs(graf, vizitatDFS, start, writer);

            // Parcurgerea in latime (BFS)
            writer.write("\nParcurgere BFS:\n");
            boolean[] vizitatBFS = new boolean[n];
            bfs(graf, vizitatBFS, start, writer);

            // Inchiderea fisierelor
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru parcurgerea in adancime (DFS)
    private static void dfs(List<Integer>[] graf, boolean[] vizitat, int nod, BufferedWriter writer) throws IOException {
        vizitat[nod] = true;
        writer.write(nod + " ");
        for (int vecin : graf[nod]) {
            if (!vizitat[vecin]) {
                dfs(graf, vizitat, vecin, writer);
            }
        }
    }

    // Metoda pentru parcurgerea in latime (BFS)
    private static void bfs(List<Integer>[] graf, boolean[] vizitat, int start, BufferedWriter writer) throws IOException {
        Queue<Integer> coada = new LinkedList<>();
        coada.add(start);
        vizitat[start] = true;
        while (!coada.isEmpty()) {
            int nod = coada.poll();
            writer.write(nod + " ");
            for (int vecin : graf[nod]) {
                if (!vizitat[vecin]) {
                    coada.add(vecin);
                    vizitat[vecin] = true;
                }
            }
        }
    }
}
