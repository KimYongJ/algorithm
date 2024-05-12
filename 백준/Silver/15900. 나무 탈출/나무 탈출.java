import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) list.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        dfs(0, 1);
        if (result % 2 != 0) System.out.println("Yes");
        else System.out.println("No");

    }

    static void dfs(int depth, int v) {
        for (int i = 0; i < list.get(v).size(); i++) {
            int next = list.get(v).get(i);
            if (!visited[next]) {
                visited[next] = true;
                dfs(depth + 1, next);
            }
        }

        if (list.get(v).size() == 1) result += depth;
    }

}
