// https://github.com/kimyongj/algorithm

import java.util.ArrayList;
import java.util.Collections;
class Node
{
	int reword;
	int[] sticker;
	Node(int reword, int[] sticker){this.reword = reword;	this.sticker = sticker;}
}
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) 
		{
			ArrayList<Node> list = new ArrayList<>();
			int N		= read();						// 상금에관한 정보
			int M		= read();						// 코치가 갖고 있는 스티커의 종류
			int stype[]	= new int[M+1];					// 코치가 갖고 있는 스티커 종류를 담을 배열
			for(int i=0; i<N; i++) 
			{
				int k			= read(); 				// 상금에 필요한 스티커 개수
				int sticker[]	= new int[k];			// 상금에 필요한 스티커 종류
				for(int j=0; j<k; j++) 
				{
					sticker[j] = read();
				}
				list.add(new Node(read(), sticker));
			}
			
			for(int i=1; i<=M; i++) 
			{
				stype[i] = read();						// 코치의 스티커 종류
			}
			// 상금 기준으로 내림차순 , 상금이 같다면 필요한 스티커 길이가 짧을 수록 앞으로 오게
			Collections.sort(list,(a,b)->{
				if(a.reword == b.reword) 
				{
					return a.sticker.length - b.sticker.length;
				}
				return b.reword - a.reword;
			});
			
			int result = 0;
			
			for(int i=0; i<N; i++) 
			{
				Node node	= list.get(i);
				int min		= 999;
				for(int s : node.sticker) 
				{
					if(min > stype[s]) 
					{
						min = stype[s];
					}
				}
				
				if(min > 0) // 상금을 차지할 수 있을 때  
				{
					for(int s : node.sticker) 
					{
						stype[s] -= min;
					}
					result += min * node.reword; // 해당 상금을 몇번 차지할 수 있는지 바로 곱해서 답을 구함
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}