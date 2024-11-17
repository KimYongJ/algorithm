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
		
		DFS(b.length - a.length, 0, b.length-1, true);
		
		System.out.print(0);
	}
	public static void DFS(int depth, int s, int e, boolean flag) {
		if(depth == 0)
		{
			if(isSame(s, e, flag))
			{
				System.out.print(1);
				System.exit(0);
			}
			return;
		}

		if(flag) {
			if(b[e] == 'A')
				DFS(depth - 1, s, e-1, flag);
			if(b[s] == 'B')
				DFS(depth - 1, s + 1, e, !flag);
		}else {
			if(b[s] == 'A')
				DFS(depth - 1, s + 1, e, flag);
			if(b[e] == 'B')
				DFS(depth - 1, s, e - 1, !flag);
		}
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