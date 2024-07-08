import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class NoduriIzolate {
    public static void main(String args[]) throws IOException {
        // Deschide fisierele de intrare si iesire
        File inputFile = new File("InputL8/input7.txt");
        File outputFile = new File("output.txt");
        Scanner scanner = new Scanner(inputFile);
        PrintWriter writer = new PrintWriter(outputFile);

        // Citeste numarul de noduri si de muchii
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Creeaza un obiect Graph si adauga muchiile in graf
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            graph.addEdge(src, dest);
        }

        scanner.close();

        // Verifica daca graful are noduri izolate si scrie rezultatul in fisierul de iesire
        if (graph.hasIsolatedNodes()) {
            writer.println("Graful are noduri izolate.");
        } else {
            writer.println("Graful nu are noduri izolate.");
        }

        // Inchide fisierele
        writer.close();
    }

    // Clasa Graph pentru a reprezenta un graf neorientat
    static class Graph {
        int V; // Numarul de noduri (vertices)
        LinkedList<Integer> adjListArray[]; // Lista de adiacenta

        // Constructorul clasei Graph
        @SuppressWarnings("unchecked") // Suprima avertizarile de tip "unchecked" generate de compilator pentru operatiile cu colectii generice
        Graph(int V) {
            this.V = V;
            adjListArray = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjListArray[i] = new LinkedList<>();
            }
        }

        // Metoda pentru adaugarea unei muchii intre doua noduri
        void addEdge(int src, int dest) {
            adjListArray[src].add(dest);
            adjListArray[dest].add(src); // Graful este neorientat, deci adaugam muchia in ambele directii
        }

        // Metoda pentru verificarea daca graful are noduri izolate
        boolean hasIsolatedNodes() {
            // Initializam un vector de vizitare si il completam cu false
            boolean[] visited = new boolean[V];
            Arrays.fill(visited, false);

            // Parcurgem fiecare nod si verificam daca are vecini
            for (int u = 0; u < V; u++) {
                if (adjListArray[u].size() == 0) { // Daca nu are vecini
                    return true; // Intoarcem true pentru a indica ca exista un nod izolat
                }
            }

            return false; // Intoarcem false daca nu s-a gasit niciun nod izolat
        }
    }
}
