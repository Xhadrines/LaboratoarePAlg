import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class DrumuriDeCostMinim {
    public static void main(String[] args) {
        String inputFileName = "InputL9/input2.txt";
        String outputFileName = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {

            // Citim prima linie din fisierul de intrare
            String[] firstLine = reader.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]); // Numarul de noduri
            int m = Integer.parseInt(firstLine[1]); // Numarul de muchii

            int[][] c = new int[n][n]; // Matricea de costuri

            // Initializam matricea c cu valori mari (infinit)
            for (int i = 0; i < n; i++) {
                Arrays.fill(c[i], Integer.MAX_VALUE); // Initializam fiecare element cu valoarea maxima
                c[i][i] = 0; // Distanta de la un nod la el insusi este 0
            }

            // Citim muchiile si costurile corespunzatoare
            for (int i = 0; i < m; i++) {
                String[] edge = reader.readLine().split(" ");
                int u = Integer.parseInt(edge[0]); // Nodul de start al muchiei
                int v = Integer.parseInt(edge[1]); // Nodul de sfarsit al muchiei
                int cost = Integer.parseInt(edge[2]); // Costul muchiei
                c[u][v] = cost; // Setam costul pentru nodurile u si v
                c[v][u] = cost; // pentru graf neorientat, setam costul si pentru nodurile v si u
            }

            // Aplicam algoritmul Roy-Floyd-Warshall pentru a calcula distantele minime
            int[][] costMinim = RoyFloydWarshall(c);

            // Scriem matricea distantelor minime in fisierul de iesire
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (costMinim[i][j] == Integer.MAX_VALUE) {
                        writer.print("INF "); // Daca nu exista drum intre nodurile i si j, scriem "INF"
                    } else {
                        writer.print(costMinim[i][j] + " "); // Altfel, scriem distanta minima
                    }
                }
                writer.println(); // Trecem la linia urmatoare a matricei
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Algoritmul Roy-Floyd-Warshall pentru calcularea drumurilor de cost minim
    public static int[][] RoyFloydWarshall(int[][] c) {
        int n = c.length;
        int[][] costMinim = new int[n][n]; // Matricea de costuri minime

        // Initializam matricea costMinim cu valorile din matricea c
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costMinim[i][j] = c[i][j];
            }
        }

        // Aplicam algoritmul Roy-Floyd-Warshall pentru a calcula distantele minime
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (costMinim[i][j] > costMinim[i][k] + costMinim[k][j]) {
                        costMinim[i][j] = costMinim[i][k] + costMinim[k][j];
                    }
                }
            }
        }

        return costMinim; // Returnam matricea cu distantele minime
    }
}
