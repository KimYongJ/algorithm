//https://www.acmicpc.net/problem/28088
//2초 1024MB
//5 3 2 // 인원수(3<=200), 처음 소리칠 사람 수(1<=N), 인사 횟수(0<=10^6)
//3 // 인사할 사람 번호 0~N-1번까지
//0
//4
//k번 후 다음 인사할 사람 수 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 인원수(3<=200)
		int M = Integer.parseInt(st.nextToken());// 처음 소리칠 사람 수(1<=N)
		int K = Integer.parseInt(st.nextToken());// 인사 횟수(0<=10^6)
		int arr[] = new int[N];
		int brr[] = new int[N];
		
		while(M-->0)
			arr[Integer.parseInt(br.readLine())] = 1;
		
		while(K-->0)
		{
			for(int i=0; i<N; i++)
				brr[i] = arr[(i+1)%N] ^ arr[(i+N-1)%N];
			
			int tmp[] = arr;
			arr = brr;
			brr = tmp;
		}

		int cnt = 0;
		
		for(int a : arr) if(a == 1) ++cnt;
		
		System.out.print(cnt);
	}
}