//https://www.acmicpc.net/problem/14719
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int o[] = new int[W];
		int a[] = new int[W];
		int b[] = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++)
			o[i] = a[i] = b[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<W; i++)a[i] = Math.max(a[i], a[i-1]);
		for(int i=W-2; i>=0; i--)b[i] = Math.max(b[i], b[i+1]);
		
		int sum = 0;
		
		for(int i=0; i<W; i++)
		{
			a[i] = Math.min(a[i], b[i]);
			sum += a[i] - o[i];
		}
		
		System.out.print(sum);
	}
}