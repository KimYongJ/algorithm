//https://www.acmicpc.net/problem/15789
//1초 512MB
//10 7// 왕국 수 3<=100,000, 동맹수 1<=200,000
//1 2// 동맹인 두 왕국
//1 3
//2 3
//1 4
//5 6
//8 10
//7 9
//5 9 1// ctp왕국 번호, 한솔 왕국번호, 추가 동맹 기회 k가 주어짐
//답 : 6
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{

	static int N, M;
	static int cnt[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = new int[N + 1];
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = 1;
			parent[i] = i;
		}
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			
			if(p1 == p2)
				continue;
			
			if(parent[p1] < parent[p2])
			{
				parent[p2] = p1;
				cnt[p1] += cnt[p2];
			}
			else
			{
				parent[p1] = p2;
				cnt[p2] += cnt[p1];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int base = find(Integer.parseInt(st.nextToken()));
		int exception = find(Integer.parseInt(st.nextToken()));
		int k = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> b-a);
		
		for(int i=1; i<=N; i++)
		{
			if(parent[i] == base || parent[i] == exception
					|| parent[i] != i)
				continue;
			pq.add(cnt[i]);
		}
		
		while(!pq.isEmpty() && k != 0)
		{
			cnt[base] += pq.poll();
			--k;
		}
		
		System.out.print(cnt[base]);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}