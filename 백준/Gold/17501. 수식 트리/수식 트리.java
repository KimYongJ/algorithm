//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/17501
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main
{
    static int n, lp, rp;
    static int[][] tree;
    static ArrayList<Integer> elements;
    
    static int dfs(int node, int m)
    {
        // 리프 노드인 경우 (즉, 피연산자 노드인 경우)
        if (node <= n)
        {
            // 리프 노드까지 도달했을 때의 m값을 곱한다.
            // m은 루트부터 현재 노드의 최종 부호이다.
            if (m < 0) return elements.get(lp++);
            return elements.get(rp--);
        }
        // 연산자 노드인 경우, 양쪽 자식에 대해 재귀한다.
        // 이 때, 우측 자식에 대해서만 m값에 현재 노드의 값(+-1)을 곱해준다.
        int leftRes = dfs(tree[node][0], m);
        int rightRes = dfs(tree[node][1], m * tree[node][2]);
        
        if (tree[node][2] < 0) return leftRes - rightRes;
        return leftRes + rightRes;
    }
    
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n			= Integer.parseInt(br.readLine());
        tree 		= new int[200_010][3];
        elements	= new ArrayList<>();
        
        // 피연산자 입력
        for (int i = 1; i <= n; i++)
        {
            int val = Integer.parseInt(br.readLine());
            elements.add(val);
            tree[i][2] = 1;
        }
        
        // 연산자와 연결 정보 입력
        for (int i = n + 1; i < n<<1; i++)
        {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            tree[i][0] = l;
            tree[i][1] = r;
            tree[i][2] = (type == '-') ? -1 : 1;
        }
        
        Collections.sort(elements);
        
        rp = n - 1;
        
        int root = 2 * n - 1;
        System.out.println( dfs(root, 1) );
    }
}