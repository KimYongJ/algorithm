import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken()); // 늘려야 할 고객 수
        int N = Integer.parseInt(st.nextToken()); // 홍보 도시 개수

        int[] M = new int[N + 1]; // 홍보 비용
        int[] P = new int[N + 1]; // 얻을 고객 수

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            M[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int L = C + 100; // 충분한 범위로 설정
        int INF = 1000000000; // 무한대 값

        int[][] dp = new int[N + 1][L + 1]; // DP 배열
        for (int i = 0; i <= N; i++) {
            for (int c = 0; c <= L; c++) {
                dp[i][c] = INF; // 초기화
            }
        }
        dp[0][0] = 0; // 초기 상태

        for (int i = 1; i <= N; i++) {
            for (int c = 0; c <= L; c++) {
                // 이전 값 그대로 유지
                dp[i][c] = dp[i - 1][c];

                // 현재 도시를 사용했을 때 최소 비용 갱신
                if (c >= P[i]) {
                    dp[i][c] = Math.min(dp[i][c], dp[i][c - P[i]] + M[i]);
                }
            }
        }

        // C명 이상에서 최소 비용 찾기
        int minCost = INF;
        for (int c = C; c <= L; c++) {
            minCost = Math.min(minCost, dp[N][c]);
        }

        System.out.println(minCost);
    }
}
