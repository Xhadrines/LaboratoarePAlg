import java.io.*;
import java.util.*;

public class IncaperiSecrete {
    public static void main(String[] args) {
        try {
            // Citim datele de intrare dintr-un fisier.
            Scanner scanner = new Scanner(new File("InputL7/input2.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            
            // Citim dimensiunile labirintului.
            int n2 = scanner.nextInt();
            int m2 = scanner.nextInt();
            scanner.nextLine();
            int[][] a2 = new int[n2][m2];
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < m2; j++) {
                    a2[i][j] = 0;
                }
            }
            
            // Initializam matricea labirintului si o populam cu informatiile din fisier.
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
            
            // Apelam functia pentru a calcula numarul de incaperi secrete si scriem rezultatul in fisierul de iesire.
            int nr = incaperiSecrete(a2, m2, n2);
            writer.write(nr + "");
            
            // Inchidem fisierele.
            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Functia pentru a calcula numarul de incaperi secrete.
    public static int incaperiSecrete(int[][] a, int n, int m) {
        int nr = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    nr = nr + 1;
                    // Apelam functia LEEincaperi pentru a marca si numara incaperile secrete.
                    LEEincaperi(a, n, m, i, j, nr);
                }
            }
        }
        return nr;
    }

    // Functia LEEincaperi pentru a marca si numara incaperile secrete folosind algoritmul Lee.
    public static void LEEincaperi(int[][] a, int n, int m, int i, int j, int nr) {
        int[] dLinie = {0, 0, 1, -1};
        int[] dColoana = {1, -1, 0, 0};
        
        a[i][j] = 1;
        Queue<Locatie> coada = new LinkedList<>();
        coada.add(new Locatie(i, j));
        
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = curent.Linie + dLinie[t];
                int coloanaVecin = curent.Coloana + dColoana[t];
                // Verificam vecinii pentru a marca si numara incaperile secrete.
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && a[linieVecin][coloanaVecin] == 0) {
                    a[linieVecin][coloanaVecin] = a[curent.Linie][curent.Coloana] + 1;
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                }
            }
        }
    }

    // Clasa Locatie pentru a reprezenta o locatie in labirint.
    static class Locatie {
        int Linie, Coloana;

        public Locatie(int linie, int coloana) {
            Linie = linie;
            Coloana = coloana;
        }

        @Override
        public String toString() {
            return Linie + " " + Coloana;
        }
    }

    // Functie pentru a calcula drumul minim in labirint folosind algoritmul Lee.
    public static List<Locatie> DrumMinim_Lee(int[][] labirint, Locatie start, Locatie stop) {
        int m = labirint.length;
        int n = labirint[0].length;
        
        int[] dLinie = {0, 0, 1, -1};
        int[] dColoana = {1, -1, 0, 0};
        
        Queue<Locatie> coada = new LinkedList<>();
        coada.offer(start);
        labirint[start.Linie][start.Coloana] = 1;
        
        while (!coada.isEmpty()) {
            Locatie curent = coada.poll();
            for (int t = 0; t < dLinie.length; t++) {
                int linieVecin = curent.Linie + dLinie[t];
                int coloanaVecin = curent.Coloana + dColoana[t];
                if (linieVecin >= 0 && linieVecin < m && coloanaVecin >= 0 && coloanaVecin < n && labirint[linieVecin][coloanaVecin] == 0) {
                    labirint[linieVecin][coloanaVecin] = labirint[curent.Linie][curent.Coloana] + 1;
                    coada.offer(new Locatie(linieVecin, coloanaVecin));
                }
            }
        }
        
        List<Locatie> drum = new ArrayList<>();
        int lungimeDrum = labirint[stop.Linie][stop.Coloana];
        int linie = stop.Linie;
        int coloana = stop.Coloana;
        while (!(linie == start.Linie && coloana == start.Coloana)) {
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
        
        drum.add(start);
        Collections.reverse(drum);
        return drum;
    }
}
