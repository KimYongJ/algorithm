//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20440
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

class Pos{int s,e;Pos(int s, int e){this.s=s;this.e=e;}}

class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		TreeSet<Integer> set = new TreeSet<>();
		int N		= read();
		Pos[] pos	= new Pos[N];
		
		for(int i=0; i<N; i++)
		{
			int s = read();
			int e = read();
			pos[i] = new Pos(s,e);
			set.add(s);
			set.add(e);
		}
		
		ArrayList<Integer> idx = new ArrayList<>(set);
		int len		= set.size();
		int psum[]	= new int[len+1];
		
		for(Pos p : pos)
		{
			int s = Collections.binarySearch(idx, p.s);
			int e = Collections.binarySearch(idx, p.e);
			psum[s]++;
			if(p.s != p.e)
				psum[e]--;
		}

		int max		= psum[0];
		int start	= -1;
		int end		= -1;
		
		for(int i=1; i<=len; i++)
			max = Math.max(max, psum[i] += psum[i-1]);

		for(int i=0; i<=len; i++)
			if(psum[i] == max && start < 0)
			{
				start = i;
				end = i;
				max = psum[i];
			}
			else if(psum[i] == max && i-1 == end)
				end = i;

		
		sb.append(max).append('\n').append(idx.get(start)).append(' ').append(idx.get(end+1));

		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}