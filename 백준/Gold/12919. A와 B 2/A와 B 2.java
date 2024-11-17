//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/12919

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static char[] a, b;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a = br.readLine().toCharArray();
		b = br.readLine().toCharArray();
		
		boolean res = DFS(b.length - a.length, 0, b.length-1, true);
		
		System.out.print(res ? 1 : 0);
	}
	public static boolean DFS(int depth, int s, int e, boolean flag) {
		if(depth == 0)
			return isSame(s, e, flag);

		if(flag)
		{
			if(b[e] == 'A' && DFS(depth - 1, s, e-1, flag))
				return true;
			if(b[s] == 'B' && DFS(depth - 1, s + 1, e, !flag))
				return true;
		}
		else
		{
			if(b[s] == 'A' && DFS(depth - 1, s + 1, e, flag))
				return true;
			if(b[e] == 'B' && DFS(depth - 1, s, e - 1, !flag))
				return true;
		}
		return false;
	}
	public static boolean isSame(int s, int e, boolean flag) {
		if(flag)
		{
			for(int i=0; i<a.length;)
				if(a[i++] != b[s++])
					return false;
			return true;
		}
		else
		{
			for(int i=0; i<a.length;)
				if(a[i++] != b[e--])
					return false;
			return true;
		}
	}
}