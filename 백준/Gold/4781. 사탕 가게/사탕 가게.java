//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4781
//3초 / 512MB
//무한 배낭 문제
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb= new StringBuilder();
		Reader in		= new Reader();
		while(true)
		{
			int N	= in.nextInt();//사탕의 종류 수(1<=오천)
			int M	= (int)Math.round((in.nextDouble() * 100));//돈의 양(0.01 <= 100.00)
			int C	= 0;				//칼로리(1<=오천)
			int P	= 0;				//가격(0.01<=100.00)
			int dp[]= new int[M + 1];	// 최대 칼로리를 담음
			
			if(N == 0 && M == 0)
				break;
			
			for(int i=1; i<=N; i++)
			{
				C = in.nextInt();//칼로리(1<=오천)
				P = (int)Math.round(in.nextDouble() * 100);//가격(0.01<=100.00)
				
				for(int j=P; j<=M; j++)
					dp[j] = Math.max(dp[j], dp[j-P] + C);
			}
			
			sb.append(dp[M]).append('\n');
		}
		System.out.print(sb);
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}

