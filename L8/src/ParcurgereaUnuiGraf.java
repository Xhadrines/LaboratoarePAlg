import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ParcurgereaUnuiGraf {
    public static void main(String[] args) {
        try {
            // Se deschide fisierul de intrare si de iesire.
            BufferedReader reader = new BufferedReader(new FileReader("InputL8/input1.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Se citeste prima linie a fisierului de intrare, care contine doua numere separate de un spatiu, reprezentand numarul de noduri (n) si numarul de muchii (m) ale grafului.
            String[] linie = reader.readLine().split(" ");
            int n = Integer.parseInt(linie[0]); 
            int m = Integer.parseInt(linie[1]); 

            // Se initializeaza o matrice de adiacenta a de dimensiune n x n, unde a[i][j] = 1 indica existenta unei muchii intre nodurile i si j.
            int[][] a = new int[n][n];

            // Se citesc urmatoarele m linii ale fisierului de intrare, fiecare continand doua numere separate de un spatiu, reprezentand nodurile care definesc o muchie in graf. Se actualizeaza matricea de adiacenta corespunzator.
            for (int i = 0; i < m; i++) {
                linie = reader.readLine().split(" ");
                int x = Integer.parseInt(linie[0]);
                int y = Integer.parseInt(linie[1]);
                a[x][y] = 1;
                a[y][x] = 1;
            }

            // Se citeste un nod de start v.
            int v = Integer.parseInt(reader.readLine());

            // Se afiseaza in fisierul de iesire "Parcurgere in latime (BF):" si se apeleaza functia BF pentru a realiza parcurgerea in latime a grafului pornind de la nodul v.
            writer.write("Parcurgere in latime (BF):\n");
            BF(a, v, writer);
            writer.write("\nParcurgere in adancime (DF):\n");
            // Se afiseaza in fisierul de iesire "Parcurgere in adancime (DF):" si se apeleaza functia DF pentru a realiza parcurgerea in adancime a grafului pornind de la nodul v.
            DF(a, v, writer);

            // Se inchid fisierele de intrare si de iesire.
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru parcurgerea in latime a grafului.
    public static void BF(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];
        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        // Se utilizeaza o coada pentru a urmari nodurile care trebuie parcurse.
        Queue<Integer> coada = new LinkedList<>();
        coada.offer(v);
        vizitat[v] = 1;

        while (!coada.isEmpty()) {
            int p = coada.poll();
            writer.write(p + " ");

            // Se parcurg vecinii nodului curent si se adauga in coada cei nevizitati.
            for (int j = 0; j < n; j++) {
                if (a[p][j] == 1 && vizitat[j] == 0) {
                    coada.offer(j);
                    vizitat[j] = 1;
                }
            }
        }
    }

    // Metoda pentru parcurgerea in adancime a grafului.
    public static void DF(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];
        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        // Se apeleaza metoda recursiva DFRecursiv pentru a realiza parcurgerea in adancime.
        DFRecursiv(a, v, vizitat, writer);
    }

    // Metoda recursiva pentru parcurgerea in adancime a grafului.
    public static void DFRecursiv(int[][] a, int v, int[] vizitat, BufferedWriter writer) throws IOException {
        int n = a.length;
        vizitat[v] = 1;
        writer.write(v + " ");

        // Se parcurg vecinii nodului curent si se apeleaza metoda recursiva pentru fiecare vecin nevizitat.
        for (int j = 0; j < n; j++) {
            if (a[v][j] == 1 && vizitat[j] == 0) {
                DFRecursiv(a, j, vizitat, writer);
            }
        }
    }
}
