//https://www.acmicpc.net/problem/18229
//2초 512MB
//4 5 20 // 인원수(1<=100), 손을 내미는 수(1<=100), 목표 값(1<=10,000,000)
//3 5 2 1 4 // 인원수마다 손을 내미는 수가 주어짐
//1 8 2 5 8
//1 5 2 3 3
//1 1 8 9 9
//답 : 2 5 // 도달한 최초의 사람번호, 뻗은 횟수
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());// 인원수(1<=100)
		int M = Integer.parseInt(st.nextToken());// 손을 내미는 수(1<=100)
		int K = Integer.parseInt(st.nextToken());// 목표 값(1<=10,000,000)
		int sum[] = new int[N + 1];
		int arr[][] = new int[N + 1][M + 1];
		
		for(int y=1; y<=N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=M; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int x=1; x<=M; x++) {
			for(int y=1; y<=N; y++) {
				sum[y] += arr[y][x];
				if(sum[y] >= K) {
					System.out.print(y + " " + x);
					return;
				}
			}
		}
		
		
	}
}