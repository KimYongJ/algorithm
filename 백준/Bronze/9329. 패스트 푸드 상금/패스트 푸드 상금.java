// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node
{
	int reword;
	int[] sticker;
	Node(int reword, int[] sticker)
	{
		this.reword = reword;	this.sticker = sticker;
	}
}

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br		= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb		= new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			ArrayList<Node> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 상금에관한 정보
			int M = Integer.parseInt(st.nextToken());	// 코치가 갖고 있는 스티커의 종류
			int stype[] = new int[M+1];					// 코치가 갖고 있는 스티커 종류를 담을 배열
			for(int i=0; i<N; i++) 
			{
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken()); // 상금에 필요한 스티커 개수
				int sticker[] = new int[k];	// 상금에 필요한 스티커 종류
				for(int j=0; j<k; j++) 
				{
					sticker[j] = Integer.parseInt(st.nextToken());
				}
				list.add(new Node(Integer.parseInt(st.nextToken()), sticker));
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) 
			{
				stype[i] = Integer.parseInt(st.nextToken());	// 코치의 스티커 종류
			}
			// 상금 기준으로 내림차순 , 상금이 같다면 필요한 스티커 길이가 짧을 수록 앞으로 오게
			Collections.sort(list,(a,b)->{
				if(a.reword == b.reword) {
					return a.sticker.length - b.sticker.length;
				}
				return b.reword - a.reword;
			});
			
			int result = 0;
			for(int i=0; i<N; i++) 
			{
				boolean flag = true;
				Node node = list.get(i);
				int min = 999;
				for(int s : node.sticker) {
					if(stype[s] < 1) {
						flag = false;
						break;
					}
					if(min > stype[s]) 
					{
						min = stype[s];
					}
				}
				
				if(flag) // 상금을 차지할 수 있을 때  
				{
					for(int s : node.sticker) 
					{
						stype[s] -= min;
					}
					result += min * node.reword;
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}