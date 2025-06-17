//https://www.acmicpc.net/problem/3683
//1초 128MB
//2 // 테스트 케이스 수 ( 1<=100 )
//1 1 2 // 고양이 수(1<=100), 개의 수(1<=100), 투표한 시청자의 수(0<=500)
//C1 D1// 시청자의 수 만큼 주어지며, 순서는 다음 라운드에 진출 시킬 동물, 탈락시킬 동물 이다. 고양이는 C로시작, 개는 D로시작하며 뒤 숫자는 동물의 번호다
//D1 C1
//1 2 4
//C1 D1
//C1 D1
//C1 D2
//D2 C1
//각 테스트 케이스마다 투표가 반영되는 시청자의 최댓값을 출력
//1
//3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int V;
	static int time;
	static int visitTime[];
	static int match[];
	static List<Integer> adList[];
	static List<Node> cat;
	static List<Node> dog;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		init();
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			clear();
			
			st = new StringTokenizer(br.readLine());
			st.nextToken();// N은 버림
			st.nextToken();// M도 버림
			V = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<V; i++)
			{
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				int num1 = Integer.parseInt(a.substring(1));
				int num2 = Integer.parseInt(b.substring(1));
				if(a.charAt(0) == 'C')// 고양이를 올릴 사람
					cat.add(new Node(num1, num2));
				else// 강아지를 올릴 사람
					dog.add(new Node(num1, num2));
			}
			
			// 서로 모순인 것을 간선으로 연결
			for(int i=0; i<cat.size(); i++)
			{
				Node c = cat.get(i);
				for(int j=0; j<dog.size(); j++)
				{
					Node d = dog.get(j);
					// 고양이를 올릴 사람과, 고양이를 떨어뜨릴 사람의 고양이 번호가 같으면 연결
					// 강아지를 올릴 사람과, 강아지를 떨어뜨릴 사람의 강아지 번호가 같으면 연결
					if(c.like == d.disLike || c.disLike == d.like)
					{
						adList[i + 1].add(j + 1);
					}
				}
			}
			
			int cnt = 0;
			
			for(int i=1; i<=cat.size(); i++)
			{
				++time;
				if(dfs(i))
					++cnt;
			}
			
			sb.append(V - cnt).append('\n');
		}
		System.out.print(sb);
	}
	static boolean dfs(int now)
	{
		for(int next : adList[now])
		{
			if(visitTime[next] == time)
				continue;
			
			visitTime[next] = time;
			
			if(match[next] == 0 || dfs(match[next]))
			{
				match[next] = now;
				return true;
			}
		}
		return false;
	}
	static void clear()
	{
		cat.clear();
		dog.clear();
		for(int i=0; i<=501; i++)
		{
			match[i] = 0;
			adList[i].clear();
		}
	}
	static void init()
	{
		match = new int[502];
		visitTime = new int[502];
		adList = new ArrayList[502];
		cat = new ArrayList<>();
		dog = new ArrayList<>();
		for(int i=0; i<=501; i++)
			adList[i] = new ArrayList<>();
	}
	static class Node
	{
		int like, disLike;
		Node(int l, int d)
		{
			like = l;
			disLike = d;
		}
	}
}