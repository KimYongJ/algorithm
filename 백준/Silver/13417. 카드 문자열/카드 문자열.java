// https://github.com/kimyongj/algorithm
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		StringBuilder str;
		IN in = new IN();
		int T = in.nextInt();
		while(T-->0) 
		{
			char first	= 'a';
			int N		= in.nextInt();
			str			= new StringBuilder();
			for(int i=0; i<N; i++) 
			{
				char c = in.nextChar();
				if(first >= c) 
				{
					first = c;
					str.insert(0,c);
				}
				else str.append(c);
			}
			sb.append(str.toString()).append('\n');
		}
		System.out.print(sb.toString());
	}
}
class IN {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
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
