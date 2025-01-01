//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17128
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	// 소의개수	4<=이십만
		int Q		= read();	// 값 변경회수	1<=이십만
		int arr[]	= new int[N+3];
		int sum[]	= new int[N];
		int total	= 0;

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		arr[N]	= arr[0];
		arr[N+1]= arr[1];
		arr[N+2]= arr[2];
		
		for(int idx=0, i=3; i<N+3; i++, idx++)
		{
			sum[idx] = 1;
			for(int s=i-3; s<=i; s++)
				sum[idx] *= arr[s];

			total += sum[idx];
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			int start = read();
			for(int i=0; i<4; i++)
			{
				int idx = --start;
				if(idx < 0)
					idx = N + idx;
				
				sum[idx] = -sum[idx];
				total += sum[idx] + sum[idx];
			}
			sb.append(total).append('\n');
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