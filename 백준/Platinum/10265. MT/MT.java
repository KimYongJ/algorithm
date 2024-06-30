// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int N, K;
	static int cycle_flag[];
	static int cycle_depth[];
	static Node forward[];
	static Node reverse[];
	static boolean forward_visit[];
	static boolean reverse_visit[];
	static ArrayList<ArrayList<Integer>> startList;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int find_reverse(int node) {
		if(reverse_visit[node]) 
			return 0;
		forward_visit[node] = reverse_visit[node] = true;// 정방향 역방향 모두 visit체크 해주어 불필요한 탐색을하지 않게함
		int cnt = 1;
		for(Node now=reverse[node]; now!=null; now=now.next)
		{
			cnt += find_reverse(now.node);
		}
		return cnt;
	}
	public static boolean find_cycle(int depth, int node, int flag) {
		for(Node now=forward[node]; now!=null; now=now.next)
		{
			int nextNode = now.node;
			if(!forward_visit[nextNode]) {
				forward_visit[nextNode] = true;
				cycle_flag[nextNode] = flag;
				cycle_depth[nextNode] = depth;
				if(find_cycle(depth + 1, nextNode, flag))
					return true;
			}else {
				int cycleCnt = depth - cycle_depth[nextNode];
				int compCnt = find_reverse(nextNode);
				ArrayList<Integer> list = new ArrayList<>();
				list.add(cycleCnt); // 사이클의 컴포넌트 개수
				list.add(compCnt);	// 해당 사이클을 포함한 전체 컴포넌트 개수
				startList.add(list);// 전체 list에 넣어줌
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		N		= read();
		K		= read();
		forward	= new Node[N];
		reverse = new Node[N];
		cycle_flag		= new int[N];
		cycle_depth		= new int[N];
		forward_visit	= new boolean[N];
		reverse_visit	= new boolean[N];
		startList		= new ArrayList<>(); // 최소 사이클의 시작점과 사이클의 노드 개수, 그 사이클까지 연결된 막대노드들의 개수의 합을 담을 리스트
		

		for(int i=0; i<N; i++) 
		{
			int a = read() - 1;
			reverse[a] = new Node(i, reverse[a]);
			forward[i] = new Node(a, forward[i]);
		}
		
		// 최소 사이클의 시작점을 찾는 것
		for(int i=0; i<N; i++)
		{
			if(!forward_visit[i]) 
			{
				forward_visit[i] = true;
				cycle_flag[i] = i+1;
				cycle_depth[i] = 0;
				find_cycle(1,i,i+1);
			}
		}
		
        // 배낭 문제
        int result = 0;
		int len = startList.size();
		int dp[][] = new int[len+1][K+1];
		for(int i = 1; i<=len; i++) {
			int min = startList.get(i-1).get(0);
			int max = startList.get(i-1).get(1);
			if( min > K) continue;
			
			for(int j=0; j<=K; j++) {
				dp[i][j] = dp[i-1][j]; // 집합에서 아무것도 선택하지 않은 경우
				for(int k=min; k<=max; k++) {
					if(j-k >= 0) {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-k] + k);
					}
				}
                if(j == K && dp[i][K] > result){
                    result = dp[i][K];
                }
			}
		}
		
		System.out.print(result);
		
	}
}