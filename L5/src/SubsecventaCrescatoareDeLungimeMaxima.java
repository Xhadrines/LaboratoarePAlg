import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SubsecventaCrescatoareDeLungimeMaxima {
    public static void main(String[] args) {
        try {
            // Citirea datelor din fisierul de intrare
            Scanner scanner = new Scanner(new File("InputL5/input2.txt"));
            PrintWriter writer = new PrintWriter(new File("output.txt"));

            // Citirea lungimii secventei
            int n = scanner.nextInt();
            int[] a = new int[n];
            // Citirea elementelor secventei
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            // Calcularea lungimii celei mai lungi subsecvente crescatoare
            int[] lung = LIS(a, n);
            int max = lung[0];
            int poz = 0;

            // Gasirea pozitiei si lungimii maxime
            for (int i = 1; i < n; i++) {
                if (max < lung[i]) {
                    max = lung[i];
                    poz = i;
                }
            }

            // Scrierea rezultatului in fisierul de iesire
            writer.println("Lungimea maxima este " + max);
            writer.print("Iar subsecventa este: ");
            TiparesteLIS(a, n, lung, poz, max, writer);

            // Inchiderea fluxurilor de date
            scanner.close();
            writer.close();

        } catch (FileNotFoundException e) {
            // Tratarea cazului in care fisierul de intrare nu poate fi gasit
            e.printStackTrace();
        }
    }

    // Functia pentru calcularea lungimii celei mai lungi subsecvente crescatoare
    public static int[] LIS(int[] a, int n) {
        int[] lung = new int[n];
        lung[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                if (a[i] <= a[j]) {
                    if (max < lung[j]) {
                        max = lung[j];
                    }
                }
            }
            lung[i] = max + 1;
        }

        return lung;
    }

    // Functia pentru tiparirea celei mai lungi subsecvente crescatoare
    public static void TiparesteLIS(int[] a, int n, int[] lung, int poz, int max, PrintWriter writer) {
        writer.print(a[poz] + " ");
        for (int i = poz + 1; i < n; i++) {
            if (lung[i] == max - 1 && a[i] >= a[poz]) {
                writer.print(a[i] + " ");
                poz = i;
                max--;
            }
            if (max == 0) {
                break;
            }
        }
    }
}
