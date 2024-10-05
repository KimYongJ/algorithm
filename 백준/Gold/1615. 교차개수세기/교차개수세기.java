import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int[] dx = {1, 0, -1, 0, 1, -1, -1, 1};
    static final int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};
    static final int N = 1 << 11;
    static List<Integer>[] arr = new ArrayList[2001];
    static long[] tree = new long[N << 1];
    static int n, m;
    
    static int read() throws Exception {// 빠른 입력을 위한 함수
    	int c, n = System.in.read() & 15;
    	while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
    	return n;
    }
    
    public static void main(String[] args) throws Exception {
        n = read();
        m = read();

        for (int i = 0; i < 2001; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = read();
            int b = read();
            arr[a].add(b);
        }

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int v : arr[i]) {
                ans += query(v + 1, n);
            }
            for (int v : arr[i]) {
                update(v, v);
            }
        }
        System.out.println(ans);
    }

    static long query(int l, int r) {
        return query(l, r, 1, 0, N - 1);
    }

    static long query(int l, int r, int i, int s, int e) {
        if (l > e || r < s) return 0;
        if (l <= s && e <= r) return tree[i];
        int m = (s + e) >> 1;
        return query(l, r, i << 1, s, m) + query(l, r, i << 1 | 1, m + 1, e);
    }

    static void update(int l, int r) {
        update(l, r, 1, 0, N - 1);
    }

    static void update(int l, int r, int i, int s, int e) {
        if (l > e || r < s) return;
        if (l <= s && e <= r) {
            tree[i]++;
            return;
        }
        int m = (s + e) >> 1;
        update(l, r, i << 1, s, m);
        update(l, r, i << 1 | 1, m + 1, e);
        tree[i] = tree[i << 1] + tree[i << 1 | 1];
    }
}