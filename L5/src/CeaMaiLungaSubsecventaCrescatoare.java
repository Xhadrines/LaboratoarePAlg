import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CeaMaiLungaSubsecventaCrescatoare {
    public static void main(String[] args) {
        try {
            // Citirea din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL5/input4.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            int n = scanner.nextInt();
            int[] arr = new int[n];

            // Citirea vectorului din fisierul de intrare
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // Apelul functiei pentru gasirea celei mai lungi subsecvente crescatoare
            ArrayList<Integer> lis = LIS(arr);

            // Scrierea rezultatului in fisierul de iesire
            writer.println(lis.size()); // Lungimea celei mai lungi subsecvente crescatoare
            for (int num : lis) {
                writer.print(num + " "); // Scrierea subsecventei in sine
            }
            writer.println(); // Adaugarea unei linii goale pentru claritate

            // Inchiderea scanner-ului si a writer-ului pentru fisiere
            scanner.close();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Afisam stack trace-ul in cazul in care fisierul de intrare nu este gasit
        }
    }

    // Implementarea algoritmului LIS (Longest Increasing Subsequence)
    public static ArrayList<Integer> LIS(int[] arr) {
        ArrayList<Integer> piles = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        // Parcurgerea vectorului de intrare si formarea pile-urilor
        for (int num : arr) {
            int pile = findPile(piles, num); // Gasirea pile-ului corespunzator pentru numarul curent
            if (pile == piles.size()) {
                piles.add(num); // Daca pile-ul nu exista, adaugam unul nou cu numarul curent
            } else {
                piles.set(pile, num); // Daca pile-ul exista, actualizam valoarea acestuia
            }
        }

        // Construirea subsecventei crescatoare pe baza pile-urilor
        for (int pile : piles) {
            result.add(pile); // Adaugam fiecare pile in subsecventa
        }

        return result; // Returnam subsecventa construita
    }

    // Functia pentru gasirea pile-ului corespunzator pentru un numar dat
    private static int findPile(ArrayList<Integer> piles, int num) {
        int left = 0, right = piles.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // Calculam mijlocul intervalului
            if (piles.get(mid) >= num) { // Daca valoarea pile-ului de la mijloc este mai mare sau egala cu numarul dat
                right = mid - 1; // Cautam in partea stanga a vectorului
            } else {
                left = mid + 1; // Altfel, cautam in partea dreapta a vectorului
            }
        }
        return left; // Returnam indicele pile-ului corespunzator
    }
}
