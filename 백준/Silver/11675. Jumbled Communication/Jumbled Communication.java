//https://www.acmicpc.net/problem/11675
//5초 256MB

import java.io.InputStream;
import java.io.IOException;

public class Main {

    // 빠른 입력
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    // y = x ^ (x<<1) 의 역변환 (8비트 기준)
    static int unscramble(int y) {
        int x = 0;
        int prevXBit = 0; // x_{i-1}

        for (int i = 0; i < 8; i++) {
            int yBit = (y >>> i) & 1;
            int xBit = yBit ^ prevXBit; // x_i = y_i XOR x_{i-1}
            x |= (xBit << i);
            prevXBit = xBit;
        }
        return x; // 0..255
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int y = fs.nextInt();           // 스크램블된 바이트 (0..255)
            int x = unscramble(y);          // 원래 바이트 복원
            if (i > 0) sb.append(' ');
            sb.append(x);
        }

        System.out.print(sb.toString());
    }
}
