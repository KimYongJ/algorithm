//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27210
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int max		= 0;
		int sum1	= 0;
		int sum2	= 0;

		for(int i=1; i<=N; i++)
		{
			if(read() == 1)
			{
				sum1++;
				sum2--;
			}
			else
			{
				sum1--;
				sum2++;
			}
			
			max = Math.max(max, sum1);
			max = Math.max(max, sum2);
			
			if(sum1 < 0)
				sum1 = 0;
			if(sum2 < 0)
				sum2 = 0;
		}
		System.out.print(max);
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