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
		boolean sound[] = new boolean[N * 5 + L * N];
		
		int len = sound.length;
		for(int i=0; i<len; i+= 5)
		{
			int j = Math.min(len, i + L);
			while(i<j)
				sound[i++] = true;
		}
		for(int i=0; i<sound.length; i++)
		{
			if(!sound[i] && i % D == 0)
			{
				System.out.print(i);
				return;
			}
		}
		
		while(len <= 100000) {
			if(len % D == 0) {
				System.out.print(len);
				return;
			}
			len++;
		}
		
	}
}