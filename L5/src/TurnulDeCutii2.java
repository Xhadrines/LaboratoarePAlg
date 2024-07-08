import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TurnulDeCutii2 {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL5/input8.txt"));
            PrintWriter writer = new PrintWriter("output.txt");

            int n = scanner.nextInt();
            scanner.nextLine();

            // Citirea datelor despre cutii si crearea obiectelor Box
            ArrayList<Box> boxes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int weightSupported = scanner.nextInt();
                int weight = scanner.nextInt();
                boxes.add(new Box(weightSupported, weight));
            }

            // Gasirea celui mai lung turn solid si scrierea rezultatului in fisierul de iesire
            ArrayList<Box> longestTower = findLongestSolidTower(boxes);
            writer.println(longestTower.size());
            for (Box box : longestTower) {
                writer.println(box.weightSupported + " " + box.weight);
            }

            // Inchiderea fluxurilor de date
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // Tratarea cazului in care fisierul de intrare nu poate fi gasit
            e.printStackTrace();
        }
    }

    // Functia pentru gasirea celui mai lung turn solid
    private static ArrayList<Box> findLongestSolidTower(ArrayList<Box> boxes) {
        // Sortarea cutiilor dupa greutatea suportata
        Collections.sort(boxes);

        // Variabila pentru stocarea celui mai lung turn solid
        ArrayList<Box> longestTower = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            // Construirea unui turn incepand cu cutia de la indexul i
            ArrayList<Box> tower = buildTower(i, boxes);
            // Actualizarea celui mai lung turn solid
            if (tower.size() > longestTower.size()) {
                longestTower = tower;
            }
        }

        return longestTower;
    }

    // Functia pentru construirea unui turn solid incepand cu cutia de la indexul bottomIndex
    private static ArrayList<Box> buildTower(int bottomIndex, ArrayList<Box> boxes) {
        ArrayList<Box> tower = new ArrayList<>();
        tower.add(boxes.get(bottomIndex));
        int weightSupported = boxes.get(bottomIndex).weightSupported;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            // Adaugarea cutiilor care pot fi asezate deasupra cutiei de la indexul bottomIndex
            if (boxes.get(i).weight <= weightSupported) {
                tower.add(boxes.get(i));
                // Actualizarea greutatii suportate a turnului
                weightSupported = Math.min(weightSupported, boxes.get(i).weightSupported);
            }
        }
        return tower;
    }

    // Clasa pentru reprezentarea unei cutii
    static class Box implements Comparable<Box> {
        int weightSupported;
        int weight;

        public Box(int weightSupported, int weight) {
            this.weightSupported = weightSupported;
            this.weight = weight;
        }

        // Implementarea metodei compareTo pentru sortarea cutiilor dupa greutatea suportata
        @Override
        public int compareTo(Box other) {
            return Integer.compare(other.weightSupported, this.weightSupported);
        }
    }
}
