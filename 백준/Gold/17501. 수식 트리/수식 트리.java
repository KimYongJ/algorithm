//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/17501
import java.util.Arrays;

public class Main
{
    static int N, root, Lidx, Ridx;
    static int[][] tree;
    static int[] elements;
    
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
    
    public static int DFS(int node, int flag) {
    	
    	if(node <= N)// 리프노드에 도착한 경우
    		return flag < 0 ? elements[Lidx++] : elements[Ridx--];// 음수일 경우 작은 수 반환

    	int leftValue = DFS(tree[node][0], flag);
    	int rightValue= DFS(tree[node][1], flag * tree[node][2]);
    	
    	// 후위 탐색(음수일 경우 마이너스 반환)
    	return tree[node][2] < 0 ? leftValue - rightValue : leftValue + rightValue;
    }
    public static void main(String[] args) throws Exception {
        N			= read();				// 노드의 개수
        tree 		= new int[200_010][3];	// 트리표현, [0]:왼쪽자식노드 [1]:오른쪽자식노드 [2]:부호
        elements	= new int[N];			// 각 노드당 들어있는 값
        root		= (N<<1) - 1;			// 루트노드 번호
        Ridx		= N - 1;
        // 피연산자 입력
        for (int i = 1; i <= N; i++)
        {
            elements[i-1]	= read();
            tree[i][2]		= 1;
        }
        // 연산자와 연결 정보 입력
        for (int i = N + 1; i <= root; i++)
        {
            tree[i][2] = (System.in.read() == '-') ? -1 : 1;
            tree[i][0] = read();
            tree[i][1] = read();
        }
        
        Arrays.sort(elements);
        
        System.out.println( DFS(root, 1) );
    }
}