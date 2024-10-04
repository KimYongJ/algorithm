import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int n, m, ccnt = 1, pcnt = 1, answer = Integer.MAX_VALUE;
    static int[][] graph = new int[51][51];
    static int[] c = new int[300], f = new int[300];
    static int[][] bvisit = new int[51][51];
    static boolean[] visit = new boolean[300];
    static ArrayList<Pair>[] adj = new ArrayList[110];
    static Queue<Triple> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 110; i++) {
            adj[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                char x = s.charAt(j);
                if (x == 'C') {
                    graph[i][j + 1] = ccnt++;
                } else if (x == '.') {
                    graph[i][j + 1] = 0;
                } else if (x == 'X') {
                    graph[i][j + 1] = -1;
                } else if (x == 'P') {
                    graph[i][j + 1] = 110 + pcnt++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (graph[i][j] > 0 && graph[i][j] < 110) {
                    for (int[] row : bvisit) {
                        Arrays.fill(row, -1);
                    }
                    q.add(new Triple(i, j, 0));
                    bvisit[i][j] = 0;
                    bfs(graph[i][j]);
                }
            }
        }

        int l = 1, r = 1000000;
        while (l <= r) {
            int mid = (l + r) / 2, match = 0;
            Arrays.fill(c, -1);
            Arrays.fill(f, -1);
            for (int i = 1; i < ccnt; i++) {
                if (c[i] == -1) {
                    Arrays.fill(visit, false);
                    if (dfs(i, mid)) match++;
                }
            }
            if (match == ccnt - 1) {
                answer = Math.min(answer, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        if (ccnt == 1) {
            System.out.println(0);
        } else if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static boolean dfs(int s, int up) {
        visit[s] = true;
        for (Pair e : adj[s]) {
            if (e.weight > up) continue;
            if (f[e.node] == -1 || (!visit[f[e.node]] && dfs(f[e.node], up))) {
                c[s] = e.node;
                f[e.node] = s;
                return true;
            }
        }
        return false;
    }

    static void bfs(int s) {
        while (!q.isEmpty()) {
            Triple current = q.poll();
            int x = current.x, y = current.y, cnt = current.count;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 1 || ny < 1 || nx > n || ny > m || bvisit[nx][ny] != -1 || graph[nx][ny] == -1) continue;
                bvisit[nx][ny] = cnt + 1;
                if (graph[nx][ny] > 110) adj[s].add(new Pair(graph[nx][ny], cnt + 1));
                q.add(new Triple(nx, ny, cnt + 1));
            }
        }
    }

    static class Pair {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class Triple {
        int x, y, count;

        Triple(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}