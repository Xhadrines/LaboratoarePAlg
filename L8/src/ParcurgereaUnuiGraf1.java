import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ParcurgereaUnuiGraf1 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("InputL8/input1.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            String[] linie = reader.readLine().split(" ");
            int n = Integer.parseInt(linie[0]);
            int m = Integer.parseInt(linie[1]);

            int[][] a = new int[n][n];

            for (int i = 0; i < m; i++) {
                linie = reader.readLine().split(" ");
                int x = Integer.parseInt(linie[0]);
                int y = Integer.parseInt(linie[1]);
                a[x][y] = 1;
                a[y][x] = 1;
            }

            int v = Integer.parseInt(reader.readLine());

            writer.write("BF: ");
            BF(a, v, writer);

            writer.write("DF: ");
            DF(a, v, writer);

            reader.close();
            writer.close();

        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void BF(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];
        
        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        Queue<Integer> coada = new LinkedList<>();
        coada.offer(v);
        vizitat[v] = 1;

        while (!coada.isEmpty()) {
            int p = coada.poll();
            writer.write(p + " ");

            for (int j = 0; j < n; j++) {
                if (a[p][j] == 1 && vizitat[j] == 0) {
                    coada.offer(j);
                    vizitat[j] = 1;
                }
            }
        }
    }

    public static void BF1(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        Queue<Integer> coada = new LinkedList<>();
        coada.offer(v);
        vizitat[v] = 1;

        while (!coada.isEmpty()) {
            int p = coada.poll();
            writer.write(p + " ");

            for (int j = 0; j < n; j++) {
                if (a[p][j] == 1 && vizitat[j] == 1) {
                    coada.offer(j);
                    vizitat[j] = 1;
                }
            }
        }
    }

    public static void BF2(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        Queue<Integer> coada = new LinkedList<>();
        coada.offer(v);
        vizitat[v] = 1;

        while (!coada.isEmpty()) {
            int p = coada.poll();
            writer.write(p + " ");

            for (int j = 0; j < n; j++) {
                if (a[p][j] == 1 && vizitat[j] == 0) {
                    coada.offer(j);
                    vizitat[j] = 1;
                }
            }
        }
    }

    public static void BF3(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        Queue<Integer> coada = new LinkedList<>();
        coada.offer(v);
        vizitat[v] = 1;

        while (!coada.isEmpty()) {
            int p = coada.poll();
            writer.write(p + " ");

            for (int j = 0; j < n; j++) {
                if (a[p][j] == 1 && vizitat[j] == 0) {
                    coada.offer(j);
                    vizitat[j] = 1;
                }
            }
        }
    }

    public static void BF4(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];
        
        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        Queue<Integer> coada = new LinkedList<>();
        coada.offer(v);
        vizitat[v] = 1;

        while (!coada.isEmpty()) {
            int p = coada.poll();
            writer.write(p + " ");

            for (int j = 0; j < n; j++) {
                if (a[p][j] == 1 && vizitat[j] == 0) {
                    coada.offer(j);
                    vizitat[j] = 1;
                }
            }
        }
    }

    public static void BF5(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        Queue<Integer> coaada = new LinkedList<>();
        coaada.offer(v);
        vizitat[v] = 1;

        while (!coaada.isEmpty()) {
            int p = coaada.poll();
            writer.write(p + " ");

            for (int j = 0; j < n; j++) {
                if (a[p][j] == 1 && vizitat[j] == 0) {
                    coaada.offer(j);
                    vizitat[j] = 1;
                }
            }
        }
    }

    public static void DF(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        DFRecursiv(a, v, vizitat, writer);
    }

    public static void DFRecursiv(int[][] a, int v, int[] vizitat, BufferedWriter writer) throws IOException {
        int n = a.length;
        vizitat[v] = 1;
        writer.write(v + " ");
        
        for (int j = 0; j < n; j++) {
            if(a[v][j] == 1 && vizitat[v] == 0) {
                DFRecursiv(a, j, vizitat, writer);
            }
        }
    }

    public static void DF1(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        DFRecursiv1(a, v, vizitat, writer);
    }

    public static void DFRecursiv1(int[][] a, int v, int[] vizitat, BufferedWriter writer) throws IOException {
        int n = a.length;
        vizitat[v] = 1;
        writer.write(v + " ");

        for (int i = 0; i < n; i++) {
            if (a[v][i] == 1 && vizitat[i] == 0) {
                DFRecursiv1(a, i, vizitat, writer);
            }
        }
    }

    public static void DF2(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        DFRecursiv2(a, v, vizitat, writer);
    }

    public static void DFRecursiv2(int[][] a, int v, int[] vizitat, BufferedWriter writer) throws IOException {
        int n = a.length;
        vizitat[v] = 1;
        writer.write(v + " ");

        for (int j = 0; j < n; j++) {
            if (a[v][j] == 1 && vizitat[j] == 0) {
                DFRecursiv2(a, j, vizitat, writer);
            }
        }
    }

    public static void DF3(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        DFRecursiv3(a, v, vizitat, writer);
    }

    public static void DFRecursiv3(int[][] a, int v, int[] vizitat, BufferedWriter writer) throws IOException {
        int n = a.length;
        vizitat[v] = 1;
        writer.write(v + " ");

        for (int j = 0; j < n; j++) {
            if (a[v][j] == 1 && vizitat[j] == 0) {
                DFRecursiv3(a, j, vizitat, writer);
            }
        }
    }

    public static void DF4(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        DFRecursiv4(a, v, vizitat, writer);
    }

    public static void DFRecursiv4(int[][] a, int v, int[] vizitat, BufferedWriter writer) throws IOException {
        int n = a.length;
        vizitat[v] = 1;
        writer.write(v + " ");

        for (int j = 0; j < n; j++) {
            if (a[v][j] == 1 && vizitat[j] == 0) {
                DFRecursiv4(a, j, vizitat, writer);
            }
        }
    }

    public static void DF5(int[][] a, int v, BufferedWriter writer) throws IOException {
        int n = a.length;
        int[] vizitat = new int[n];

        for (int i = 0; i < n; i++) {
            vizitat[i] = 0;
        }

        DFRecursiv5(a, v, vizitat, writer);
    }

    public static void DFRecursiv5(int[][] a, int v, int[] vizitat, BufferedWriter writer) throws IOException {
        int n = a.length;
        vizitat[v] = 1;
        
        writer.write(v + " ");

        for (int j = 0; j < n; j++) {
            if (a[v][j] == 1 && vizitat[j] == 0) {
                DFRecursiv(a, j, vizitat, null);
            }
        }
    }
}
