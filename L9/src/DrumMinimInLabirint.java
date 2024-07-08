import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DrumMinimInLabirint {
    // Clasa pentru a reprezenta o locatie in labirint (coordonate linie si coloana)
    static class Locatie {
        int linie, coloana;

        public Locatie(int linie, int coloana) {
            this.linie = linie;
            this.coloana = coloana;
        }

        @Override
        public String toString() {
            return linie + " " + coloana;
        }
    }

    public static void main(String[] args) {
        String inputFileName = "InputL9/input4.txt";
        String outputFileName = "output.txt";

        try (Scanner scan = new Scanner(new File(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            int n = scan.nextInt(); // Numarul de linii in labirint
            int m = scan.nextInt(); // Numarul de coloane in labirint
            scan.nextLine(); // Trecem la urmatoarea linie

            int[][] a = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] = 0;
                }
            }

            // Citim labirintul si marcam peretii (valoarea -1)
            for (int i = 0; i < n; i++) {
                String linie = scan.nextLine();
                int j = 0;
                int ind = 0;
                while (ind < linie.length()) {
                    if (linie.charAt(ind) == '-') {
                        a[i][j] = -1;
                        ind += 1;
                    }
                    j += 1;
                    ind += 1;
                }
            }

            // Citim punctele de start si de stop
            int startX = scan.nextInt();
            int startY = scan.nextInt();
            scan.nextLine();
            int endX = scan.nextInt();
            int endY = scan.nextInt();

            Locatie start = new Locatie(startX, startY);
            Locatie end = new Locatie(endX, endY);

            // Aplicam algoritmul Lee pentru a gasi drumul minim
            List<Locatie> lista = DrumMinim_Lee(a, start, end);
            int size = lista.size();
            writer.write(size + "\n");
            for (Locatie x : lista) {
                writer.write(x.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Algoritmul Lee pentru gasirea drumului minim intr-un labirint
    public static List<Locatie> DrumMinim_Lee(int[][] labirint, Locatie start, Locatie stop) {
        int m = labirint.length; // Numarul de linii in labirint
        int n = labirint[0].length; // Numarul de coloane in labirint

        int[] dLinie = {0, 0, 1, -1}; // Vector pentru a naviga pe linii (dreapta, stanga, jos, sus)
        int[] dColoana = {1, -1, 0, 0}; // Vector pentru a naviga pe coloane (dreapta, stanga, jos, sus)

        Queue<Locatie> coada = new LinkedList<>();
        coada.offer(start);
        labirint[start.linie][start.coloana] = 1; // Marcam punctul de start cu 1

        // Parcurgem labirintul cu algoritmul Lee
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = curent.linie + dLinie[t];
                int coloanaVecin = curent.coloana + dColoana[t];
                // Verificam vecinii si adaugam in coada cei care pot fi parcursi
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && labirint[linieVecin][coloanaVecin] == 0) {
                    labirint[linieVecin][coloanaVecin] = labirint[curent.linie][curent.coloana] + 1;
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                }
            }
        }

        // Construim drumul minim folosind informatiile din labirint
        List<Locatie> drum = new ArrayList<>();
        int lungimeDrum = labirint[stop.linie][stop.coloana];
        int linie = stop.linie;
        int coloana = stop.coloana;
        while (!(linie == start.linie && coloana == start.coloana)) {
            drum.add(new Locatie(linie, coloana));
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = linie + dLinie[t];
                int coloanaVecin = coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && labirint[linieVecin][coloanaVecin] == lungimeDrum - 1) {
                    linie = linieVecin;
                    coloana = coloanaVecin;
                    lungimeDrum--;
                    break;
                }
            }
        }

        drum.add(start); // Adaugam punctul de start la drum
        Collections.reverse(drum); // Inversam drumul pentru a fi in ordinea corecta
        return drum;
    }
}
