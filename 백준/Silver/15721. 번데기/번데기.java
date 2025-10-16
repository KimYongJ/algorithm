//https://www.acmicpc.net/problem/15721
//1초 128MB
//4 //사람 수(1<=2000)
//6 //구하고자 하는 T(1<=10,000)
//1 // 구호가 뻔이면 0, 데기면 1로 주어진다. 
//답 : 3
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());// 사람 수(1<=2000)
		int T = Integer.parseInt(br.readLine());// 구하고자 하는 T(1<=10,000)
		int G = Integer.parseInt(br.readLine());// 구호가 뻔이면 0, 데기면 1로 주어진다. 
		int cnt[] = new int[2];
		int idx = 0;
		int round = 1;
		
		while(true)
		{
			for(int i=0; i<2; i++)
			{
				for(int j=0; j<2; j++)
				{
					if(++cnt[j] == T && G == j)
					{
						System.out.print(idx);
						return;
					}
					idx = (idx + 1) % N;
				}
			}
			
			for(int i=0; i<2; i++)
			{
				for(int j=0; j<=round; j++)
				{
					if(++cnt[i] == T && G == i)
					{
						System.out.print(idx);
						return;
					}
					idx = (idx + 1) % N;
				}
			}
			round++;
		}
	}
}