//https://www.acmicpc.net/problem/13166
//1초 512MB
//3// 용의자 수(1<=200,000)
//1 5 2 6// 용의자 수만큼, i번째 용의자의 친구를 나타내는 정수 Ai와 해당 친구를 변호인으로 두기 위한 임계값 KAi, 다른 친구정수 Bi와 해당 친구를 변호인으로 두기위한 KBi가주어짐(친구번호 : 1<=2*용의자수 / 임계값은 0<=백만)
//2 7 3 8
//3 9 1 10
//모든 용의자가 변호인을 갖게 되는 파티의 최소 비용K 출력 및 없으면 -1출력
//답 : 9

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static final int LEN = 400_000;
	static int N, K;
	static int time;
	static int visitTime[];
	static int match[];
	static List<Friend> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visitTime = new int[LEN + 1];
		match = new int[LEN + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			adList[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			adList[i].add(new Friend(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			adList[i].add(new Friend(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		int res = -1;
		int s = 0;
		int e = 1_000_000;
		while(s <= e)
		{
			K = (s + e) >> 1;
			if(cal())
			{
				res = K;
				e = K - 1;
			}
			else
				s = K + 1;
		}
		
		System.out.print(res);
	}
	static boolean dfs(int suspect) {
		for(Friend f : adList[suspect])
		{
			if(f.cost <= K)
			{
				
				if(visitTime[f.num] == time)
					continue;
				
				visitTime[f.num] = time;
				
				if(match[f.num] == 0 || dfs(match[f.num]))
				{
					match[f.num] =  suspect;
					return true;
				}
			}
		}
		return false;
	}
	static boolean cal() {
		Arrays.fill(match, 0);
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			++time;
			if(dfs(i))
				++cnt;
		}
		
		return cnt == N;
	}
	static class Friend{
		int num, cost;
		Friend(int n, int c){
			num = n;
			cost = c;
		}
	}
}