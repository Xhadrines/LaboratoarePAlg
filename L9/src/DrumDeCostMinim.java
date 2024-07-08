import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class DrumDeCostMinim {
    public static void main(String[] args) {
        String inputFileName = "InputL9/input2.txt";
        String outputFileName = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {

            // Citim prima linie pentru a afla numarul de noduri (n) si numarul de muchii (m)
            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new IllegalArgumentException("Fisierul de intrare este gol.");
            }
            String[] parts = firstLine.split(" ");
            int n = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);

            // Initializam matricea de costuri (c) cu valori mari (infinite)
            int[][] c = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(c[i], Integer.MAX_VALUE);
            }

            // Citim muchiile si costurile corespunzatoare
            for (int i = 0; i < m; i++) {
                String line = reader.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("Numarul de muchii nu corespunde cu cel specificat in prima linie.");
                }
                String[] edge = line.split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                int cost = Integer.parseInt(edge[2]);
                c[u][v] = cost;
                c[v][u] = cost; // pentru graf neorientat
            }

            // Citim nodul de start
            String startNodeLine = reader.readLine();
            if (startNodeLine == null) {
                throw new IllegalArgumentException("Lipsesc datele nodului de start.");
            }
            int startNode = Integer.parseInt(startNodeLine.trim());

            // Aplicam algoritmul lui Dijkstra pentru a calcula distantele minime
            int[] dist = Dijkstra(c, n, startNode);

            // Scriem distantele minime in fisierul de iesire
            for (int d : dist) {
                writer.println(d);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Algoritmul lui Dijkstra pentru calculul drumurilor minime in grafuri
    public static int[] Dijkstra(int[][] c, int n, int v) {
        boolean[] vizitat = new boolean[n]; // Vector pentru a marca nodurile vizitate
        int[] dist = new int[n]; // Vector pentru a stoca distantele minime

        // Initializam nodul de start ca vizitat si distanta catre el ca fiind 0
        Arrays.fill(vizitat, false);
        vizitat[v] = true;
        for (int i = 0; i < n; i++) {
            if (v != i && c[v][i] != Integer.MAX_VALUE) {
                dist[i] = c[v][i];
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        dist[v] = 0;

        // Aplicam algoritmul lui Dijkstra
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int p = -1;

            // Gasim nodul nevizitat cu distanta minima
            for (int j = 0; j < n; j++) {
                if (!vizitat[j] && dist[j] < min) {
                    min = dist[j];
                    p = j;
                }
            }

            // Daca nu mai exista noduri accesibile, iesim din bucla
            if (p == -1) {
                break;
            }
            vizitat[p] = true;

            // Actualizam distantele folosind nodul gasit
            for (int j = 0; j < n; j++) {
                if (!vizitat[j] && c[p][j] != Integer.MAX_VALUE && dist[j] > dist[p] + c[p][j]) {
                    dist[j] = dist[p] + c[p][j];
                }
            }
        }

        // Inlocuim distantele neaccesibile cu -1
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}
