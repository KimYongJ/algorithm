//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/17501
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main
{
    static int N, Lidx, Ridx;
    static int[][] tree;
    static int[] elements;

    public static int DFS(int node, int flag) {
    	// 리프노드에 도착한 경우
    	if(node <= N)
    	{
    		if(flag < 0)// 음수일 경우 작은 수 반환
    			return elements[Lidx++];
    		return elements[Ridx--];
    	}
    	
    	int leftValue = DFS(tree[node][0], flag);
    	int rightValue= DFS(tree[node][1], flag * tree[node][2]);
    	
    	if(tree[node][2] < 0)// 음수일 경우
    		return leftValue - rightValue;
    	return leftValue + rightValue;
    }
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N			= Integer.parseInt(br.readLine());	// 노드의 개수
        tree 		= new int[200_010][3];				// 트리표현, [0]:왼쪽자식노드 [1]:오른쪽자식노드 [2]:부호
        elements	= new int[N];						// 각 노드당 들어있는 값
        
        // 피연산자 입력
        for (int i = 1; i <= N; i++)
        {
            elements[i-1]	= Integer.parseInt(br.readLine());
            tree[i][2]		= 1;
        }
        
        // 연산자와 연결 정보 입력
        for (int i = N + 1; i < N<<1; i++)
        {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	char c = st.nextToken().charAt(0);
            tree[i][2] = (c == '-') ? -1 : 1;
            tree[i][0] = Integer.parseInt(st.nextToken());
            tree[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(elements);
        
        Ridx = N - 1;
        
        int root = (N<<1) - 1;
        System.out.println( DFS(root, 1) );
    }
}