//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2096

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[][] = new int[N][3];
		int brr[][] = new int[N][3];
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			brr[i][0] = arr[i][0] = Integer.parseInt(st.nextToken());
			brr[i][1] = arr[i][1] = Integer.parseInt(st.nextToken());
			brr[i][2] = arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N; i++) {
			arr[i][0] += Math.max(arr[i-1][0], arr[i-1][1]);
			arr[i][2] += Math.max(arr[i-1][1], arr[i-1][2]);
			arr[i][1] += Math.max(arr[i-1][2],Math.max(arr[i-1][0], arr[i-1][1]));
			brr[i][0] += Math.min(brr[i-1][0], brr[i-1][1]);
			brr[i][2] += Math.min(brr[i-1][1], brr[i-1][2]);
			brr[i][1] += Math.min(brr[i-1][2],Math.min(brr[i-1][0], brr[i-1][1]));
		}
		System.out.printf("%d %d",Math.max(Math.max(arr[N-1][0], arr[N-1][1]),arr[N-1][2]),
				Math.min(Math.min(brr[N-1][0], brr[N-1][1]),brr[N-1][2]));
	}
}