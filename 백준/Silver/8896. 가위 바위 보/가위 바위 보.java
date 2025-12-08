//https://www.acmicpc.net/problem/8896
//1초 128MB
//3 // 테스트 케이스 수
//5 // 참여 로봇 수(2<=10)
//RPSSSPR // 문자열 길이 (3<=30), 로봇 번호는 1부터 시작
//SSRPRPS
//PRSSRSP
//RRRPSPP
//SSSSSRP
//4
//RPSPSPSPRPRPSR
//SPSSRRRSSRPRRR
//RSPRPPPPSSRPSR
//PRRSSSRRPRSRRR
//3
//SPPPSS
//SPRRRR
//SSSSPP
//답
//2
//0 // 무승부
//3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			ArrayList<Node> list = new ArrayList<>();// 문자열 길이 (3<=30), 로봇 번호는 1부터 시작
			
			int N = Integer.parseInt(br.readLine());// 참여 로봇 수(2<=10)

			for(int i=1; i<=N; i++)
				list.add(new Node(i,br.readLine().toCharArray()));
			
			int len = list.get(0).str.length;
			
			for(int round=0; round<len; round++)
			{
				int bitMask = 0;
				
				for(Node s : list)
				{
					if(s.str[round] == 'R') bitMask |= 1;
					else if(s.str[round] == 'S') bitMask |= 2;
					else bitMask |= 4;
				}

				if(Integer.bitCount(bitMask) == 2)
				{
					char win = getWin(bitMask);
					
					for(int i=0; i<list.size(); i++)
						if(list.get(i).str[round] != win)
							list.remove(i--);
				}
			}
			
			sb.append(list.size() == 1 ? list.get(0).idx : 0).append('\n');
		}
		
		System.out.print(sb);
	}
	static char getWin(int bitMask) {
		if((bitMask & 1) == 1 && (bitMask & 2) == 2) return 'R';
		if((bitMask & 1) == 1 && (bitMask & 4) == 4) return 'P';
		return 'S';
	}
	static class Node{
		int idx;
		char[] str;
		Node(int idx, char[] str){
			this.idx = idx;
			this.str = str;
		}
	}
}