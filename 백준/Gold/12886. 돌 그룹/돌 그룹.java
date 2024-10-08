//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12886
//채점 데이터가 너무 약해서.. 아래 코드 포함 다른 코드들이 통과하는듯..
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		int A				= Integer.parseInt(st.nextToken());
		int B				= Integer.parseInt(st.nextToken());
		int C				= Integer.parseInt(st.nextToken());
		int tmp[]			= new int[3];
		boolean visit[][]	= new boolean[1500][1500];
		ArrayDeque<int[]> q = new ArrayDeque<>();

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
				for(int j=0; j<3; j++)
					if(i!=j && now[i] < now[j])
					{
						int anoIdx = 3 - (i+j);
						int valueI = now[i] << 1;
						int valueJ = now[j] - now[i];
						
						if(!visit[valueI][valueJ])
						{
							visit[valueI][valueJ] = true;
							tmp[i]		= valueI;
							tmp[j]		= valueJ;
							tmp[anoIdx] = now[anoIdx];
							
							q.add(new int[] {tmp[0], tmp[1], tmp[2]});
						}
					}
		}
		System.out.print(0);
	}
}