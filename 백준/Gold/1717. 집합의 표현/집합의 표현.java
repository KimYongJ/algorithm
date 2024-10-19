//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1717
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	public static int find(int a, int[] parent) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a], parent);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		final String yes = "YES";
		final String no = "NO";
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int parent[] = new int[N+1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a	= Integer.parseInt(st.nextToken());
			int b	= Integer.parseInt(st.nextToken());
			if(b < a)
			{
				int dummy = b;
				b = a;
				a = dummy;
			}
			
			int aParent = find(a, parent);
			int bParent = find(b, parent);
			
			if(cmd == 1)
				sb.append(aParent == bParent ? yes : no).append('\n');
			else
				parent[bParent] = aParent;
		}
		
		System.out.print(sb.toString());
	}
}