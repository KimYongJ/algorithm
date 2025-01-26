//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5926
//1초 / 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Node{int xpos, id;Node(int x, int i){xpos=x; id=i;}}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		Node node[] = new Node[N];
		
		TreeSet<Integer> set		= new TreeSet<>();	// id의 종류를 담을 배열
		HashMap<Integer,Integer> hm = new HashMap<>();	// id 압축을 위한 map
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int xpos	= Integer.parseInt(st.nextToken());
			int id		= Integer.parseInt(st.nextToken());
			node[i]		= new Node(xpos, id);
			set.add(node[i].id);
		}
		
		Arrays.sort(node, (a,b)->a.xpos - b.xpos);
		
		int rnk = 0;
		for(int s : set)
			hm.put(s, rnk++);
		
		for(int i=0; i<N; i++)
			node[i].id = hm.get(node[i].id);
		
		int count[]	= new int[rnk];
		int cnt		= 0;
		int s		= 0;
		int e		= 0;
		int min 	= Integer.MAX_VALUE;
		while(e<N)
		{
			if(count[node[e].id] == 0)
			{
				cnt++;
			}
			count[node[e].id]++;
			
			if(cnt == rnk)
			{
				while(cnt == rnk)
				{
					min = Math.min(min, node[e].xpos - node[s].xpos);
					if(--count[node[s++].id] == 0)
						--cnt;
				}
			}
			
			++e;			
		}
		System.out.print(min);
	}
}