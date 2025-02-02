//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9657
//1초 / 128mb

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+5];
		arr[1] = arr[3] = arr[4] = 1;
		// 게임이론 : 상대가 이기는 수를 고르지 않는다. 상대가 이길 수 있다면 내가 진것임
		// 현재에 올 수 있는 모든 위치의 값이 상근이면 창영 이가 이긴다.
		for(int i=5; i<=N; i++)
			if(arr[i-1] + arr[i-3] + arr[i-4] != 3)
				arr[i] = 1;

		System.out.print(arr[N] == 1 ? "SK" : "CY");
	}
}