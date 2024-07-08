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

public class CeiDoiIndragostiti {
    // Definim o clasa interna pentru reprezentarea unei locatii in labirint.
    static class Locatie {
        int linie, coloana;

        // Constructorul clasei Locatie.
        public Locatie(int linie, int coloana) {
            this.linie = linie;
            this.coloana = coloana;
        }

        // Metoda pentru afisarea informatiilor despre locatie sub forma de string.
        @Override
        public String toString() {
            return linie + " " + coloana;
        }
    }

    // Metoda principala care va fi apelata la rularea programului.
    public static void main(String[] args) {
        try {
            // Scanner pentru citirea din fisierul de intrare.
            Scanner scan = new Scanner(new File("InputL7/input7.txt"));
            // BufferedWriter pentru scrierea in fisierul de iesire.
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Citim dimensiunile labirintului.
            int m = scan.nextInt();
            int n = scan.nextInt();
            scan.nextLine();

            // Initializam matricea labirintului cu valorile din fisierul de intrare.
            int[][] labirint = new int[m][n];
            for (int i = 0; i < m; i++) {
                String linie = scan.nextLine();
                for (int j = 0; j < n; j++) {
                    labirint[i][j] = linie.charAt(j) == '-' ? -1 : 0;
                }
            }

            // Citim locatiile celor doi indragostiti.
            Locatie indragostit1 = new Locatie(scan.nextInt(), scan.nextInt());
            Locatie indragostit2 = new Locatie(scan.nextInt(), scan.nextInt());

            // Gasim punctul de intalnire si drumurile minime catre acesta.
            List<Locatie> puncteIntalnire = gasestePuncteIntalnire(labirint, indragostit1, indragostit2);
            Locatie punctIntalnire = puncteIntalnire.get(0);
            List<Locatie> drum1 = DrumMinim_Lee(labirint, indragostit1, punctIntalnire);
            List<Locatie> drum2 = DrumMinim_Lee(labirint, indragostit2, punctIntalnire);

            // Scriem rezultatele in fisierul de iesire.
            writer.write(drum1.size() + "\n");
            writer.write(punctIntalnire.toString() + "\n");
            writer.write(drum2.size() + "\n");
            for (Locatie loc : drum1) {
                writer.write(loc.toString() + "\n");
            }
            for (Locatie loc : drum2) {
                writer.write(loc.toString() + "\n");
            }

            // Inchidem resursele de citire si scriere.
            scan.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda pentru gasirea drumului minim folosind algoritmul lui Lee.
    public static List<Locatie> DrumMinim_Lee(int[][] labirint, Locatie start, Locatie stop) {
        // Initializam dimensiunile labirintului.
        int m = labirint.length;
        int n = labirint[0].length;
        // Definim vectorii de deplasare pentru a naviga in labirint.
        int[] dLinie = {0, 0, 1, -1};
        int[] dColoana = {1, -1, 0, 0};
        // Initializam o coada pentru parcurgerea labirintului in latime.
        Queue<Locatie> coada = new LinkedList<>();
        // Adaugam pozitia de start in coada.
        coada.offer(start);
        // Marcam pozitia de start in labirint cu valoarea 1.
        labirint[start.linie][start.coloana] = 1;
        
        // Parcurgem labirintul pana cand coada devine goala.
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            // Parcurgem vecinii pozitiei curente.
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = curent.linie + dLinie[t];
                int coloanaVecin = curent.coloana + dColoana[t];
                // Verificam daca vecinul este valid si nevizitat.
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && labirint[linieVecin][coloanaVecin] == 0) {
                    // Marcam vecinul ca vizitat si calculam distanta pana la el.
                    labirint[linieVecin][coloanaVecin] = labirint[curent.linie][curent.coloana] + 1;
                    // Adaugam vecinul in coada pentru a fi explorat mai departe.
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                }
            }
        }
        // Construim drumul minim pornind de la pozitia de stop.
        List<Locatie> drum = new ArrayList<>();
        int lungimeDrum = labirint[stop.linie][stop.coloana];
        int linie = stop.linie;
        int coloana = stop.coloana;
        while (!(linie == start.linie && coloana == start.coloana)) {
            drum.add(new Locatie(linie, coloana));
            // Parcurgem vecinii pozitiei curente pentru a gasi pozitia precedenta din drum.
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
        // Adaugam pozitia de start in drum si il intoarcem, deoarece am construit drumul de la sfarsit catre inceput.
        drum.add(start);
        Collections.reverse(drum);
        return drum;
    }

