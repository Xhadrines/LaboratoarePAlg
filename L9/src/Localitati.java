import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Localitati {
    public static void main(String[] args) {
        String inputFileName = "InputL9/input3.txt";
        String outputFileName = "output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {

            // Citire numar de localitati
            int n = Integer.parseInt(reader.readLine());

            // Citire matrice de distante
            int[][] distante = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] line = reader.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    distante[i][j] = Integer.parseInt(line[j]);
                }
            }

            // Citire localitati A si B si distanta maxima Dmax
            String[] lastLine = reader.readLine().split(" ");
            int localitateA = Integer.parseInt(lastLine[0]);
            int localitateB = Integer.parseInt(lastLine[1]);
            int Dmax = Integer.parseInt(lastLine[2]);

            // Verificare daca exista solutie
            if (distanteMinime(distante, localitateA, localitateB, Dmax)) {
                // Apelam functia pentru a gasi drumul
                int[] drum = gasesteDrumul(distante, localitateA, localitateB, Dmax);
                // Scriem drumul in fisier
                for (int i = 0; i < drum.length; i++) {
                    writer.print(drum[i]);
                    if (i < drum.length - 1) {
                        writer.print(" ");
                    }
                }
            } else {
                writer.println("Nu exista solutie");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Verifica daca exista drum cu distanta minima mai mica sau egala cu Dmax
    public static boolean distanteMinime(int[][] distante, int A, int B, int Dmax) {
        int[] distantaMinima = new int[distante.length];
        boolean[] vizitat = new boolean[distante.length];
        Arrays.fill(distantaMinima, Integer.MAX_VALUE);
        Arrays.fill(vizitat, false);

        distantaMinima[A] = 0;

        for (int count = 0; count < distante.length - 1; count++) {
            int u = gasesteMinim(distantaMinima, vizitat);
            vizitat[u] = true;
            for (int v = 0; v < distante.length; v++) {
                if (!vizitat[v] && distante[u][v] != 0 && distantaMinima[u] != Integer.MAX_VALUE
                        && distantaMinima[u] + distante[u][v] < distantaMinima[v]) {
                    distantaMinima[v] = distantaMinima[u] + distante[u][v];
                }
            }
        }

        return distantaMinima[B] <= Dmax;
    }

    // Gaseste nodul cu cea mai mica distanta nevizitata
    public static int gasesteMinim(int[] distante, boolean[] vizitat) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < distante.length; i++) {
            if (!vizitat[i] && distante[i] <= min) {
                min = distante[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    // Gaseste drumul cu distanta minima intre localitatea A si B
    public static int[] gasesteDrumul(int[][] distante, int A, int B, int Dmax) {
        int[] drum = new int[distante.length];
        int[] distantaMinima = new int[distante.length];
        boolean[] vizitat = new boolean[distante.length];
        Arrays.fill(distantaMinima, Integer.MAX_VALUE);
        Arrays.fill(vizitat, false);

        distantaMinima[A] = 0;
        drum[A] = -1;

        for (int count = 0; count < distante.length - 1; count++) {
            int u = gasesteMinim(distantaMinima, vizitat);
            vizitat[u] = true;
            for (int v = 0; v < distante.length; v++) {
                if (!vizitat[v] && distante[u][v] != 0 && distantaMinima[u] != Integer.MAX_VALUE && distantaMinima[u] + distante[u][v] < distantaMinima[v]) {
                    distantaMinima[v] = distantaMinima[u] + distante[u][v];
                    drum[v] = u;
                }
            }
        }

        // Construim drumul de la B la A
        int index = B;
        int lungimeDrum = 0;
        while (index != -1) {
            lungimeDrum++;
            index = drum[index];
        }

        // Verificam daca lungimea drumului este mai mica sau egala cu Dmax
        if (lungimeDrum <= Dmax) {
            // Reconstruim drumul
            int[] drumFinal = new int[lungimeDrum];
            index = B;
            for (int i = lungimeDrum - 1; i >= 0; i--) {
                drumFinal[i] = index;
                index = drum[index];
            }
            return drumFinal;
        } else {
            return null; // Nu exista solutie
        }
    }
}
