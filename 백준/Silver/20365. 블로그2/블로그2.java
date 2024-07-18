// https://github.com/kimyongj/algorithm
class Main{
	public static void main(String[] args)throws Exception{
		Read read	= new Read();
		int len		= read.nextInt();
		String str	= read.nextString();
		char c, now	= '1';
		int Rcnt	= 0;
		int Bcnt	= 0;
		for(int i=0; i<len; i++) 
		{
			c = str.charAt(i);
			if(c != now) 
			{
				if(c == 'B') {Bcnt++;}
				else {Rcnt++;}
				now = c;
			}
		}
		System.out.print(Math.min(Bcnt, Rcnt) + 1);
	}
}

class Read {
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