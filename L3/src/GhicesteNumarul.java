import java.util.Random;
import java.util.Scanner;

public class GhicesteNumarul {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitam utilizatorului sa introduca valoarea maxima pentru numarul de ghicit (n)
        System.out.println("Introduceti valoarea maxima pentru numarul de ghicit (n):");
        int n = scanner.nextInt();

        // Initializam limitele inferioara si superioara ale intervalului de cautare
        int lowerBound = 1;
        int upperBound = n;

        // Initializam un obiect Random pentru a genera ghiciri aleatorii
        Random random = new Random();
        int guess;
        String response;

        // Intram in bucla pana cand utilizatorul raspunde 'BRAVO'
        do {
            // Generam o ghicire aleatorie intre limitele intervalului de cautare
            guess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

            // Afisam ghicirea si solicitam raspunsul utilizatorului
            System.out.println("Calculatorul ghiceste: " + guess);
            System.out.println("Este numarul ghicit prea mare, prea mic sau corect?");
            System.out.println("Introduceti 'PREA MARE', 'PREA MIC' sau 'BRAVO':");
            response = scanner.next();

            // Ajustam limitele intervalului de cautare in functie de raspunsul utilizatorului
            if (response.equalsIgnoreCase("PREA MARE")) {
                upperBound = guess - 1;
            } else if (response.equalsIgnoreCase("PREA MIC")) {
                lowerBound = guess + 1;
            }
        } while (!response.equalsIgnoreCase("BRAVO"));

        // Inchidem Scanner-ul
        scanner.close();
    }
}
