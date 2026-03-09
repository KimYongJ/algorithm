//https://www.acmicpc.net/problem/3258
//1초 128MB
//7 6 2 // 원판 필드(2<=1,000), 목표필드(2<=Z), 장애물 필드 수(0<=N-2)
//2 4 // 장애물 필드 번호
//답 : 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//원판 필드(2<=1,000)
		int G = Integer.parseInt(st.nextToken()) - 1;//목표필드(2<=Z)
		int T = Integer.parseInt(st.nextToken());//장애물 필드 수(0<=N-2)
		int visit[] = new int[N];
		boolean block[] = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<T; i++)
			block[Integer.parseInt(st.nextToken())-1] = true;
		
		if(T == 0)
		{
			System.out.print(1);
			return;
		}
				
		for(int k=1; k<=G; k++)
		{
			visit[0] = k;
			int idx = k % N;

			while(visit[idx] != k && !block[idx])
			{
				if(idx == G)
				{
					System.out.print(k);
					return;
				}
				visit[idx] = k;
				idx = (idx + k) % N;
			}
			
		}
	}
}