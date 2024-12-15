//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1184
import java.util.HashMap;
class Main{
	
	static int psum[][];
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		int cnt = 0;
		int N	= read();
		psum	= new int[N+1][N+1];
		
		for(int y=1; y<=N;  y++)
			for(int x=1; x<=N; x++)
				psum[y][x] = read()+ psum[y][x-1] + psum[y-1][x] - psum[y-1][x-1];
		
		// 가능한 꼭지점의 좌표
		for(int y=1; y<N; y++)
			for(int x=1; x<N; x++)
			{
				HashMap<Integer,Integer> map1 = new HashMap<>();	// 2사분면 4사분면 비교
				HashMap<Integer,Integer> map2 = new HashMap<>();	// 1사분면 3사분면 비교

				for(int y1=1; y1<=y; y1++)
				{
					for(int x1=1; x1<=x; x1++)						// 2사분면
					{
						int sum = getSum(y1,x1, y, x);
						map1.put(sum, map1.getOrDefault(sum, 0) + 1);
					}
					for(int x1=N; x1>x; x1--)						// 1사분면
					{
						int sum = getSum(y1, x+1, y, x1);
						map2.put(sum, map2.getOrDefault(sum, 0) + 1);
					}
				}
				
				for(int y1=N; y1>y; y1--)
				{
					for(int x1=N; x1>x; x1--)						// 4사분면
						cnt += map1.getOrDefault(getSum(y+1, x+1, y1, x1), 0);
					for(int x1=1; x1<=x; x1++)						// 3사분면
						cnt += map2.getOrDefault(getSum(y+1, x1, y1, x), 0);
				}
				
			}

		
		System.out.print(cnt);
	}
	public static int getSum(int y1, int x1, int y2, int x2) {
		return psum[y2][x2] - psum[y2][x1-1] - psum[y1-1][x2] + psum[y1-1][x1-1];
		
	}
}