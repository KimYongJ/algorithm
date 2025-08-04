//https://www.acmicpc.net/problem/31477
//1초 1024MB
//7// 방의 수 (2<=100,000)
//1 2 3 //A,B,V가 주어지며 A,B는 방번호(1<=N), V는 두께(1<=1,000)
//1 3 4
//2 4 2
//2 5 2
//3 6 1
//3 7 2
//1번방은 양갈래가 묶인방이며 덩굴을 자르기 위해 필요한 힘의 합의 최솟값을 출력한다.
//답6
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static List<Node> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adList = new ArrayList[N + 1];

		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adList[a].add(new Node(b,v));
			adList[b].add(new Node(a,v));
		}
		
		System.out.print(dfs(1, 0));

	}
	static int dfs(int now, int parent) {
		int sum = 0;
		
		for(Node next : adList[now])
		{
			if(parent == next.next)
				continue;
			
			sum += Math.min(next.v, dfs(next.next, now));
		}
		// 리프노드 일경우 max로 반환
		return sum == 0 ? Integer.MAX_VALUE : sum;
	}
	static class Node{
		int next, v;
		Node(int n, int v){
			this.next = n;
			this.v = v;
		}
	}
}