import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TurnulDeCutii1 {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL5/input6.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            // Citirea numarului de cutii
            int n = scanner.nextInt();
            ArrayList<Box> boxes = new ArrayList<>();

            // Citirea dimensiunilor fiecarei cutii si crearea obiectelor Box
            for (int i = 0; i < n; i++) {
                int length = scanner.nextInt();
                int width = scanner.nextInt();
                int height = scanner.nextInt();
                boxes.add(new Box(length, width, height));
            }

            // Sortarea cutiilor dupa arie
            Collections.sort(boxes);

            // Calcularea inaltimii maxime a turnului si cutiilor care alcatuiesc inaltimea maxima
            ArrayList<Box> maxHeightBoxes = calculateMaxHeightBoxes(boxes);
            int maxHeight = calculateMaxHeight(boxes);

            // Scrierea rezultatului in fisierul de iesire
            writer.println(maxHeight);
            for (Box box : maxHeightBoxes) {
                writer.println(box.length + " " + box.width + " " + box.height);
            }

            // Inchiderea fluxurilor de date
            scanner.close();
            writer.close();

        } catch (FileNotFoundException e) {
            // Tratarea cazului in care fisierul de intrare nu poate fi gasit
            e.printStackTrace();
        }
    }

    // Functia pentru calcularea inaltimii maxime a turnului
    private static int calculateMaxHeight(ArrayList<Box> boxes) {
        int n = boxes.size();
        int[] dp = new int[n];
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = boxes.get(i).height;
            for (int j = 0; j < i; j++) {
                // Verificarea daca cutia curenta poate fi asezata deasupra cutiei de la indexul j
                if (boxes.get(i).canBePlacedAbove(boxes.get(j))) {
                    dp[i] = Math.max(dp[i], dp[j] + boxes.get(i).height);
                }
            }
            maxHeight = Math.max(maxHeight, dp[i]);
        }

        return maxHeight;
    }

    // Functia pentru identificarea cutiilor care alcatuiesc inaltimea maxima a turnului
    private static ArrayList<Box> calculateMaxHeightBoxes(ArrayList<Box> boxes) {
        int n = boxes.size();
        int[] dp = new int[n];
        ArrayList<Box> maxHeightBoxes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            dp[i] = boxes.get(i).height;
            for (int j = 0; j < i; j++) {
                if (boxes.get(i).canBePlacedAbove(boxes.get(j))) {
                    if (dp[i] < dp[j] + boxes.get(i).height) {
                        dp[i] = dp[j] + boxes.get(i).height;
                        maxHeightBoxes.add(boxes.get(i));
                    }
                }
            }
        }

        return maxHeightBoxes;
    }

    // Clasa pentru reprezentarea unei cutii
    static class Box implements Comparable<Box> {
        int length;
        int width;
        int height;

        public Box(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }

        // Functia pentru calcularea ariei fetei cutiei
        public int getFaceArea() {
            return length * width;
        }

        // Functia pentru verificarea daca cutia curenta poate fi asezata deasupra cutiei other
        public boolean canBePlacedAbove(Box other) {
            return this.getFaceArea() <= other.getFaceArea();
        }

        // Implementarea metodei compareTo pentru sortarea cutiilor dupa arie
        @Override
        public int compareTo(Box other) {
            return this.getFaceArea() - other.getFaceArea();
        }
    }
}
