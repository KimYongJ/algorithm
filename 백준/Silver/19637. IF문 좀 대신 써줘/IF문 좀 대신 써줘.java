//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/19637
class Main{
	static int readInt() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	static String readString() throws Exception{
		StringBuilder sb = new StringBuilder();
		int c = System.in.read();
		while(c <= 32) {c = System.in.read();}
		while(c > 32) {
			sb.append((char)c);
			c = System.in.read();
		}
		return sb.toString();
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= readInt();
		int M		= readInt();
		int level[] = new int[N];
		String name[] = new String[N];
		
		for(int i=0; i<N; i++)
		{
			name[i]	= readString();
			level[i]= readInt();
		}
        
		while(M-->0)
		{
			int number = readInt();
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
                    s = mid + 1;
			}
			sb.append(name[idx]).append('\n');
		}
		System.out.print(sb.toString());
	}
}
