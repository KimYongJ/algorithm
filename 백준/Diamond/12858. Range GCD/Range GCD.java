
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /* ---------------------- 전역 ---------------------- */
    static int N, Q;                     // 원소 개수, 질의 수
    static long[] arr;                     // 초기 수열 (1-based, a[N+1]=dummy)
    static long[] diffSeg;               // diff[] GCD 세그먼트 트리

    /* ------------------ 유틸 : GCD -------------------- */
    /** 유클리드 최대공약수 (항상 양수 반환) */
    static long gcd(long x, long y) {
        if (y == 0) return Math.abs(x);
        return gcd(y, x % y);
    }

    /* ========= ① diff용 세그먼트 트리 (구간 GCD) ========= */

    /** 트리 빌드 : diff[i] = a[i+1] - a[i] */
    static void buildDiff(int node, int l, int r) {
        if (l == r) {
            diffSeg[node] = arr[l + 1] - arr[l];
            return;
        }
        int m = (l + r) >>> 1;
        buildDiff(node << 1, l, m);
        buildDiff(node << 1 | 1, m + 1, r);
        diffSeg[node] = gcd(diffSeg[node << 1], diffSeg[node << 1 | 1]);
    }

    /** 단일 diff 인덱스에 delta 추가 */
    static void updateDiff(int node, int l, int r, int idx, long delta) {
        if (idx < l || idx > r) return;
        if (l == r) {
            diffSeg[node] += delta;
            return;
        }
        int m = (l + r) >>> 1;
        updateDiff(node << 1, l, m, idx, delta);
        updateDiff(node << 1 | 1, m + 1, r, idx, delta);
        diffSeg[node] = gcd(diffSeg[node << 1], diffSeg[node << 1 | 1]);
    }

    /** diff[l..r] 의 GCD (구간이 비면 0) */
    static long queryDiff(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        if (ql <= l && r <= qr) return diffSeg[node];
        int m = (l + r) >>> 1;
        return gcd(
            queryDiff(node << 1, l, m, ql, qr),
            queryDiff(node << 1 | 1, m + 1, r, ql, qr)
        );
    }

    /* ========= ② 값용 세그먼트 트리 (Range-Add / Point-Query) ========= */

    /** 1-based 인덱스 전용 range-add 세그먼트 트리 */
    static final class RangeAddPointSegTree {
        private final long[] tree;     // 리프 : 실제 a[i] 값, 내부 : 왼쪽 리프 값
        private final long[] lazy;     // 구간에 아직 전파되지 않은 덧셈 값
        private final int n;

        RangeAddPointSegTree(int n) {
            this.n = n;
            tree = new long[4 * n + 4];
            lazy = new long[4 * n + 4];
        }

        /** lazy 값 아래로 전달 & 현 노드 반영 */
        private void propagate(int node, int l, int r) {
            if (lazy[node] == 0) return;
            tree[node] += lazy[node];
            if (l != r) {                    // 자식 노드에 누적
                lazy[node << 1]     += lazy[node];
                lazy[node << 1 | 1] += lazy[node];
            }
            lazy[node] = 0;
        }

        /** [ql,qr] 에 delta 더하기 */
        void rangeAdd(int ql, int qr, long delta) { rangeAdd(1, 1, n, ql, qr, delta); }
        private void rangeAdd(int node, int l, int r, int ql, int qr, long delta) {
            propagate(node, l, r);
            if (qr < l || r < ql) return;
            if (ql <= l && r <= qr) {
                lazy[node] += delta;
                propagate(node, l, r);
                return;
            }
            int m = (l + r) >>> 1;
            rangeAdd(node << 1, l, m, ql, qr, delta);
            rangeAdd(node << 1 | 1, m + 1, r, ql, qr, delta);
        }

        /** a[idx] 단일 조회 */
        long pointQuery(int idx) { return pointQuery(1, 1, n, idx); }
        private long pointQuery(int node, int l, int r, int idx) {
            propagate(node, l, r);
            if (l == r) return tree[node];
            int m = (l + r) >>> 1;
            return (idx <= m)
                 ? pointQuery(node << 1, l, m, idx)
                 : pointQuery(node << 1 | 1, m + 1, r, idx);
        }
    }

    /* ---------------------- 메인 ---------------------- */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        arr = new long[N + 2];                // a[N+1] 더미 0
        diffSeg = new long[4 * N + 4];

        RangeAddPointSegTree valSeg = new RangeAddPointSegTree(N);  // 값용 트리

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
        {
        	arr[i] = Long.parseLong(st.nextToken());
            valSeg.rangeAdd(i, i, arr[i]);     // 초기값 세팅 : a[i] += a[i]
        }

        /* diff 세그먼트 트리 빌드 */
        buildDiff(1, 1, N);

        /* 쿼리 처리 */
        Q = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            long t = Long.parseLong(st.nextToken());   // 0이면 질의, 아니면 덧셈
            int x  = Integer.parseInt(st.nextToken());
            int y  = Integer.parseInt(st.nextToken());
            if (x > y) { int tmp = x; x = y; y = tmp; } // 보장 안 됐을 때 대비

            if (t == 0)
            {               /* ───── 질의 : 최대공약수 ───── */
                long first = valSeg.pointQuery(x);          // a[x]
                long gDiff = queryDiff(1, 1, N, x, y - 1);  // diff[x..y-1] GCD
                out.append(gcd(first, Math.abs(gDiff))).append('\n');
            }
            else
            {                    /* ───── 갱신 : 구간 덧셈 ───── */
                /* diff 배열 갱신  diff[x-1] += t */
                updateDiff(1, 1, N, x - 1,  t);
                /* diff[y] -= t  (y ≤ N) */
                updateDiff(1, 1, N, y,     -t);
                /* 값 세그트리 : [x,y] 범위에 t 더하기 */
                valSeg.rangeAdd(x, y, t);
            }
        }
        System.out.print(out);
    }
}
