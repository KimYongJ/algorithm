// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int N, arr[], left, right, cnt;
	public static void getLeft (int idx) {while(idx == left)  {left++;}}
	public static void getRight(int idx) {while(idx == right) {right--;}}
	public static boolean search(int baseNum, int baseIdx) {
		int num;
		while(left < right) {
			getLeft(baseIdx);
			getRight(baseIdx);
			if(right < 0 || left >= N)
				break;
			if(left == right)
				continue;
			
			num = arr[left] + arr[right];
			if(num == baseNum) 
				return true;
			else if(num > baseNum) 
				right--;
			else 
				left++;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++)  // 가장 큰 수부터 차례대로 탐색
		{
			left  = 0;
			right = N-1;
			if ( search(arr[i],i) )
				cnt++;
		}
		System.out.print(cnt);
	}
}