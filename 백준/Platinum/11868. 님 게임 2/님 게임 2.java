//https://www.acmicpc.net/problem/11868
//2초 512MB
//6 // 돌 더미 수(1<=100)
//9 8 9 8 9 9// 각 돌 더미에 쌓여있는 돌의 개수(1<=십억)
//답 : cubelover

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = arr[i-1] ^ Integer.parseInt(st.nextToken());
		
		System.out.println(arr[N] == 0 ? "cubelover" : "koosaga");
	}
}