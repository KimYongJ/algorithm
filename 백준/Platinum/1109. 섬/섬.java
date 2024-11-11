//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1109
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    static int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static int Y, X;
    static int[][] board;
    static boolean[][] visited;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args)throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        Y		= Integer.parseInt(st.nextToken())+2;
        X		= Integer.parseInt(st.nextToken())+2;
        board	= new int[Y][X];
        visited	= new boolean[Y][X];

        for (int i = 1; i <= Y-2; i++)
        {
            String line = br.readLine();
            for (int j = 1; j <= X-2; j++)
            	if(line.charAt(j-1) == 'x')
            		board[i][j] = 1;
        }

        Set<Point> ocean = new HashSet<>();
        ocean.add(new Point(0, 0));

        DFS(ocean);
        if (result.size() > 0) {
            result.remove(result.size() - 1);
        }

        if (result.isEmpty()) 
            System.out.println(-1);
        else
        {
        	StringBuilder sb = new StringBuilder();
            for (int count : result)
                sb.append(count).append(' ');
            System.out.print(sb.toString());
        }
    }

    public static int DFS(Set<Point> ocean) {
        Set<Point> island = new HashSet<>();
        int h = 0;
        for (Point p : ocean)
        {
            if (!visited[p.y][p.x])
            {
                island.addAll(BFS(p.y, p.x, 4, 0));
            }
        }
        for (Point p : island)
        {
            if (!visited[p.y][p.x])
            {
                h = Math.max(h, DFS(BFS(p.y, p.x, 8, 1)) + 1);
            }
        }
        while (result.size() <= h)
        {
            result.add(0);
        }
        result.set(h, result.get(h) + 1);
        return h;
    }

    public static Set<Point> BFS(int y, int x, int d, int n) {
        Deque<Point> dq = new ArrayDeque<>();
        dq.offer(new Point(y, x));
        Set<Point> S = new HashSet<>();
        while (!dq.isEmpty())
        {
            Point p = dq.poll();
            if (visited[p.y][p.x])
            {
                continue;
            }
            visited[p.y][p.x] = true;
            for (int i = 0; i < d; i++)
            {
                int y1 = p.y + dy[i];
                int x1 = p.x + dx[i];
                if (y1 >= 0 && y1 < Y && x1 >= 0 && x1 < X && !visited[y1][x1])
                {
                    if (board[y1][x1] == n)
                    {
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