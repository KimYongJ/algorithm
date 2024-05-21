import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, S[], B[];
    static int result;
    static boolean visit[];

    public static void Back(int depth, int mul, int sum, int start) {
        if (depth > 0) {
            result = Math.min(result, Math.abs(mul - sum));
        }
        if (depth == N) {
            return;
        }
        for (int i = start; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                Back(depth + 1, mul * S[i], sum + B[i], i + 1);
                visit[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        S = new int[N];     // 곱
        B = new int[N];     // 합
        visit = new boolean[N];
        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        Back(0, 1, 0, 0);
        System.out.print(result);
    }
}