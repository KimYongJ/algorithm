//https://www.acmicpc.net/problem/23968
//1초 512

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=N-1; i>0; i--) {
			for(int j=0; j<i; j++) {
				if(arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					if(--K == 0) {
						System.out.print(arr[j] + " " + arr[j + 1]);
						return;
					}
				}
			}
		}
		System.out.print(-1);
	}
}