    // Metoda pentru gasirea punctelor de intalnire posibile intre cei doi indragostiti.
    public static List<Locatie> gasestePuncteIntalnire(int[][] labirint, Locatie start1, Locatie start2) {
        int m = labirint.length;
        int n = labirint[0].length;
        // Initializam matricele de vizitare pentru fiecare indragostit.
        boolean[][] vizitat1 = new boolean[m][n];
        boolean[][] vizitat2 = new boolean[m][n];
        // Initializam cozi pentru explorarea labirintului de catre fiecare indragostit.
        Queue<Locatie> coada1 = new LinkedList<>();
        Queue<Locatie> coada2 = new LinkedList<>();
        coada1.offer(start1);
        coada2.offer(start2);
        // Marcham pozitiile de start ca fiind vizitate pentru fiecare indragostit.
        vizitat1[start1.linie][start1.coloana] = true;
        vizitat2[start2.linie][start2.coloana] = true;
        // Initializam o lista pentru a stoca punctele de intalnire posibile.
        List<Locatie> puncteIntalnire = new ArrayList<>();
        // Parcurgem labirintul pana cand ambele cozi sunt goale.
        while (!coada1.isEmpty() || !coada2.isEmpty()) {
            // Exploram pozitia curenta din coada 1.
            Locatie curent1 = coada1.poll();
            // Verificam daca pozitia curenta din coada 1 este vizitata de catre indragostitul 2 si o adaugam in lista de intalnire.
            if (vizitat2[curent1.linie][curent1.coloana]) {
                puncteIntalnire.add(curent1);
            }
            // Exploram pozitia curenta din coada 2.
            Locatie curent2 = coada2.poll();
            // Verificam daca pozitia curenta din coada 2 este vizitata de catre indragostitul 1 si o adaugam in lista de intalnire.
            if (vizitat1[curent2.linie][curent2.coloana]) {
                puncteIntalnire.add(curent2);
            }
            // Parcurgem vecinii pozitiilor curente pentru a gasi pozitiile adiacente nevizitate.
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (Math.abs(i) != Math.abs(j)) {
                        int linieVecin1 = curent1.linie + i;
                        int coloanaVecin1 = curent1.coloana + j;
                        int linieVecin2 = curent2.linie + i;
                        int coloanaVecin2 = curent2.coloana + j;
                        // Verificam daca vecinul este valid si nevizitat de catre fiecare indragostit.
                        if (linieVecin1 >= 0 && linieVecin1 < m && coloanaVecin1 >= 0 && coloanaVecin1 < n && labirint[linieVecin1][coloanaVecin1] == 0 && !vizitat1[linieVecin1][coloanaVecin1]) {
                            coada1.offer(new Locatie(linieVecin1, coloanaVecin1));
                            vizitat1[linieVecin1][coloanaVecin1] = true;
                        }
                        if (linieVecin2 >= 0 && linieVecin2 < m && coloanaVecin2 >= 0 && coloanaVecin2 < n && labirint[linieVecin2][coloanaVecin2] == 0 && !vizitat2[linieVecin2][coloanaVecin2]) {
                            coada2.offer(new Locatie(linieVecin2, coloanaVecin2));
                            vizitat2[linieVecin2][coloanaVecin2] = true;
                        }
                    }
                }
            }
        }
        // Returnam lista de puncte de intalnire.
        return puncteIntalnire;
    }
}
