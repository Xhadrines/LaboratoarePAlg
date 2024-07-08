import java.io.*;
import java.util.*;

public class CelMaiScurtDrumInLabirint {
    // Definim o clasa pentru a reprezenta o locatie in labirint, cu coordonatele ei.
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
            // Citim datele de intrare dintr-un fisier.
            Scanner scan = new Scanner(new File("InputL7/input1.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Citim dimensiunile labirintului.
            int n = scan.nextInt();
            int m = scan.nextInt();
            scan.nextLine();

            // Initializam matricea labirintului si o populam cu informatiile din fisier.
            int[][] a = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] = 0;
                }
            }

            // Citim descrierea labirintului din fisier si o salvam in matricea labirintului.
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

            // Citim pozitiile de start si de final din fisier.
            int startX = scan.nextInt();
            int startY = scan.nextInt();
            scan.nextLine();
            int endX = scan.nextInt();
            int endY = scan.nextInt();

            // Initializam locatiile de start si de final.
            Locatie start = new Locatie(startX, startY);
            Locatie end = new Locatie(endX, endY);

            // Apelam functia DrumMinim_Lee pentru a gasi cel mai scurt drum in labirint.
            List<Locatie> lista = DrumMinim_Lee(a, start, end);
            int size = lista.size();
            
            // Scriem rezultatele in fisierul de iesire.
            writer.write(size + "\n");
            for (Locatie x : lista) {
                writer.write(x.toString() + "\n");
            }

            // Inchidem fisierele.
            scan.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Implementam algoritmul Lee pentru a gasi cel mai scurt drum in labirint.
    public static List<Locatie> DrumMinim_Lee(int[][] labirint, Locatie start, Locatie stop) {
        int m = labirint.length;
        int n = labirint[0].length;
        
        // Definim vectorii de deplasare posibila in sus, jos, stanga si dreapta.
        int[] dLinie = {0, 0, 1, -1};
        int[] dColoana = {1, -1, 0, 0};
        
        // Initializam o coada pentru explorarea nodurilor in labirint.
        Queue<Locatie> coada = new LinkedList<>();
        coada.offer(start);
        labirint[start.linie][start.coloana] = 1;
        
        // Parcurgem labirintul folosind algoritmul Lee.
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
        
        // Reconstruim drumul minim folosind distantele calculate.
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
        
        // Adaugam locatia de start in drum si returnam drumul inversat.
        drum.add(start);
        Collections.reverse(drum);
        return drum;
    }
}
