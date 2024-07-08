import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ElementulLipsa {
    public static void main(String[] args) {
        // Deschide fisierul de intrare pentru citire
        File inputFile = new File("InputL1/input1.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            // Citeste numarul de elemente din fisier
            int n = scanner.nextInt();
            // Initializeaza un array de dimensiunea n - 1 pentru a stoca elementele
            int[] A = new int[n - 1];
            // Citeste elementele si le stocheaza in array
            for (int i = 0; i < n - 1; i++) {
                A[i] = scanner.nextInt();
            }

            // Apeleaza functiile care determina elementul lipsa folosind trei variante diferite si salveaza rezultatul intr-o variabila
            int missingValue = ValoareLipsa_v1(A, n);
            int missingValue2 = ValoareLipsa_v2(A, n);
            int missingValue3 = ValoareLipsa_v3(A, n);

            // Deschide fisierul de iesire pentru scriere
            File outputFile = new File("output.txt");
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                // Scrie rezultatul in fisierul de iesire
                writer.println("Valoare lipsa folosind prima metoda: " + missingValue);
                writer.println("Valoare lipsa folosind a doua metoda: " + missingValue2);
                writer.println("Valoare lipsa folosind a treia metoda: " + missingValue3);
            }
        } catch (FileNotFoundException e) {
            // Prinde si afiseaza exceptia in cazul in care apare o eroare la citirea/scrierea fisierului
            System.out.println("Eroare la citirea/scrierea fisierului: " + e.getMessage());
        }
    }

    // Functie care determina valoarea lipsa dintr-un array folosind prima metoda
    public static int ValoareLipsa_v1(int[] A, int n) {
        // Parcurge fiecare valoare posibila
        for (int v = 1; v <= n; v++) {
            boolean amGasit = false;
            // Verifica daca valoarea este prezenta in array
            for (int i = 0; i < n - 1; i++) {
                if (A[i] == v) {
                    amGasit = true;
                    break;
                }
            }
            // Daca valoarea nu este gasita, o returneaza
            if (!amGasit) {
                return v;
            }
        }
        return -1;
    }

    // Functie care determina valoarea lipsa dintr-un array folosind a doua metoda
    public static int ValoareLipsa_v2(int[] A, int n) {
        // Initializeaza un array care va tine evidenta prezentei valorilor
        int[] prezent = new int[n];

        // Initializeaza toate elementele din arrayul prezent cu 0
        for (int i = 0; i < n; i++) {
            prezent[i] = 0;
        }

        // Marcheaza valorile prezente in arrayul prezent
        for (int i = 0; i < n - 1; i++) {
            prezent[A[i] - 1] = 1;
        }

        // Gaseste prima valoare lipsa si o returneaza
        for (int i = 0; i < n; i++) {
            if (prezent[i] == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    // Functie care determina valoarea lipsa dintr-un array folosind a treia metoda
    public static int ValoareLipsa_v3(int[] A, int n) {
        // Calculeaza suma tuturor valorilor prezente in array
        int suma = 0;
        for (int i = 0; i < n - 1; i++) {
            suma += A[i];
        }
        // Returneaza valoarea lipsa folosind formula pentru suma unei progresii aritmetice
        return n * (n + 1) / 2 - suma;
    }
}
