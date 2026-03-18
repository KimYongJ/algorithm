//https://www.acmicpc.net/problem/33897
//2초 1024MB
//9 // 수열 길이(1<=300,000)
//6 7 8 9 3 4 5 1 2 // 1<=10^8
//답 : 3 4 // 스킬 사용 횟수 + 한번의 스킬로 베어낸 수의 최댓값
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int prev = Integer.MAX_VALUE;
		int cnt = 0;
		int max = 0;
		int sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(N-->0)
		{
			int now = Integer.parseInt(st.nextToken());
			if(prev > now)
			{
				cnt++;
				sum = 1;
			}
			else
			{
				sum++;
			}
			
			max = Math.max(max, sum);
			prev = now;
		}
		
		System.out.printf("%d %d", cnt, max);
	}
}