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

public class CalulDeSah {
    // Clasa pentru a reprezenta o locatie pe tabla
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
        try {
            Scanner scan = new Scanner(new File("InputL7/input6.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Citirea dimensiunilor tablei si a pozitiilor calului si a regelui
            int m = scan.nextInt();
            int n = scan.nextInt();
            scan.nextLine(); // Consumam newline-ul
            char[][] a = new char[m][n];
            Locatie cal = null;
            Locatie rege = null;
            for (int i = 0; i < m; i++) {
                String linie = scan.nextLine();
                for (int j = 0; j < n; j++) {
                    a[i][j] = linie.charAt(j);
                    if (a[i][j] == 'C') {
                        cal = new Locatie(i, j);
                    } else if (a[i][j] == 'R') {
                        rege = new Locatie(i, j);
                    }
                }
            }

            // Calcularea si scrierea drumului minim pentru varianta 1
            List<Locatie> drum1 = DrumMinim_Lee(a, cal, rege);
            writer.write(distanta(cal, drum1.get(0)) + "\n");
            for (Locatie loc : drum1) {
                writer.write(loc.toString() + "\n");
            }

            // Calcularea si scrierea drumului minim pentru varianta 2
            List<Locatie> drum2 = DrumMinim_Lee_CuDama(a, cal, rege);
            writer.write(distanta(cal, drum2.get(0)) + "\n");
            for (Locatie loc : drum2) {
                writer.write(loc.toString() + "\n");
            }

            scan.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Functie pentru a calcula drumul minim intre doua locatii folosind algoritmul lui Lee
    public static List<Locatie> DrumMinim_Lee(char[][] tabla, Locatie start, Locatie stop) {
        int m = tabla.length;
        int n = tabla[0].length;
        int[] dLinie = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] dColoana = {2, -2, 1, -1, 2, -2, 1, -1};
        Queue<Locatie> coada = new LinkedList<>();
        coada.offer(start);
        tabla[start.linie][start.coloana] = 'X'; // Marcam locatia calului ca fiind vizitata
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = curent.linie + dLinie[t];
                int coloanaVecin = curent.coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && tabla[linieVecin][coloanaVecin] == '0') {
                    tabla[linieVecin][coloanaVecin] = (char) (tabla[curent.linie][curent.coloana] + 1);
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                }
            }
        }
        // Construim drumul
        List<Locatie> drum = new ArrayList<>();
        int distantaMinima = tabla[stop.linie][stop.coloana] - 'X';
        int linie = stop.linie;
        int coloana = stop.coloana;
        while (!(linie == start.linie && coloana == start.coloana)) {
            drum.add(new Locatie(linie, coloana));
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = linie + dLinie[t];
                int coloanaVecin = coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && tabla[linieVecin][coloanaVecin] == distantaMinima - 1) {
                    linie = linieVecin;
                    coloana = coloanaVecin;
                    distantaMinima--;
                    break;
                }
            }
        }
        drum.add(start);
        Collections.reverse(drum);
        return drum;
    }

    // Functie pentru a calcula drumul minim intre doua locatii folosind algoritmul lui Lee cu restrictia data de pozitia damei
    public static List<Locatie> DrumMinim_Lee_CuDama(char[][] tabla, Locatie start, Locatie stop) {
        int m = tabla.length;
        int n = tabla[0].length;
        int[] dLinie = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] dColoana = {2, -2, 1, -1, 2, -2, 1, -1};
        Queue<Locatie> coada = new LinkedList<>();
        coada.offer(start);
        boolean[][] visited = new boolean[m][n];
        visited[start.linie][start.coloana] = true;
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = curent.linie + dLinie[t];
                int coloanaVecin = curent.coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && tabla[linieVecin][coloanaVecin] == '0' && !visited[linieVecin][coloanaVecin]) {
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                    visited[linieVecin][coloanaVecin] = true;
                }
            }
        }
        // Construim drumul
        List<Locatie> drum = new ArrayList<>();
        int distantaMinima = distanta(start, stop);
        int linie = stop.linie;
        int coloana = stop.coloana;
        while (!(linie == start.linie && coloana == start.coloana)) {
            drum.add(new Locatie(linie, coloana));
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = linie + dLinie[t];
                int coloanaVecin = coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && distanta(new Locatie(linieVecin, coloanaVecin), start) < distantaMinima) {
                    linie = linieVecin;
                    coloana = coloanaVecin;
                    distantaMinima = distanta(new Locatie(linie, coloana), start);
                    break;
                }
            }
        }
        drum.add(start);
        Collections.reverse(drum);
        return drum;
    }

    // Functie pentru a calcula distanta Manhattan intre doua locatii
    public static int distanta(Locatie a, Locatie b) {
        return Math.abs(a.linie - b.linie) + Math.abs(a.coloana - b.coloana);
    }
}
