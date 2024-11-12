//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3040

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static int map[] = new int[9];
	static int res[] = new int[7];
	
	public static boolean Bruteforce(int idx, int depth, int sum) {
		if(depth == 7 && sum == 100)
			return true;
		if(depth == 7)
			return false;
		for(int i=idx; i<9; i++)
		{
			res[depth] = map[i];
			if(Bruteforce(i + 1, depth + 1, sum + map[i]))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<9; i++)
			map[i] = Integer.parseInt(br.readLine());
		
		Bruteforce(0, 0, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int r : res)
			sb.append(r).append('\n');
		System.out.print(sb.toString());
	}
}