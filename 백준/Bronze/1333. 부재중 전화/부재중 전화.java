//https://www.acmicpc.net/problem/1333
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 노래 수
		int L = Integer.parseInt(st.nextToken());// 노래하나의 길이
		int D = Integer.parseInt(st.nextToken());// 벨이 울리는 초

		int len = N * 5 + L * N;
		for(int i=0; i<len; i+= 5)
		{
			i = Math.min(len, i + L);
			
			for(int j = i, z = i + 5; j<z; j++)
			{
				if(j % D == 0)
				{
					System.out.print(j);
					return;
				}
			}
		}
		
		while(len <= 100000)
		{
			if(len % D == 0)
			{
				System.out.print(len);
				return;
			}
			len++;
		}
		
	}
}