//https://www.acmicpc.net/problem/10434
//1초 256MB
//4 // 테스트 케이스 1<=1,000
//1 1 // 테케 번호, 탐색할 숫자1 ≤ m ≤ 10000
//2 7
//3 383
//4 1000
//답
//1 1 NO
//2 7 YES
//3 383 YES
//4 1000 NO
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			boolean visit[] = new boolean[10001];
			
			sb.append(n).append(' ').append(t).append(' ')
				.append(isPrime(t) && isHappy(t, visit) ? "YES" : "NO").append('\n');
		}
		System.out.print(sb);
	}
	static boolean isHappy(int n, boolean visit[]) {
		if(n == 1) return true;
		if(visit[n]) return false;
		
		visit[n] = true;
		
		int next = 0;
		
		while(n != 0)
		{
			next += (int)Math.pow((n%10),2);
			n/=10;
		}
		
		return isHappy(next, visit);
	}
	static boolean isPrime(int n) {
		if(n <= 2) return false;
		
		for(int i=2; i*i<=n; i++)
			if(n % i == 0) return false;
		
		return true;
	}
}