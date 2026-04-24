import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            int[] cost = new int[26];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int time = Integer.parseInt(st.nextToken());
                cost[c - 'A'] = time;
            }

            char[][] grid = new char[H][];
            int ex = -1, ey = -1;
            for (int i = 0; i < H; i++) {
                grid[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 'E') {
                        ex = i;
                        ey = j;
                    }
                }
            }

            // E가 이미 가장자리에 있으면 0
            if (ex == 0 || ex == H - 1 || ey == 0 || ey == W - 1) {
                sb.append(0).append('\n');
                continue;
            }

            long[][] dist = new long[H][W];
            for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);
            dist[ex][ey] = 0;

            // {거리, x, y}
            PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
            pq.offer(new long[]{0, ex, ey});

            long answer = -1;

            while (!pq.isEmpty()) {
                long[] cur = pq.poll();
                long d = cur[0];
                int x = (int) cur[1];
                int y = (int) cur[2];

                if (d > dist[x][y]) continue;

                // 가장자리에 도달한 순간이 최솟값
                if (x == 0 || x == H - 1 || y == 0 || y == W - 1) {
                    answer = d;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                    long nd = d + cost[grid[nx][ny] - 'A'];
                    if (nd < dist[nx][ny]) {
                        dist[nx][ny] = nd;
                        pq.offer(new long[]{nd, nx, ny});
                    }
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }
}