//https://www.acmicpc.net/problem/1671
//2초 128MB
//5			// 상어의 마리수 (1<=50)
//4 5 5		// 크기(1<=20억), 속도(1<=20억), 지능(1<=20억)
//10 10 8
//5 7 10
//8 7 7
//8 10 3
////살아남을 수 있는 상어 수의 최솟값 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Main{

	static int time;
	static int N;
	static int match[];
	static int visitTime[];
	static List<Shark> shark;
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());// 상어의 마리수 (1<=50)
		match = new int[N + 1];
		visitTime = new int[N + 1];
		shark = new ArrayList<>();
		adList = new ArrayList[N + 1];
		
		for(int i=1; i<=N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());// 크기(1<=20억)
			int b = Integer.parseInt(st.nextToken());// 속도(1<=20억)
			int c = Integer.parseInt(st.nextToken());// 지능(1<=20억)
			shark.add(new Shark(a,b,c));
			adList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=N; i++)
		{
			Shark s1 = shark.get(i - 1);
			for(int j=1; j<=N; j++)
			{
				if(i == j)
					continue;
				
				Shark s2 = shark.get(j - 1);
				if(s1.a == s2.a && s1.b == s2.b && s1.c == s2.c)
				{
					if(i < j)
						adList[i].add(j);
				}
				else if(s1.a >= s2.a && s1.b >= s2.b && s1.c >= s2.c)
					adList[i].add(j);
			}
		}
				
		int cnt = N;
		
		for(int i=1; i<=N; i++)
		{
			++time;
			if(dfs(i)) --cnt;
			++time;
			if(dfs(i)) --cnt;
		}
		System.out.print(cnt);
	}
	static boolean dfs(int now) {
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
	static class Shark{
		int a, b, c;
		Shark(int a, int b, int c){
			this.a=a;
			this.b=b;
			this.c=c;
		}
	}
}