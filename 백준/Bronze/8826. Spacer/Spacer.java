import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        int Z = fs.nextInt();

        while (Z-- > 0) {
            int n = fs.nextInt();
            String s = fs.next();

            int vertical = 0;   // N:+1, S:-1
            int horizontal = 0; // E:+1, W:-1

            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if (ch == 'N') vertical++;
                else if (ch == 'S') vertical--;
                else if (ch == 'E') horizontal++;
                else if (ch == 'W') horizontal--;
            }

            out.append(Math.abs(vertical) + Math.abs(horizontal)).append('\n');
        }

        System.out.print(out);
    }
}