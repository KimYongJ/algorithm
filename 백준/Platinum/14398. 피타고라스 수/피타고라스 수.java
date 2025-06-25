//https://www.acmicpc.net/problem/14398
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int time;
	static int match[];
	static int visitTime[];
	static List<Integer> adList[];
	static List<Integer> even;
	static List<Integer> odd;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		match = new int[N + 1];
		visitTime = new int[N + 1];
		adList = new ArrayList[N + 1];
		even = new ArrayList<>();
		odd = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			int num = Integer.parseInt(st.nextToken());
			adList[i] = new ArrayList<>();
			if(num % 2 == 0)
				even.add(num);
			else
				odd.add(num);
		}
		
		for(int i=0; i<even.size(); i++)
		{
			int e = even.get(i);
			for(int j=0; j<odd.size(); j++)
			{
				int o = odd.get(j);
				
				if(gcd(e, o) == 1 && isSqure((long)e * e + (long)o * o))
					adList[i + 1].add(j + 1);
			}
		}
		
		int cnt = 0;
		
		for(int i=1; i<=even.size(); i++)
		{
			++time;
			
			if(dfs(i))
				++cnt;
		}
		
		System.out.print(cnt);
	}
	static boolean dfs(int i)
	{
		for(int j : adList[i])
		{
			if(visitTime[j] == time)
				continue;
			
			visitTime[j] = time;
			
			if(match[j] == 0 || dfs(match[j]))
			{
				match[j] = i;
				return true;
			}
		}
		return false;
	}
	static boolean isSqure(long x) {
		long sq = (int)Math.sqrt(x);
		return x == sq*sq;
	}
	static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}