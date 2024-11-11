import java.util.*;

public class Main {
    static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        M += 2;
        board = new int[N+2][M];
        visited = new boolean[N+2][M];

        // Read input and prepare the board
        for (int i = 1; i <= N; i++) {
            String line = scanner.next();
            for (int j = 1; j <= line.length(); j++) {
                board[i][j] = line.charAt(j-1) == 'x' ? 1 : 0;
            }
        }
        N += 2;

        // Initialize the ocean set
        Set<Point> ocean = new HashSet<>();
        ocean.add(new Point(0, 0));

        // Perform DFS
        DFS(ocean);
        if (result.size() > 0) {
            result.remove(result.size() - 1);
        }
        
        // Output result
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int count : result) {
                System.out.print(count + " ");
            }
        }
    }

    public static int DFS(Set<Point> ocean) {
        Set<Point> island = new HashSet<>();
        int h = 0;
        for (Point p : ocean) {
            if (!visited[p.y][p.x]) {
                island.addAll(BFS(p.y, p.x, 4, 0));
            }
        }
        for (Point p : island) {
            if (!visited[p.y][p.x]) {
                h = Math.max(h, DFS(BFS(p.y, p.x, 8, 1)) + 1);
            }
        }
        while (result.size() <= h) {
            result.add(0);
        }
        result.set(h, result.get(h) + 1);
        return h;
    }

    public static Set<Point> BFS(int y, int x, int d, int n) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(y, x));
        Set<Point> S = new HashSet<>();
        while (!dq.isEmpty()) {
            Point p = dq.poll();
            if (visited[p.y][p.x]) {
                continue;
            }
            visited[p.y][p.x] = true;
            for (int i = 0; i < d; i++) {
                int y1 = p.y + dy[i];
                int x1 = p.x + dx[i];
                if (y1 >= 0 && y1 < N && x1 >= 0 && x1 < M && !visited[y1][x1]) {
                    if (board[y1][x1] == n) {
                        dq.offer(new Point(y1, x1));
                    } else {
                        S.add(new Point(y1, x1));
                    }
                }
            }
        }
        return S;
    }

    static class Point {
        int y, x;
        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}