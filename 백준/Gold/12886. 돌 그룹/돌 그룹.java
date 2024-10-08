//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12886
//채점 데이터가 너무 약한가.. 아래 코드 포함 다른 코드들이 통과하는데.. 이유가..

import java.util.ArrayDeque;

class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int A				= read();
		int B				= read();
		int C				= read();
		int tmp[]			= new int[3];
		boolean visit[][]	= new boolean[1500][1500];
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visit[B][A] = visit[C][B] = visit[C][A] =
		visit[A][B] = visit[B][C] = visit[A][C] = true;;

		q.add(new int[] {A,B,C});
		
		while(!q.isEmpty())
		{
			int[] now = q.poll();
			if(now[0] == now[1] && now[1] == now[2])
			{
				System.out.print(1);
				return;
			}
			
			for(int i=0; i<3; i++)
			{
				int j = (i + 1) % 3;
				if(now[i] != now[j])
				{
					int anoIdx = 3 - (i+j);
					int valueI, valueJ;
					if(now[i] < now[j])
					{
						valueI = now[i] << 1;
						valueJ = now[j] - now[i];						
					}
					else
					{
						valueI = now[i] - now[j];
						valueJ = now[j] << 1;
					}
					
					
					if(!visit[valueI][valueJ] && !visit[valueJ][valueI])
					{
						visit[valueI][valueJ] = visit[valueJ][valueI] = true;
						tmp[i]		= valueI;
						tmp[j]		= valueJ;
						tmp[anoIdx] = now[anoIdx];
						
						q.add(new int[] {tmp[0], tmp[1], tmp[2]});
					}
				}
			}
		}
		System.out.print(0);
	}
}
