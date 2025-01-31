//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19947
//1초 / 512mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H		= Integer.parseInt(st.nextToken());	// 초기비용 만<=십만
		int Y		= Integer.parseInt(st.nextToken());	// 투자 기간 0<=10
		int arr[]	= new int[11];
		arr[0]		= H;
		
		for(int i=1; i<=Y; i++)
		{
			arr[i] = (int)(arr[i-1] * 1.05);
			
			if(3 <= i)
				arr[i] = Math.max(arr[i], (int)(arr[i-3] * 1.2));
		
			if(5 <= i)
				arr[i] = Math.max(arr[i], (int)(arr[i-5] * 1.35));
		}
		
		
		System.out.print(arr[Y]);
	}
}