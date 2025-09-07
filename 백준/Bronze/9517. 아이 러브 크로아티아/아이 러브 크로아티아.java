//https://www.acmicpc.net/problem/9517
//1초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int time = 210;
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			time -= Integer.parseInt(st.nextToken());
			
			if(time < 0)
				break;
			
			if(st.nextToken().charAt(0) == 'T' && ++K == 9)
				K = 1;
		}
		
		System.out.print(K);
	}
}