//https://www.acmicpc.net/problem/33674
//0.2초 1024MB
//4 5 7 // 별이떨어지는 위치 수(1<=100), 일수(1<=100), 기준개수(1<=100)
//2 3 1 2 // 떨어지는 별 개수
//답 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			max = Math.max(max, Integer.parseInt(st.nextToken()));
		
		int m = K / max; // 청소없이 버틸 수 있는 최대 일수
		
		System.out.print((D-1) / m);
	}
}