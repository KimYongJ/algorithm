// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, animalCnt[];
	static boolean isSheep[];
	static ArrayList<Integer>[] list;
	
	public static long DFS( int node ) {
		long sum = 0;
		
		for(int nextNode : list[node]) 
			sum += DFS(nextNode);
		
		return isSheep[node] 
				? 
				sum + animalCnt[node]
				:
				sum - animalCnt[node] <= 0 ? 0 : sum - animalCnt[node];
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 			= Integer.parseInt(br.readLine());
		isSheep 	= new boolean[N+1]; 	// 양인지 아닌지 체크
		animalCnt 	= new int[N+1]; 		// 양과 늑대가 몇마리인지 체크
		list 		= new ArrayList[N+1];	// 1노드 부터 아래 노드로 연결
		
		for(int i=1; i<=N; i++) 
			list[i] = new ArrayList<>();
		
		for(int i=2; i<=N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			isSheep[i] = "S".equals(st.nextToken()); // 양이면 true
			animalCnt[i] = Integer.parseInt(st.nextToken()); // 양과 늑대의 마리수를 입력
			list[Integer.parseInt(st.nextToken())].add(i); // 입력되는 숫자가 인덱스로 갈 수 있게 연결( 즉 1이 나머지들로 갈 수 있도록 연결 )
		}
		
		System.out.println(DFS(1));
	}
}