//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/19637
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in	= new Reader();
		int N		= in.nextInt();
		int M		= in.nextInt();
		int level[] = new int[N];
		String name[] = new String[N];
		
		for(int i=0; i<N; i++)
		{
			name[i]	= in.nextString();
			level[i]= in.nextInt();
		}
		while(M-->0)
		{
			int number = in.nextInt();
			int s	= 0;
			int e	= N-1;
			int idx	= 0;
			while(s <= e)
			{
				int mid = (s + e) / 2;
				if(number <= level[mid])
				{
					idx = mid;
					e = mid -1;
				}
				else
				{
					s = mid + 1;
				}
			}
			
			sb.append(name[idx]).append('\n');
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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read();}
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return n;
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
