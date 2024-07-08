import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class VrajitoriSiComori {
    public static void main(String[] args) {
        try {
            // Citirea din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL7/input5.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Citirea dimensiunilor matricei
            int n2 = scanner.nextInt();
            int m2 = scanner.nextInt();
            scanner.nextLine();

            // Initializarea matricei si umplerea cu valori implicite
            int[][] a2 = new int[n2][m2];
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < m2; j++) {
                    a2[i][j] = 0;
                }
            }

            // Citirea matricei din fisier
            for (int i = 0; i < n2; i++) {
                String linie = scanner.nextLine();
                int j = 0;
                int ind = 0;
                while (ind < linie.length()) {
                    if (linie.charAt(ind) == '-') {
                        a2[i][j] = -1;
                        ind += 1;
                    }
                    j += 1;
                    ind += 1;
                }
            }

            // Gasirea numarului de incaperi secrete si scrierea rezultatului in fisierul de iesire
            int nr = incaperiSecrete(a2, m2, n2);
            writer.write(nr + "");

            // Inchiderea fluxurilor de citire si scriere
            scanner.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru gasirea numarului de incaperi secrete
    public static int incaperiSecrete(int[][] a, int n, int m) {
        int nr = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0 && !visited[i][j]) {
                    nr += LEEincaperi(a, visited, n, m, i, j);
                }
            }
        }
        return nr;
    }

    // Metoda pentru parcurgerea in latime a unei incaperi
    public static int LEEincaperi(int[][] a, boolean[][] visited, int n, int m, int i, int j) {
        int nr = 0;
        // Vectorii pentru deplasarea in sus, jos, stanga si dreapta, respectiv diagonale
        int[] dLinie = new int[]{0, 0, 1, -1, -1, -1, 1, 1};
        int[] dColoana = new int[]{1, -1, 0, 0, -1, 1, -1, 1};
        Queue<Locatie> coada = new LinkedList<>();
        coada.add(new Locatie(i, j));
        visited[i][j] = true;
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            int linie = curent.linie;
            int coloana = curent.coloana;
            if (a[linie][coloana] == 0) {
                nr++;
            }
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = linie + dLinie[t];
                int coloanaVecin = coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && a[linieVecin][coloanaVecin] != -1 && !visited[linieVecin][coloanaVecin]) {
                    visited[linieVecin][coloanaVecin] = true;
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                }
            }
        }
        return nr;
    }

    // Clasa pentru reprezentarea unei locatii in matrice
    public static class Locatie {
        public int linie, coloana;

        public Locatie(int linie, int coloana) {
            this.linie = linie;
            this.coloana = coloana;
        }

        @Override
        public String toString() {
            return linie + " " + coloana;
        }
    }
}
