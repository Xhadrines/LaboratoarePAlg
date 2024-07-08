import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VrajitorulSiSchimbarileDePopularitate {
    public static void main(String[] args) {
        try {
            // Se deschid fisierele de intrare si de iesire.
            BufferedReader reader = new BufferedReader(new FileReader("InputL8/input2.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Se citeste numarul de teste.
            int numTests = Integer.parseInt(reader.readLine());

            // Pentru fiecare test:
            for (int test = 0; test < numTests; test++) {
                // Se citeste numarul de locuitori si numarul de relatii de preferinta.
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int numLocuitori = Integer.parseInt(tokenizer.nextToken());
                int numRelatii = Integer.parseInt(tokenizer.nextToken());

                // Se initializeaza o lista de preferinte pentru fiecare locuitor.
                List<List<Integer>> preferinte = new ArrayList<>();
                for (int i = 0; i < numLocuitori; i++) {
                    preferinte.add(new ArrayList<>());
                }

                // Se citesc relatiile de preferinta.
                for (int i = 0; i < numRelatii; i++) {
                    tokenizer = new StringTokenizer(reader.readLine());
                    int x = Integer.parseInt(tokenizer.nextToken());
                    int y = Integer.parseInt(tokenizer.nextToken());
                    preferinte.get(x).add(y);
                }

                // Se considera ca vrajitorul este intotdeauna persoana cu indicele 0 si se salveaza popularitatea sa initiala.
                int numVrajitor = 0;
                int initialPopularity = preferinte.get(numVrajitor).size();

                // Se citeste numarul de vrajitori si se aplica fiecare vraja asupra preferintelor.
                int numVrajituri = Integer.parseInt(reader.readLine());
                for (int i = 0; i < numVrajituri; i++) {
                    tokenizer = new StringTokenizer(reader.readLine());
                    String vraja = tokenizer.nextToken();
                    int persoana = Integer.parseInt(tokenizer.nextToken());

                    // Se inverseaza preferintele persoanei sau se elimina complet preferintele ei sau se face popularitatea ei catre toti ceilalti.
                    if (vraja.equals("Inverseaza")) {
                        for (int j = 0; j < numLocuitori; j++) {
                            preferinte.get(j).remove(Integer.valueOf(persoana));
                        }
                    } else if (vraja.equals("Nepopular")) {
                        preferinte.get(persoana).clear();
                    } else if (vraja.equals("Popular")) {
                        for (int j = 0; j < numLocuitori; j++) {
                            if (j != persoana) {
                                preferinte.get(j).add(persoana);
                            }
                        }
                    }
                }

                // Se calculeaza diferenta dintre popularitatea initiala si cea finala a vrajitorului si se scrie in fisierul de iesire.
                int finalPopularity = preferinte.get(numVrajitor).size();
                int difference = finalPopularity - initialPopularity;
                writer.write(difference + "\n");
            }

            // Se inchid fisierele de intrare si de iesire.
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
