//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1007
class Main{
	
	static int N, pair[][];
	static double min;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			N			= read();
			min			= Double.MAX_VALUE;
			pair		= new int[N][2];
			int sumX	= 0;
			int sumY	= 0;
			int x,y;
			for(int i=0; i<N; i++)
			{
				sumX += x = read();
				sumY += y = read();
				pair[i] = new int[] {x<<1,y<<1};
			}
			
			selectMinus(N>>1, sumX, sumY, 0);
			
			sb.append(String.format("%.11f", min)).append('\n');
		}
		System.out.print(sb.toString());
	}
	static void selectMinus(int depth, int sumX, int sumY, int idx) {
		if(depth == 0)
		{
			double result = Math.sqrt((long)sumX*sumX + (long)sumY*sumY);
			if(result < min)
				min = result;
			return;
		}
		
		for(int i=idx; i< N; i++)
			selectMinus(depth - 1, sumX - pair[i][0], sumY - pair[i][1], i + 1);
		
	}
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}