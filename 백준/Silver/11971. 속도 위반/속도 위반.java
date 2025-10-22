//https://www.acmicpc.net/problem/11971
//2초 512MB
//3 3 // 도로 구간의 수, 연정이가 달린 구간의 수
//40 75// 도로의 길이, 제한속도
//50 35// 도로의 길이, 제한속도
//10 45// 도로의 길이, 제한속도
//40 76// 연정이가 달린 길이, 속도
//20 30// 연정이가 달린 길이, 속도
//40 40// 연정이가 달린 길이, 속도
//답 : 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());// 도로 구간의 수
		int m = Integer.parseInt(st.nextToken());// 연정이가 달린 구간의 수
		int arr[] = new int[101];
		int res = 0;
		int now = 0;
		int len = 0;
		
		while(n-->0)
		{
			st = new StringTokenizer(br.readLine());
			len += Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			
			while(now < len)
				arr[++now] = speed;
		}
		
		now = 0;
		len = 0;
		while(m-->0)
		{
			st = new StringTokenizer(br.readLine());
			len += Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			
			while(now < len)
				res = Math.max(res, speed - arr[++now]);
		}
		
		System.out.print(res);
	}
}