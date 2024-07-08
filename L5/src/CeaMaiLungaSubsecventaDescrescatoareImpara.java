import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CeaMaiLungaSubsecventaDescrescatoareImpara {
    public static void main(String[] args) {
        try {
            // Deschideti fisierul de intrare pentru citire si fisierul de iesire pentru scriere
            Scanner scanner = new Scanner(new File("InputL5/input5.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            int n = scanner.nextInt();
            int[] arr = new int[n];

            // Citirea vectorului din fisierul de intrare
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            // Gasirea celei mai lungi subsecvente descrescatoare cu numere impare
            ArrayList<Integer> longestDecreasingOddSubsequence = findLongestDecreasingOddSubsequence(arr);

            // Scrierea rezultatului in fisierul de iesire
            writer.println(longestDecreasingOddSubsequence.size()); // Scrierea lungimii subsecventei
            for (int num : longestDecreasingOddSubsequence) {
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

    // Functia pentru gasirea celei mai lungi subsecvente descrescatoare cu numere impare
    private static ArrayList<Integer> findLongestDecreasingOddSubsequence(int[] arr) {
        ArrayList<ArrayList<Integer>> dp = new ArrayList<>(); // Matrice pentru stocarea subsecventelor
        ArrayList<Integer> result = new ArrayList<>(); // Lista pentru stocarea celei mai lungi subsecvente gasite

        // Parcurgem vectorul de intrare si construim subsecventele descrescatoare cu numere impare
        for (int num : arr) {
            if (num % 2 == 1) { // Daca numarul este impar
                ArrayList<Integer> currSeq = new ArrayList<>(); // Initializam o noua subsecventa curenta
                boolean found = false; // Variabila pentru a marca daca am gasit o subsecventa valida
                for (int j = dp.size() - 1; j >= 0; j--) { // Parcurgem in ordine inversa subsecventele existente
                    if (!dp.get(j).isEmpty() && dp.get(j).get(dp.get(j).size() - 1) > num) { // Daca am gasit o subsecventa valida
                        currSeq = new ArrayList<>(dp.get(j)); // Copiem subsecventa gasita
                        currSeq.add(num); // Adaugam numarul curent la subsecventa
                        found = true; // Marcam ca am gasit o subsecventa
                        break; // Intrerupem cautarea
                    }
                }
                if (!found) { // Daca nu am gasit o subsecventa valida
                    currSeq.add(num); // Adaugam doar numarul curent la subsecventa
                }
                dp.add(currSeq); // Adaugam subsecventa curenta la matricea dp
                if (currSeq.size() > result.size()) { // Daca lungimea subsecventei curente este mai mare decat cea mai lunga gasita pana acum
                    result = currSeq; // Actualizam subsecventa rezultat
                }
            } else {
                dp.add(new ArrayList<>()); // Daca numarul este par, adaugam o subsecventa goala la matricea dp
            }
        }

        return result; // Returnam subsecventa rezultat
    }
}
