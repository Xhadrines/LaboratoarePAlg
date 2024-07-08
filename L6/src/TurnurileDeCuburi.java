import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TurnurileDeCuburi {
    public static void main(String[] args) {
        try {
            // Citirea datelor de intrare din fisier
            Scanner scanner = new Scanner(new File("InputL6/input6.txt"));
            int n = scanner.nextInt(); // Numarul de cuburi in turnul 1
            int m = scanner.nextInt(); // Numarul de cuburi in turnul 2

            // Initializarea turnurilor
            int[] turn1 = new int[n];
            int[] turn2 = new int[m];

            // Citirea inaltimilor cuburilor pentru fiecare turn
            for (int i = 0; i < n; i++) {
                turn1[i] = scanner.nextInt();
            }

            for (int i = 0; i < m; i++) {
                turn2[i] = scanner.nextInt();
            }

            scanner.close();

            // Initializarea unui vector pentru a memora numarul de cuburi eliminate din fiecare turn
            int[] dp = new int[m + 1];

            // Initializarea listelor pentru a memora cuburile eliminate din fiecare turn
            ArrayList<Integer> elimTurn1 = new ArrayList<>();
            ArrayList<Integer> elimTurn2 = new ArrayList<>();

            // Parcurgerea fiecarui cub din turnul 1 si actualizarea matricei dp
            for (int i = 0; i < n; i++) {
                // Initializarea unor vectori si liste temporare pentru aceasta iteratie
                int[] currentDp = new int[m + 1];
                ArrayList<Integer> currentElimTurn1 = new ArrayList<>();
                ArrayList<Integer> currentElimTurn2 = new ArrayList<>();

                // Parcurgerea cuburilor din turnul 2 si actualizarea matricei dp si a listelor temporare
                for (int j = 0; j < m; j++) {
                    currentDp[j + 1] = dp[j]; // Copierea valorilor din dp

                    // Verificarea daca cuburile din cele doua turnuri sunt identice
                    if (turn1[i] == turn2[j]) {
                        currentDp[j + 1]++;
                        currentElimTurn1.add(i + 1); // Adaugarea cubului eliminat din turnul 1
                        currentElimTurn2.add(j + 1); // Adaugarea cubului eliminat din turnul 2
                    } else if (currentDp[j] > currentDp[j + 1]) {
                        currentElimTurn1.add(i + 1); // Adaugarea cubului eliminat din turnul 1
                    } else {
                        currentElimTurn2.add(j + 1); // Adaugarea cubului eliminat din turnul 2
                    }
                }

                // Actualizarea matricei dp si a listelor finale cu cele temporare
                dp = currentDp;
                elimTurn1 = currentElimTurn1;
                elimTurn2 = currentElimTurn2;
            }

            // Scrierea rezultatelor in fisierul de iesire
            FileWriter writer = new FileWriter("output.txt");
            writer.write(elimTurn1.size() + "\n"); // Scrierea numarului de cuburi eliminate din turnul 1
            for (int i : elimTurn1) {
                writer.write(i + " "); // Scrierea cuburilor eliminate din turnul 1
            }
            writer.write("\n");
            for (int i : elimTurn2) {
                writer.write(i + " "); // Scrierea cuburilor eliminate din turnul 2
            }
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!"); // Afisarea unui mesaj de eroare in caz de fisier lipsa
            e.printStackTrace(); // Afisarea urmaririi stivei
        } catch (IOException e) {
            System.out.println("Error writing to file!"); // Afisarea unui mesaj de eroare in caz de eroare la scriere
            e.printStackTrace(); // Afisarea urmaririi stivei
        }
    }
}
