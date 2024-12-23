//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/13751

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main{
	
	static Set<Integer> set = new TreeSet<>();
	static int[] bar, plates;
	static int b, p, nowBar;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		b		= Integer.parseInt(st.nextToken());	// 바개수 1<=14
		p		= Integer.parseInt(st.nextToken());	// 플렛개수 1<=14
		bar		= new int[b];
		plates	= new int[p];
		
		for(int i=0; i<b; i++)
			bar[i] = Integer.parseInt(br.readLine());
		
		for(int i=0; i<p; i++)
			plates[i] = Integer.parseInt(br.readLine());
		
		for(int w : bar)
		{
			nowBar = w;
			bruteforce(0, 0, 0);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int s : set)
			sb.append(s).append('\n');
		System.out.print(sb);
	}
	public static void bruteforce(int left, int right, int idx) {
		if(left == right)
			set.add(nowBar + left + right);
		
		if(idx == p)
			return;
		
		bruteforce(left + plates[idx], right, idx + 1);
		bruteforce(left, right + plates[idx], idx + 1);
		bruteforce(left, right, idx + 1);
	}
}