//https://github.com/KimYongJ/algorithm

class Main{
    private static int read() throws Exception {
        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32) N = 10 * N + c - 48;
        return N;
    }
	public static void main(String[] args)throws Exception{
        final int INF = 16;
		int N 		= read();         // 지역 개수
		int M 		= read();         // 예으니 수색범위
		int R 		= read();         // 길의 개수
		int item[] 	= new int[N];     // 지역당 아이템 개수
		int map[][]	= new int[N][N];  // 맵
		for(int i=0; i<N; i++)
			item[i] = read();
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++) 
			{
				if(i==j) continue;
				map[i][j] = INF;
			}
		
		int a,b,c;
		for(int i=0; i<R; i++) 
		{
			a = read()-1;
			b = read()-1;
			c = read();
			map[a][b] = c;
			map[b][a] = c;
		}
		for(int k=0; k<N; k++)
			for(int i=0; i<N; i++) 
			{
				if(k==i) continue;
				for(int j=0; j<N; j++) 
				{
					if(i==k || j==k) continue;
					if(map[i][j] > map[i][k]+map[k][j])
						map[i][j] = map[i][k]+map[k][j];
				}
			}
		
		int result = 0, sum;
		for(int i=0; i<N; i++) 
		{
			sum = 0;
			for(int j=0; j<N; j++)
				if(map[i][j] <= M)
					sum += item[j];
			result = Math.max(result, sum);
		}
		System.out.println(result);
	}
}