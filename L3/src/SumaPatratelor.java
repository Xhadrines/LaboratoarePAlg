import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SumaPatratelor {
    public static void main(String[] args) {
        try {
            // Citim datele din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL3/input7.txt"));
            int n = scanner.nextInt(); // Citim numarul de elemente
            int[] array = new int[n]; // Initializam un vector pentru a stoca numerele

            // Citim elementele si le stocam in vector
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }

            // Calculam suma patratelor utilizand metoda recursiva
            int sum = sumaPatratelor(array, 0, n - 1);

            // Deschidem fisierul de iesire pentru scriere
            PrintWriter writer = new PrintWriter("output.txt");
            writer.println(sum); // Scriem suma patratelor in fisierul de iesire

            // Inchidem scanner-ul si writer-ul
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // Afisam un mesaj de eroare in cazul in care fisierul de intrare nu este gasit
            System.out.println("Fisierul de intrare nu a fost gasit.");
            e.printStackTrace();
        }
    }

    // Metoda recursiva pentru a calcula suma patratelor
    private static int sumaPatratelor(int[] array, int left, int right) {
        // Cazul de baza: daca avem un singur element, returnam patratul lui
        if (left == right) {
            return array[left] * array[left];
        }

        // Calculam mijlocul intervalului
        int mid = left + (right - left) / 2;

        // Calculam suma patratelor din subintervalul stanga si dreapta
        int leftSum = sumaPatratelor(array, left, mid);
        int rightSum = sumaPatratelor(array, mid + 1, right);

        // Returnam suma totala a patratelor
        return leftSum + rightSum;
    }
}
