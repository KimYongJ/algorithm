import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int INF = 1_000_000_000;

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dist = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                // 두 방을 잇는 통로는 하나뿐이지만 안전하게 min 처리
                if (c < dist[a][b]) {
                    dist[a][b] = c;
                    dist[b][a] = c;
                }
            }

            // 플로이드-워셜
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    if (dist[i][k] == INF) continue;
                    for (int j = 1; j <= N; j++) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

            int K = Integer.parseInt(br.readLine().trim());
            int[] friends = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                friends[i] = Integer.parseInt(st.nextToken());
            }

            int bestRoom = -1;
            int bestSum = Integer.MAX_VALUE;
            for (int room = 1; room <= N; room++) {
                int sum = 0;
                for (int f : friends) sum += dist[f][room];
                if (sum < bestSum) {       // 같은 값이면 갱신 안 하므로 자연스럽게 작은 번호 선택
                    bestSum = sum;
                    bestRoom = room;
                }
            }

            sb.append(bestRoom).append('\n');
        }

        System.out.print(sb);
    }
}