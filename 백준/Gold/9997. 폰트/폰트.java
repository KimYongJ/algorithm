//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9997
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	static final int TOTAL = (1<<26)-1;
	static int N, arr[];
	static int cnt;
	
	public static void bruteforce(int idx, int bitmask) {
		if(bitmask == TOTAL)
		{
			cnt += 1<<(N-idx);
			return;
		}
		if(idx == N)
			return;
		
		bruteforce(idx + 1, bitmask);
		bruteforce(idx + 1, bitmask | arr[idx]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		arr = new int[N];
		
		int total = 0;
		for(int i=0; i<N; i++)
			for(char c : br.readLine().toCharArray())
				total |= arr[i] |= 1<<(c-'a');
		
		if(total == TOTAL)
			bruteforce(0,0);
		
		System.out.print(cnt);
	}
}