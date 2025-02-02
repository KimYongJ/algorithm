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
		
		for(int i=5; i<=N; i++)
			if(arr[i-1] + arr[i-3] + arr[i-4] != 3)
				arr[i] = 1;

		System.out.print(arr[N] == 1 ? "SK" : "CY");
	}
}