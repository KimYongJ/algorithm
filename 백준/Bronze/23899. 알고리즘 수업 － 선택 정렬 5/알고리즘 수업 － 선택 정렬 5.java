//https://www.acmicpc.net/problem/23899
//1초 512MB
//5 // 배열크기 (5<=10,000)
//3 1 2 5 4 // 원소 수1<= 10^9
//2 1 3 4 5
//같으면 1 아니면 0 답 : 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		int brr[] = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) brr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=arr.length-1; i>0 && !isSame(arr, brr); i--)
		{
			int max = arr[i];
			int idx = i;
			
			for(int j=0; j<i; j++)
			{
				if(arr[j] > max)
				{
					max = arr[j];
					idx = j;
				}
			}
			if(idx != i)
			{
				arr[idx] = arr[i];
				arr[i] = max;
			}
		}
		System.out.print(isSame(arr, brr) ? 1 : 0);
	}
	static boolean isSame(int arr[], int brr[]) {
		for(int i=0; i<arr.length; i++)
			if(arr[i] != brr[i]) return false;
		return true;
	}
}