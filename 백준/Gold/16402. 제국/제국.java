//https://www.acmicpc.net/problem/16402
//1초 256MB
//5 2// 왕국 수 2<=500, 전쟁 결과 수 1<=2,000
//Kingdom of A
//Kingdom of B
//Kingdom of C
//Kingdom of D
//Kingdom of E
//Kingdom of A,Kingdom of B,1
//Kingdom of C,Kingdom of D,2
////답
//3
//Kingdom of A
//Kingdom of D
//Kingdom of E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int parent[];
	static int idx;
	static HashMap<String, Integer> map1 = new HashMap<>();
	static HashMap<Integer, String> map2 = new HashMap<>();
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 왕국 수 2<=500
		M = Integer.parseInt(st.nextToken());// 전쟁 결과 수 1<=2,000
		parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			String str = br.readLine().split(" ")[2];
			map1.put(str, i);
			map2.put(i, str);
			parent[i] = i;
		}
		
		for(int i=1; i<=M; i++)
		{
			String cmd[] = br.readLine().split(",");
			int n1 = getIdx(cmd[0]);
			int n2 = getIdx(cmd[1]);
			int p1 = find(n1);
			int p2 = find(n2);
			int w = Integer.parseInt(cmd[2]);
			
			if(p1 != p2)
			{
				if(w == 1)
					parent[p2] = p1;
				else
					parent[p1] = p2;
			}
			else if((parent[p1] == n1 || parent[p2] == n2)) {
				int sokguk = parent[p1] == n1 ? n2 : n1;
				int win = w == 1 ? n1 : n2;
				int lose = w == 1 ? n2 : n1;
				if(win == sokguk)
				{
					parent[find(lose)] = win;
					parent[win] = win;
				}
			}
		}
		
		List<String> list = new ArrayList<>();
		
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				list.add(map2.get(i));
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append('\n');
		
		for(String s : list)
			sb.append("Kingdom of ").append(s).append('\n');
		
		System.out.print(sb);
	}
	static int getIdx(String str) {
		return map1.get(str.split(" ")[2]);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}