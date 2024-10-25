//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/17501
import java.util.Arrays;

class Main{
	
	static int N, root, leftIdx, rightIdx;
	static int arr[];
	static int tree[][];
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
    
	public static int DFS(int node, int flag) {
		if(node <= N)// 리프노드일 경우
			return flag < 0 ? arr[leftIdx++] : arr[rightIdx--];// 부호가 음수이면 작은 리프노드수를, 양수면 큰리프노드수를 반환

		int leftValue = DFS(tree[node][0], flag);					// 왼쪽 탐색
		int rightValue= DFS(tree[node][1], flag * tree[node][2]);	// 오른쪽 탐색
		// 현재 노드의 부호에 따라 왼쪽 오른쪽 값을 더하거나 빼준다.
		return tree[node][2] < 0 ? leftValue - rightValue : leftValue + rightValue;
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		root	= (N << 1) - 1;
		arr		= new int[N];
		tree	= new int[root+1][3];
		rightIdx= N - 1;
		
		for(int i=1; i<=N; i++)
			arr[i-1] = read();		// 리프노드 값 입력
		// 부호 입력
		for(int i=N+1; i<=root; i++)
		{
			tree[i][2] = System.in.read()=='-' ? -1 : 1;
			tree[i][0] = read();
			tree[i][1] = read();
		}
		
		Arrays.sort(arr);			// 리프노드 값을 오름차순 정렬하여 추후 후위탐색에서 사용
		
		System.out.print( DFS(root, 1) );
	}
}
