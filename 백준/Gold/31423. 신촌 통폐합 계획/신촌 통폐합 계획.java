//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31423
class Main{
	public static void  main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in		= new Reader();
		int N			= in.nextInt();	// 대학교 개수(2<=오십만)
		String[] str	= new String[N+1];
		int next[]		= new int[N+1];
		int tail[]		= new int[N+1];
		
		for(int i=1; i<=N; i++)
		{
			str[i]	= in.nextString();
			tail[i] = i;
		}
		int a=0,b;
		for(int i=1; i<N; i++)
		{
			a = in.nextInt();
			b = in.nextInt();
			next[tail[a]] = b;
			tail[a] = tail[b];
		}
		
		while(a != 0)
		{
			sb.append(str[a]);
			a = next[a];
		}

		System.out.print(sb.toString());
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }
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

    boolean isNumber(byte c) {return 47 < c && c < 58;}
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}
