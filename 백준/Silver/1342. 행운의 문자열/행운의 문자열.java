// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	

	static int cnt;

	public static void backtracking(int arr[], int len , int depth,int before) {
		if(len == depth) 
		{
			cnt++;
			return;
		}
		
		for(int i=1; i<11; i++)
		{
			if(arr[i] == 0) 
				continue;
			if(before != i) 
			{
				arr[i]--;
				backtracking(arr, len, depth + 1, i);
				arr[i]++;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arr[] 	= new int[28];
		String str 	= br.readLine();
		int len 	= str.length()+1;

		for(char c : str.toCharArray())
			arr[c-96]++;
		
		int arr2[] = new int[11];
		for(int i=0,idx=1; i<28; i++) // 빠른 연산을 위한 데이터 전처리
			if(arr[i] >0) 
				arr2[idx++] = arr[i];

		

		
		backtracking(arr2, len, 1,0);
		
		System.out.print(cnt);
	}
}