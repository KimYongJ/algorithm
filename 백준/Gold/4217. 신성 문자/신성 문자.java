import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {
    static final int MAX_N = 400;
    static final int MAX_M = 500;
    static final int[][] hexa = new int[16][4];
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static int[][] arr = new int[MAX_N][MAX_M];
    static int[][] check = new int[MAX_N][MAX_M];
    static int n, m;
    static char[] keys = {'W', 'A', 'K', 'J', 'S', 'D'};

    public static void go(int r, int c, int col) {
        check[r][c] = col;
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k], nc = c + dc[k];
            if (0 <= nr && nr <= n + 1 && 0 <= nc && nc <= m * 4 + 2 && check[nr][nc] == 0 && arr[r][c] == arr[nr][nc])
                go(nr, nc, col);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = 1;
        // Initialize hexa array
        for (int i = 0; i < 16; i++) {
            int num = i, f = 3;
            while (num != 0) {
                hexa[i][f--] = num % 2;
                num /= 2;
            }
        }
        while (true) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            if (n == 0 && m == 0) break;
            for (int[] row : arr)
                Arrays.fill(row, 0);
            for (int[] row : check)
                Arrays.fill(row, 0);
            int key = 1;
            for (int i = 0; i < n; i++) {
                char[] line = scanner.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    int num = Character.isDigit(line[j]) ? line[j] - '0' : line[j] - 'a' + 10;
                    for (int k = 0; k < 4; k++)
                        arr[i + 1][j * 4 + k + 1] = hexa[num][k];
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m * 4; j++) {
                    if (check[i][j] == 0)
                        go(i, j, key++);
                }
            }
            ArrayList<HashMap<Integer, Integer>> v = new ArrayList<>();
            for (int i = 0; i < key; i++)
                v.add(new HashMap<>());
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m * 4; j++) {
                    if (arr[i][j] == 1) {
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k], nc = j + dc[k];
                            if (0 <= nr && nr <= n + 1 && 0 <= nc && nc <= m * 4 + 1 && arr[nr][nc] == 0) {
                                v.get(check[i][j]).put(check[nr][nc], 1);
                            }
                        }
                    }
                }
            }
            StringBuilder ans = new StringBuilder();
            for (HashMap<Integer, Integer> map : v) {
                if (map.size() >= 1)
                    ans.append(keys[map.size() - 1]);
            }
            char[] sortedAns = ans.toString().toCharArray();
            Arrays.sort(sortedAns);
            System.out.printf("Case %d: %s\n", tc++, new String(sortedAns));
        }
        scanner.close();
    }
}