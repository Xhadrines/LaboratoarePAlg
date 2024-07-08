import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Excursii2 {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare si deschiderea fisierului de iesire
            Scanner scanner = new Scanner(new File("InputL6/input7.txt"));
            FileWriter writer = new FileWriter("output.txt");

            int T = scanner.nextInt(); // Numarul de teste

            // Iteram prin fiecare test
            for (int i = 0; i < T; i++) {
                scanner.nextLine(); // Consumam linia goala

                // Citirea si separarea oraselor
                String[] cities = scanner.nextLine().split(" ");

                // Crearea unei liste de orase
                ArrayList<String> cityList = new ArrayList<>();
                Collections.addAll(cityList, cities);

                // Verificarea daca lista de orase este in ordine alfabetica
                if (isInAlphabeticOrder(cityList)) {
                    writer.write(cityList.size() + "\n"); // Scrierea numarului de orase in fisierul de iesire
                } else {
                    writer.write("0\n"); // Daca nu este in ordine alfabetica, se scrie 0
                }
            }

            // Inchiderea fluxurilor de date
            scanner.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Functia pentru a verifica daca o lista de orase este in ordine alfabetica
    private static boolean isInAlphabeticOrder(ArrayList<String> cities) {
        for (int i = 1; i < cities.size(); i++) {
            if (cities.get(i - 1).compareTo(cities.get(i)) > 0) {
                return false; // Daca gasim un oras care vine dupa altul in ordine alfabetica, lista nu este ordonata
            }
        }
        return true; // Daca nu am gasit nicio problema, lista este ordonata
    }
}
