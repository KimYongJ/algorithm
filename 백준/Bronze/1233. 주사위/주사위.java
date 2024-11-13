//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1233

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a		= Integer.parseInt(st.nextToken());
		int b		= Integer.parseInt(st.nextToken());
		int c		= Integer.parseInt(st.nextToken());
		int max		= 0;
		int cnt[]	= new int[81];
		
		for(int i=1; i<=a; i++)
			for(int j=1; j<=b; j++)
				for(int z=1; z<=c; z++)
					max = Math.max(max,++cnt[i+j+z]);
		
		for(int i=0; i<81; i++)
			if(cnt[i] == max)
			{
				System.out.print(i);
				return;
			}
		
	}
}