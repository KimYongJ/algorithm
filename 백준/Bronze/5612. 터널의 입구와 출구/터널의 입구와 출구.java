//https://www.acmicpc.net/problem/5612
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int max = M;
		
		for(int i=0; i<N && 0<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			M += Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken());
			if(max < M)
				max = M;
		}
		System.out.print(M < 0 ? 0 : max);
	}
}