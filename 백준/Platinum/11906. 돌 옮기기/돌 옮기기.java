//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11906
import java.util.*;
import java.io.*;

class Node {
    long idx;
    char col;
    Node(long i, char c){ idx = i; col = c; }
}

public class Main {
    static long R;
    static int M, N;
    static Node[] stones;

    // KMP prefix function
    static int[] buildPi(char[] p) {
        int n = p.length;
        int[] pi = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && p[i] != p[j]) j = pi[j - 1];
            if (p[i] == p[j]) pi[i] = ++j;
        }
        return pi;
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        R = in.nextLong();          // 호수 둘레
        M = in.nextInt();           // 검은 돌 개수
        N = M << 1;                 // 전체 돌 수 = 2N

        stones = new Node[N];
        for (int i = 0; i < N; i++) {
            long pos = in.nextLong();
            char c = in.nextChar(); // 'B' 또는 'W'
            stones[i] = new Node(pos, c);
        }

        // 둘레 순서로 정렬(입력이 반드시 정렬되어 있다는 보장이 없을 수 있음)
        Arrays.sort(stones, (a, b) -> Long.compare(a.idx, b.idx));

        // 색 배열 구성
        char[] col = new char[N];
        for (int i = 0; i < N; i++) col[i] = stones[i].col;

        // 패턴: 보색(complement)
        char[] pat = new char[N];
        for (int i = 0; i < N; i++) pat[i] = (col[i] == 'B') ? 'W' : 'B';

        // 텍스트: 색 배열을 두 번 이어붙이기
        char[] text = new char[2 * N];
        for (int i = 0; i < N; i++) {
            text[i] = col[i];
            text[i + N] = col[i];
        }

        int[] pi = buildPi(pat);

        long ans = Long.MAX_VALUE;

        // KMP: text에서 pat을 찾는다. 일치 시작 위치가 곧 시프트 s
        // s는 1..N-1 범위만 유효 (s=0은 자기 자신, 의미 없음)
        for (int i = 0, j = 0; i < 2 * N - 1; i++) { // 시작 s < N 만 확인
            while (j > 0 && text[i] != pat[j]) j = pi[j - 1];
            if (text[i] == pat[j]) {
                if (j == N - 1) {
                    int s = i - (N - 1);   // 매치 시작 인덱스 = 시프트
                    if (0 < s && s < N) {
                        ans = Math.min(ans, (long)s * R);
                    }
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        System.out.print(ans == Long.MAX_VALUE ? -1 : ans);
    }
}

/** 빠른 입력 */
class Reader {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0, len = 0;

    private byte read() throws IOException {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
        }
        return buffer[ptr++];
    }

    char nextChar() throws IOException {
        byte c;
        while ((c = read()) <= 32) if (c == -1) return 0;
        return (char)c;
    }

    int nextInt() throws IOException {
        int sgn = 1, val = 0;
        byte c;
        while ((c = read()) <= 32) if (c == -1) return -1;
        if (c == '-') { sgn = -1; c = read(); }
        while (c > 32) { val = val * 10 + (c - '0'); c = read(); }
        return val * sgn;
    }

    long nextLong() throws IOException {
        int sgn = 1;
        long val = 0;
        byte c;
        while ((c = read()) <= 32) if (c == -1) return -1;
        if (c == '-') { sgn = -1; c = read(); }
        while (c > 32) { val = val * 10 + (c - '0'); c = read(); }
        return sgn == 1 ? val : -val;
    }
}

