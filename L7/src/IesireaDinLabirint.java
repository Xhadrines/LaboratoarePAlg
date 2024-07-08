import java.io.*;
import java.util.*;

public class IesireaDinLabirint {
    // Clasa pentru a reprezenta o locatie in labirint, cu coordonatele ei.
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
            Scanner scan = new Scanner(new File("InputL7/input4.txt"));
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

            // Citim pozitia de start din fisier.
            int startX = scan.nextInt();
            int startY = scan.nextInt();
            //scan.nextLine();
            Locatie start = new Locatie(startX, startY);

            // Determinam drumul minim pana la iesire.
            List<Locatie> lista = DrumMinim_Lee(a, start);
            
            // Verificam daca exista o iesire din labirint.
            boolean exitFound = false;
            for (Locatie loc : lista) {
                if (loc.linie == 0 || loc.linie == n - 1 || loc.coloana == 0 || loc.coloana == m - 1) {
                    exitFound = true;
                    break;
                }
            }

            if (exitFound) {
                // Daca exista, afisam lungimea drumului minim si locatia de iesire.
                writer.write(lista.size() + "\n");
                writer.write(lista.get(lista.size() - 1).toString());
            } else {
                // Daca nu exista, afisam un mesaj corespunzator.
                writer.write("Suntem blocati in labirint");
            }

            // Inchidem fisierele.
            scan.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Functia pentru a gasi drumul minim folosind algoritmul Lee.
    public static List<Locatie> DrumMinim_Lee(int[][] labirint, Locatie start) {
        int m = labirint.length;
        int n = labirint[0].length;
        
        int[] dLinie = {0, 0, 1, -1};
        int[] dColoana = {1, -1, 0, 0};
        
        Queue<Locatie> coada = new LinkedList<>();
        coada.offer(start);
        labirint[start.linie][start.coloana] = 1;
        
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
        
        List<Locatie> drum = new ArrayList<>();
        int lungimeDrum = labirint[start.linie][start.coloana];
        int linie = start.linie;
        int coloana = start.coloana;
        while (lungimeDrum > 1) {
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
