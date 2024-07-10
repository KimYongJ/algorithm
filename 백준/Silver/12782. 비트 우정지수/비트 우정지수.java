// https://github.com/kimyongj/algorithm
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		IN in = new IN();
		int T = in.nextInt();
		
		while(T-->0) 
		{
			String a	= in.nextString();
			String b	= in.nextString();
			int len		= a.length();
			int _0		= 0;
			int _1		= 0;
			for(int i=0; i<len; i++) 
			{
				char c = a.charAt(i);
				if(c != b.charAt(i)) 
				{
					if(c == '0') 
					{
						_0 ++;
					}
					else 
					{
						_1 ++;
					}
				}
			}
			sb.append(Math.max(_0, _1))
				.append('\n');
		}
		System.out.print(sb.toString());
	}
}
class IN {
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