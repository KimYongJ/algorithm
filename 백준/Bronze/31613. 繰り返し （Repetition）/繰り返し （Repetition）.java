//https://www.acmicpc.net/problem/31613
// 2초 1024MB
import java.io.IOException;
import java.io.InputStream;

public class Main {

    // 빠른 입력
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long x = fs.nextLong();
        long n = fs.nextLong();

        long cnt = 0;

        while (x < n) {
            long r = x % 3;

            if (r == 0) {
                x = x + 1;
            } else if (r == 1) {
                // x*2 >= n 이면 더 계산할 필요 없이 n으로 캡
                if (x >= (n + 1) / 2) x = n;
                else x = x * 2;
            } else { // r == 2
                // x*3 >= n 이면 n으로 캡
                if (x >= (n + 2) / 3) x = n;
                else x = x * 3;
            }

            cnt++;
        }

        System.out.println(cnt);
    }
}
