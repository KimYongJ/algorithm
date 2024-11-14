//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10157

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		if(Y*X < K)
		{
			System.out.print(0);
			return;
		}
		
		// 1,1 에서 아래(0)->오른쪽(1)->위(2)->왼쪽(3)->아래(0) // 출력 : x부터 
		int downLimit	= Y;
		int rightLimit	= X;
		int upLimit		= 1;
		int leftLimit	= 2;
		int dir	= 0;
		int y	= 1;
		int x	= 1;
		int n	= 1;
		while(n != K)
		{
			if(dir == 0) {
				while(y < downLimit && n != K) {
					++y;
					++n;
				}
				--downLimit;
			}
			else if(dir == 1) {
				while(x < rightLimit && n != K) {
					x++;
					++n;
				}
				--rightLimit;
			}
			else if(dir == 2) {
				while(upLimit<y && n != K) {
					--y;
					++n;
				}
				++upLimit;
			}
			else if(dir == 3) {
				while(leftLimit<x && n != K) {
					--x;
					++n;
				}
				++leftLimit;
			}
			dir = (dir + 1) % 4;
		}
		
		
		
		System.out.printf("%d %d",x,y);
	}
}