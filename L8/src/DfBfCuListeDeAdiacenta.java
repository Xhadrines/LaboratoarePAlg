import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class DfBfCuListeDeAdiacenta {
    public static void main(String args[]) throws IOException {
        // Deschiderea fisierelor de intrare si de iesire
        BufferedReader reader = new BufferedReader(new FileReader("InputL8/input7.txt"));
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

        // Citirea numarului de noduri si muchii din prima linie
        String[] params = reader.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);

        // Crearea grafului
        Graph graph = new Graph(n);

        // Adaugarea muchiilor la graf
        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            graph.addEdge(u, v);
        }

        // Citirea nodului de start
        int startNode = Integer.parseInt(reader.readLine());
        boolean[] visited = new boolean[n];

        // Parcurgerea DFS a grafului incepand de la nodul de start
        writer.println("DFS traversal:");
        dfs(graph, startNode, visited, writer);

        // Resetarea marcarilor nodurilor
        Arrays.fill(visited, false);

        // Parcurgerea BFS a grafului incepand de la nodul de start
        writer.println("\nBFS traversal:");
        bfs(graph, startNode, visited, writer);

        // Inchiderea fisierelor
        reader.close();
        writer.close();
    }

    // Clasa care reprezinta un graf folosind liste de adiacenta
    private static class Graph {
        private LinkedList<Integer>[] adj;

        @SuppressWarnings({ "unchecked", "rawtypes" }) // Suprima avertizarile de tip "unchecked" si "rawtypes" generate de compilator pentru operatiile cu colectii si tipuri generice
        public Graph(int V) {
            adj = new LinkedList[V];
            for (int i = 0; i < V; ++i)
                adj[i] = new LinkedList();
        }

        // Metoda pentru adaugarea unei muchii intre nodurile v si w
        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v); // Graful este neorientat
        }

        // Metoda pentru obtinerea listei de adiacenta a grafului
        public LinkedList<Integer>[] getAdjacencyList() {
            return adj;
        }
    }

    // Metoda recursiva pentru parcurgerea in adancime (DFS)
    public static void dfs(Graph graph, int v, boolean[] visited, PrintWriter writer) {
        visited[v] = true;
        writer.print(v + " ");

        LinkedList<Integer>[] adj = graph.getAdjacencyList();
        for (int i : adj[v]) {
            if (!visited[i]) {
                dfs(graph, i, visited, writer);
            }
        }
    }

    // Metoda pentru parcurgerea in latime (BFS)
    public static void bfs(Graph graph, int v, boolean[] visited, PrintWriter writer) {
        LinkedList<Integer> queue = new LinkedList<>();
        visited[v] = true;
        queue.add(v);

        LinkedList<Integer>[] adj = graph.getAdjacencyList();
        while (!queue.isEmpty()) {
            v = queue.poll();
            writer.print(v + " ");

            for (int i : adj[v]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
