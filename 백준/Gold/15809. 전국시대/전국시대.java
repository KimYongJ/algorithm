//https://www.acmicpc.net/problem/15809
//1초 128MB
//5 3// 국가수(1<=100,000), 기록의 수(1<=100,000)
//10// N줄에 걸쳐 각 국가의 병력이 주어짐(1<=10,000)
//20
//30
//40
//50
//1 1 2// M개의 줄에 걸쳐 O,P,Q가주어짐 
//1 3 4// O가 1이면 P,Q가 동맹을 의미, 2인경우 P,Q가 전쟁을 벌임을 의미
//2 1 3
////답
//2
//40 50
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	static int parent[];
	static long cnt[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 국가수(1<=100,000)
		M = Integer.parseInt(st.nextToken());// 기록의 수(1<=100,000)
		parent = new int[N + 1];
		cnt = new long[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			cnt[i] = Integer.parseInt(br.readLine());
			parent[i] = i;
		}
		
		for(int i=1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			union(o, p1, p2);
		}
		
		List<Long> force = new ArrayList<>();
		int country = 0;
		
		for(int i=1; i<=N; i++)
		{
			if(parent[i] == i)
			{
				country++;
				force.add(cnt[i]);
			}
		}
		
		Collections.sort(force);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(country).append('\n');
		
		for(int i=0; i<force.size(); i++)
			sb.append(force.get(i)).append(' ');
		
		System.out.print(sb);
	}
	static void union(int o, int p1, int p2) {
		if(o == 1)
		{
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
			return;
		}
		
		if(cnt[p1] == cnt[p2])
		{
			parent[p1] = parent[p2] = 0;
		}
		else if(cnt[p1] > cnt[p2])
		{
			cnt[p1] = cnt[p1] - cnt[p2];
			parent[p2] = parent[p1];
		}
		else {
			cnt[p2] = cnt[p2] - cnt[p1];
			parent[p1] = parent[p2];
		}
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}