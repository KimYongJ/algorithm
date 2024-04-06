// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int N, cnt, arr[][], base[][];
	static boolean visit[];
	public static boolean check(int num) {
		int num1 = num%10;
		int num2 = num/10%10;
		int num3 = num/100;
		int strike, ball;
		for(int i=0; i<N; i++) {
			strike = ball = 0;
			if(num1 == base[i][0]) strike++;
			if(num2 == base[i][1]) strike++;
			if(num3 == base[i][2]) strike++;
			if(num1 == base[i][1] || num1 == base[i][2]) ball++;
			if(num2 == base[i][0] || num2 == base[i][2]) ball++;
			if(num3 == base[i][0] || num3 == base[i][1]) ball++;
			
			if(arr[i][0] != strike || arr[i][1] != ball)
				return false;
		}
		return true;
	}
	public static void DFS(int depth,int num) {
		if(depth == 3) {
			if(check(num))
				cnt++;
			return;
		}
		for(int i=1; i<10; i++) {
			if(!visit[i]) {
				visit[i] = true;
				DFS(depth + 1 ,num + (int)(Math.pow(10, depth) * i));
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N		= Integer.parseInt(br.readLine());
		arr 	= new int[N][2];
		visit 	= new boolean[10];
		base	= new int[N][3];
		for(int i=0; i<N; i++) 
		{
			st 			= new StringTokenizer(br.readLine());
			int main 	= Integer.parseInt(st.nextToken());
			base[i][0] 	= main%10;
			base[i][1] 	= main/10%10;
			base[i][2] 	= main/100;
			arr[i][0] 	= Integer.parseInt(st.nextToken());
			arr[i][1]	= Integer.parseInt(st.nextToken());
		}
		
		DFS(0, 0);
		
		System.out.println(cnt);
	}
}