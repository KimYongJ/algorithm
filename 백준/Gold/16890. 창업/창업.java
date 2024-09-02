// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/16890
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder front = new StringBuilder();
		StringBuilder back	= new StringBuilder();
		char[] forward	= br.readLine().toCharArray();
		char[] reverse	= br.readLine().toCharArray();
		
		Arrays.sort(forward);
		Arrays.sort(reverse);
		
		int len = forward.length;
		int fStartIdx = 0, rStartIdx = len-1;
		int fEndIdx = len/2 + len%2 -1, rEndIdx = fEndIdx+1;
		for(int i=0; i<len; i++)
		{
			if(i%2==0)// 정방향으로 놓길 원하는 사람 
			{
				if(forward[fStartIdx] >= reverse[rStartIdx]) 
					back.append(forward[fEndIdx--]);
				else 
					front.append(forward[fStartIdx++]);
			}
			else // 역방향으로 놓길 원하는 사람
			{
				if(forward[fStartIdx] < reverse[rStartIdx]) 
					front.append(reverse[rStartIdx--]);
				else 
					back.append(reverse[rEndIdx++]);
			}
		}
		
		front.append(back.reverse().toString());
		
		System.out.print(front.toString());	
	}
}
/*
bb // 1<=30만 문자열(소문자)
aa
답ab
 * */
