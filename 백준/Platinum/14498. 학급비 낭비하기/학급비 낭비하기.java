//https://www.acmicpc.net/problem/14498
//2초 512MB
//2 3 5 // 뽁뽁이 색상 수N(1<=128), 꼭꼭이 색상 수 M(1<=128), 친구들의 수 K(1<=512)
//1 3 0// K줄에 걸쳐 ni, mi, ci가 주어지며 ni은 뽁뽁이, mi 꼭꼭이 모델이며 ci가 0이면 ni를 구매, 1이면 mi를 구매하길 원하는것
//2 3 0
//2 2 1
//2 1 0
//1 3 1
//최소 준비해야하는 사탕 수 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, K;
	static List<Node> pre;
	
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 뽁뽁이 색상 수N(1<=128)
		M = Integer.parseInt(st.nextToken());// 꼭꼭이 색상 수 M(1<=128)
		K = Integer.parseInt(st.nextToken());// 친구들의 수 K(1<=512)
		pre = new ArrayList<>();
		match = new int[K + 1];
		visitTime = new int[K + 1];
		adList = new ArrayList[K + 1];
		
		for(int i=0; i<=K; i++)
		{
			adList[i] = new ArrayList<>();
			match[i] = -1;
		}

		for(int i=0; i<K; i++)
		{
			st = new StringTokenizer(br.readLine());
			int ni = Integer.parseInt(st.nextToken());
			int mi = Integer.parseInt(st.nextToken());
			int ci = Integer.parseInt(st.nextToken());
			
			pre.add(new Node(ni, mi, ci));
		}
		
		for(int i=0; i<K; i++)
		{
			Node l = pre.get(i);
			for(int j=i + 1; j<K; j++)
			{
				Node r = pre.get(j);
				
				if(
					(l.ni == r.ni || l.mi == r.mi) && l.ci != r.ci
					)
				{
					if(l.ci == 1)
						adList[i].add(j);
					else
						adList[j].add(i);
				}
			}
		}
		
		int cnt = 0;
		
		for(int i=0; i<K; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		
		System.out.print(cnt);
	}
	static boolean dfs(int a)
	{
		for(int b : adList[a])
		{
			if(visitTime[b] == time)
				continue;
			
			visitTime[b] = time;
			
			if(match[b] == -1 || dfs(match[b]))
			{
				match[b] = a;
				return true;
			}
		}
		return false;
	}
	static class Node{
		int ni, mi, ci;
		Node(int n, int m, int c){
			ni = n;
			mi = m;
			ci = c;
		}
	}
}