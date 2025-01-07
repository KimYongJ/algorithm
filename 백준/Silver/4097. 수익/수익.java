//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4097
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = read();
			if(N == 0)
				break;
			
			int curSum = ~(1<<30);
			int maxSum = ~(1<<30);
			while(N-->0)
			{
				int num = read();
				curSum = Math.max(curSum + num, num);
				maxSum = Math.max(maxSum, curSum);
			}
			sb.append(maxSum).append('\n');
		}
		System.out.print(sb);
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