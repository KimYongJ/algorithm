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
		
		// 1,1 에서 아래(0)->오른쪽(1)->위(2)->왼쪽(3)->아래(0) // 출력 : x,y순
		int downLimit	= Y;
		int rightLimit	= X;
		int upLimit		= 1;
		int leftLimit	= 2;
		int dir	= 0;
		int y	= 1;
		int x	= 1;
		while(1 != K)
		{
			int diff = 0;
			if(dir == 0)
			{
				diff = downLimit - y;
				if(K<=diff)
				{
					y += K - 1;
                    break;
				}
				else
					y = downLimit--;
			}
			else if(dir == 1)
			{
				diff = rightLimit - x;
				if(K<=diff)
				{
					x += K - 1;
					break;
				}
				else
					x = rightLimit--;
			}
			else if(dir == 2) {
				diff = y - upLimit;
				if(K<=diff)
				{
					y -= K -1;
					break;
				}
				else
					y = upLimit++;
			}
			else
			{
				diff = x - leftLimit;
				if(K<=diff)
				{
					x -= K - 1;
					break;
				}
				else
					x = leftLimit++;
			}
			K -= diff;
			dir = (dir + 1) % 4;
		}
		
		
		
		System.out.printf("%d %d",x,y);
	}
}