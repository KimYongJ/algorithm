import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 29; // 배열 크기 증가
    static int N;
    static double[] prob = new double[4]; // 확률을 저장할 배열
    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동, 서, 남, 북
    static boolean[][] visited = new boolean[MAX][MAX];
    static double result = 0.0;

    static void dfs(int x, int y, int depth, double p) {
        if (depth == N) {
            result += p; // 현재까지의 확률을 결과에 더함
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, p * prob[i]); // 이동 확률을 곱함
                visited[nx][ny] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            prob[i] = Integer.parseInt(st.nextToken()) / 100.0; // 확률 입력 및 변환
        }

        int mid = MAX / 2; // 시작 위치
        visited[mid][mid] = true;
        dfs(mid, mid, 0, 1.0); // 시작 확률은 1.0

        System.out.println(result);
    }
}