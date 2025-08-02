import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static final int[][] STARTS = {{0,0},{0,2},{1,1},{2,0},{2,2}};
    static final int[][] DIR   = {{0,1},{1,0},{-1,0},{0,-1}};

    static int N, M, MAX;          // 목표값, 숫자 개수, 경로 길이(=2*M-1)
    static char[][] board = new char[3][3];
    static boolean[][] visited = new boolean[3][3];
    static int[][] path;           // 경로 기록 [MAX][2]

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAX = 2 * M - 1;
        path = new int[MAX][2];

        for (int y = 0; y < 3; y++) {
            String line = br.readLine();
            for (int x = 0; x < 3; x++) board[y][x] = line.charAt(x);
        }

        for (int[] s : STARTS) {
            int sy = s[0], sx = s[1];
            if (!isNum(sy, sx)) continue;          // 시작은 반드시 숫자
            visited[sy][sx] = true;
            path[0][0] = sy; path[0][1] = sx;
            int first = board[sy][sx] - '0';
            if (dfs(sy, sx, first, 1, '+', true, 1)) return; // 숫자 하나 썼으므로 cnt=1
            visited[sy][sx] = false;
        }
        System.out.print(0);
    }

    /** y,x : 현재 위치
     *  sum : 지금까지 계산값
     *  cnt : 사용한 숫자 개수
     *  op  : 다음 숫자에 적용할 연산자
     *  needOp : 다음 이동이 연산자 칸이어야 하는가?
     *  depth  : 지금까지 이동한 칸 수(시작 칸 포함) */
    static boolean dfs(int y, int x, int sum, int cnt,
                       char op, boolean needOp, int depth) {

        if (cnt == M) {                               // 숫자 M개 사용 완료
            if (sum == N && depth == MAX) {
                printPath();
                return true;
            }
            return false;
        }

        for (int[] d : DIR) {
            int ny = y + d[0], nx = x + d[1];
            if (!in(ny, nx) || visited[ny][nx]) continue;

            if (needOp) {                             // 연산자 칸을 찾아야 함
                if (!isOp(ny, nx)) continue;
                visited[ny][nx] = true;
                path[depth][0] = ny; path[depth][1] = nx;
                if (dfs(ny, nx, sum, cnt, board[ny][nx], false, depth + 1))
                    return true;
                visited[ny][nx] = false;
            } else {                                  // 숫자 칸을 찾아야 함
                if (!isNum(ny, nx)) continue;
                int val = board[ny][nx] - '0';
                int nextSum = (op == '+') ? sum + val : sum - val;
                visited[ny][nx] = true;
                path[depth][0] = ny; path[depth][1] = nx;
                if (dfs(ny, nx, nextSum, cnt + 1, op, true, depth + 1))
                    return true;
                visited[ny][nx] = false;
            }
        }
        return false;
    }

    /* ---------- 유틸 ---------- */
    static boolean in(int y, int x) { return 0 <= y && y < 3 && 0 <= x && x < 3; }
    static boolean isNum(int y, int x) { return '0' <= board[y][x] && board[y][x] <= '9'; }
    static boolean isOp(int y, int x)  { return board[y][x] == '+' || board[y][x] == '-'; }

    static void printPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(1).append('\n');
        for (int i = 0; i < MAX; i++)
            sb.append(path[i][0]).append(' ').append(path[i][1]).append('\n');
        System.out.print(sb);
    }
}
