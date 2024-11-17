//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1535
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, L[], J[];
	static int res;
	
	public static void bruteforce(int idx, int energy, int sum) {
		if(energy < 1)
			return;
		
		res = Math.max(sum, res);
		
		if(N<=idx)
			return;
		
		bruteforce(idx+1, energy, sum);
		bruteforce(idx+1, energy-L[idx], sum+J[idx]);
		
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		L = new int[N];	// 잃는 체력
		J = new int[N];	// 얻는 기쁨
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			L[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			J[i] = Integer.parseInt(st.nextToken());
		
		bruteforce(0, 100, 0);
		
		System.out.print(res);
	}
}