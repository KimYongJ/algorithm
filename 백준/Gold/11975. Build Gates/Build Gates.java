//https://www.acmicpc.net/problem/11975
// 2초 512MB
//14 // 걸음 숫자(1<=1,000)
//NNNESWWWSSEEEE // N (북), E (동), S (남), 또는 W (서)
//답 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main{
	
	static final int offset = 1002;
	static final int len = 2010;
	static int N;
	static Set<Integer> vertax;
	static Set<Integer> edge;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		vertax = new HashSet<>();
		edge = new HashSet<>();
		
		int Y = offset;
		int X = offset;
		int u = Y*len + X;
		int v = -1;
		vertax.add(u);
		
		String str = br.readLine();
		for(int i=0; i<str.length(); i++)
		{
			char c = str.charAt(i);
			switch(c)
			{
			case 'N':--Y;break;
			case 'S':++Y;break;
			case 'E':++X;break;
			case 'W':--X;break;
			}
			v = Y*len + X;
			vertax.add(v);
			
			int min = Math.min(u,v);
			int max = Math.max(u,v);
			
			edge.add(max * len + min);
			
			u = v;
		}
		
		int res = edge.size() - vertax.size() + 1;
		
		System.out.print(res);
	}
}