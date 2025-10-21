//https://www.acmicpc.net/problem/26215
//1초 512MB
//3// 집수(1<=100)
//1 2 3// 쌓여 있는 눈의 양(1<=2000)
//답 : 3 (1440분을 넘기면 -1출력)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int sum = 0;
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(T-->0)
		{
			int n = Integer.parseInt(st.nextToken());
			sum += n;
			max = Math.max(max, n);
		}
		
		int res = Math.max(max, (sum + 1) / 2);
		
		System.out.print(res > 1440 ? -1 : res);
	}
}