// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = Integer.MAX_VALUE;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 노드 갯수
		int e = Integer.parseInt(st.nextToken()); // 간선의 갯수
		
		long[][] arr = new long[n+1][n+1]; // 노드 갯수를 2차원 배열로 선언
		long dist = 0;
		for(int i=0; i<=n; i++)
			for(int j=0; j<=n; j++) {
				if(i==j)continue;
				arr[i][j] = MAX;
			}
		
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine()); // 정점과 간선을 입력 받는다.
			int aNode = Integer.parseInt(st.nextToken());
			int bNode = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());
			arr[aNode][bNode] = dist;
			arr[bNode][aNode] = dist;
		}
		st = new StringTokenizer(br.readLine()); // 반드시 거쳐야 하는 노드를 받는다.
		int mNode1 = Integer.parseInt(st.nextToken()); // 반드시 방문 해야 하는 노드 1
		int mNode2 = Integer.parseInt(st.nextToken()); // 반드시 방문 해야 하는 노드 2
		
		// 플로이드 와샬 알고리즘
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(arr[i][j] > arr[i][k]+arr[k][j] ) {
						dist = arr[i][k]+arr[k][j];
						arr[i][j] = dist;
						arr[j][i] = dist;
					}
				}
			}
		}
		
		// 1번 방법 -> 반드시 거칠노드 1-> 반드시 거칠노드 2-> N
		long case1 = arr[1][mNode1] + arr[mNode1][mNode2] + arr[mNode2][n];
		// 2번 방법 -> 반드시 거칠노드 2-> 반드시 거칠노드 1-> N
		long case2 = arr[1][mNode2] + arr[mNode1][mNode2] + arr[mNode1][n];
		
		long result = Math.min(case1, case2);
		if(result>=MAX)
			result = -1;
		System.out.println(result);
	}	
	

}