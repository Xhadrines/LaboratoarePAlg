import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class GrupuriDeColindatori {
    public static void main(String[] args) {
        try {
            // Deschide fisierele pentru citire si scriere
            BufferedReader reader = new BufferedReader(new FileReader("InputL8/input5.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Citeste numarul de teste
            int numarTeste = Integer.parseInt(reader.readLine());

            // Parcurge fiecare test
            for (int t = 0; t < numarTeste; t++) {
                // Citeste numarul de colindatori
                int n = Integer.parseInt(reader.readLine());

                // Initializeaza matricea de prietenie
                int[][] prietenie = new int[n][n];

                // Construieste matricea de prietenie
                for (int i = 0; i < n; i++) {
                    StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                    for (int j = 0; j < n; j++) {
                        prietenie[i][j] = Integer.parseInt(tokenizer.nextToken());
                    }
                }

                // Gaseste grupurile de colindatori pentru testul curent
                List<Integer> grupuri = gasesteGrupuriColindatori(prietenie, n);

                // Scrie rezultatele in fisierul de iesire
                for (int numarColindatori : grupuri) {
                    writer.write(numarColindatori + " ");
                }
                writer.write("\n");
            }

            // Inchide fisierele
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru gasirea grupurilor de colindatori
    private static List<Integer> gasesteGrupuriColindatori(int[][] prietenie, int n) {
        List<Integer> grupuri = new ArrayList<>(); // Lista pentru a stoca numarul de colindatori din fiecare grup
        boolean[] vizitat = new boolean[n]; // Vector pentru a tine evidenta nodurilor vizitate

        // Parcurge fiecare nod
        for (int i = 0; i < n; i++) {
            if (!vizitat[i]) { // Daca nodul nu a fost vizitat
                int numarColindatori = bfs(prietenie, vizitat, i); // Aplica BFS pentru a gasi numarul de colindatori
                if (numarColindatori > 0) { // Daca s-a gasit cel putin un colindator in grupul curent
                    grupuri.add(numarColindatori); // Adauga numarul de colindatori la lista de grupuri
                }
            }
        }

        grupuri.sort(null); // Sorteaza grupurile crescator dupa numarul de colindatori
        return grupuri;
    }

    // Metoda pentru parcurgerea in latime (BFS)
    private static int bfs(int[][] prietenie, boolean[] vizitat, int nod) {
        Queue<Integer> coada = new LinkedList<>(); // Coada pentru BFS
        coada.add(nod); // Adauga nodul de start in coada
        vizitat[nod] = true; // Marcheaza nodul ca vizitat
        int numarColindatori = 1; // Contor pentru numarul de colindatori

        // Parcurge coada
        while (!coada.isEmpty()) {
            int curent = coada.poll(); // Extrage primul nod din coada
            // Parcurge vecinii nodului curent
            for (int i = 0; i < prietenie.length; i++) {
                if (prietenie[curent][i] == 1 && !vizitat[i]) { // Daca exista o legatura si vecinul nu a fost vizitat
                    coada.add(i); // Adauga vecinul in coada
                    vizitat[i] = true; // Marcheaza vecinul ca vizitat
                    numarColindatori++; // Incrementam numarul de colindatori
                }
            }
        }

        return numarColindatori; // Returneaza numarul de colindatori din grupul curent
    }
}
