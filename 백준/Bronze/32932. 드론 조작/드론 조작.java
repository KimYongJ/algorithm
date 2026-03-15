import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int K = fs.nextInt();

        Set<Long> obstacleSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int x = fs.nextInt();
            int y = fs.nextInt();
            obstacleSet.add(encode(x, y));
        }

        String commands = fs.next();

        int x = 0;
        int y = 0;

        for (int i = 0; i < K; i++) {
            char c = commands.charAt(i);

            int nx = x;
            int ny = y;

            if (c == 'U') ny++;
            else if (c == 'D') ny--;
            else if (c == 'L') nx--;
            else if (c == 'R') nx++;

            if (!obstacleSet.contains(encode(nx, ny))) {
                x = nx;
                y = ny;
            }
        }

        System.out.println(x + " " + y);
    }

    static long encode(int x, int y) {
        return (((long) x) << 32) ^ (y & 0xffffffffL);
    }
}