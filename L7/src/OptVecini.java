import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class OptVecini {
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
            // Citirea datelor de intrare din fisier
            Scanner scan = new Scanner(new File("InputL7/input3.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            int n = scan.nextInt();
            int m = scan.nextInt();
            scan.nextLine();

            int[][] a = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] = 0;
                }
            }

            // Initializarea si construirea labirintului
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

            // Citirea si initializarea pozitiilor de start si stop
            int startX = scan.nextInt();
            int startY = scan.nextInt();
            scan.nextLine();
            int endX = scan.nextInt();
            int endY = scan.nextInt();

            Locatie start = new Locatie(startX, startY);
            Locatie end = new Locatie(endX, endY);

            // Calcularea tuturor drumurilor minime posibile folosind algoritmul Lee
            List<List<Locatie>> toateDrumurile = DrumuriMinime_Lee(a, start, end);
            
            // Scrierea rezultatului in fisierul de iesire
            writer.write(toateDrumurile.size() + "\n");
            for (List<Locatie> drum : toateDrumurile) {
                writer.write(drum.size() + "\n");
                for (Locatie x : drum) {
                    writer.write(x.toString() + "\n");
                }
            }

            // Inchiderea fisierelor
            scan.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Functie pentru a calcula toate drumurile minime folosind algoritmul Lee
    public static List<List<Locatie>> DrumuriMinime_Lee(int[][] labirint, Locatie start, Locatie stop) {
        int m = labirint.length;
        int n = labirint[0].length;

        int[] dLinie = {0, 0, 1, -1, 1, -1, 1, -1};
        int[] dColoana = {1, -1, 0, 0, 1, -1, -1, 1};

        Queue<Locatie> coada = new LinkedList<>();
        coada.offer(start);
        labirint[start.linie][start.coloana] = 1;

        List<List<Locatie>> toateDrumurile = new ArrayList<>();
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = curent.linie + dLinie[t];
                int coloanaVecin = curent.coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && labirint[linieVecin][coloanaVecin] == 0) {
                    labirint[linieVecin][coloanaVecin] = labirint[curent.linie][curent.coloana] + 1;
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                }
            }
        }

        // Construirea tuturor drumurilor minime folosind recursivitate
        int lungimeDrum = labirint[stop.linie][stop.coloana];
        for (int t = 0; t < dLinie.length; t++) {
            int linieVecin = stop.linie + dLinie[t];
            int coloanaVecin = stop.coloana + dColoana[t];
            if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && labirint[linieVecin][coloanaVecin] == lungimeDrum - 1) {
                Locatie next = new Locatie(linieVecin, coloanaVecin);
                List<Locatie> drum = new ArrayList<>();
                drum.add(next);
                toateDrumurile.add(drum);
                ConstruiesteDrum(labirint, start, next, drum, toateDrumurile);
            }
        }

        return toateDrumurile;
    }

    // Functie recursiva pentru construirea tuturor drumurilor minime
    public static void ConstruiesteDrum(int[][] labirint, Locatie start, Locatie stop, List<Locatie> drumCurent, List<List<Locatie>> toateDrumurile) {
        int m = labirint.length;
        int n = labirint[0].length;

        int[] dLinie = {0, 0, 1, -1, 1, -1, 1, -1};
        int[] dColoana = {1, -1, 0, 0, 1, -1, -1, 1};

        int lungimeDrum = labirint[stop.linie][stop.coloana];
        for (int t = 0; t < dLinie.length; t++) {
            int linieVecin = stop.linie + dLinie[t];
            int coloanaVecin = stop.coloana + dColoana[t];
            if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && labirint[linieVecin][coloanaVecin] == lungimeDrum - 1) {
                Locatie next = new Locatie(linieVecin, coloanaVecin);
                List<Locatie> drumNou = new ArrayList<>(drumCurent);
                drumNou.add(next);
                toateDrumurile.add(drumNou);
                ConstruiesteDrum(labirint, start, next, drumNou, toateDrumurile);
            }
        }
    }
}
