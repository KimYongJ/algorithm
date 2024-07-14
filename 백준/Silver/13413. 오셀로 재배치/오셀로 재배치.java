// https://github.com/kimyongj/algorithm
class Main{
	public static void main(String[] args)throws Exception{
		IN in				= new IN();
		StringBuilder sb	= new StringBuilder();
		int T 				= in.nextInt();
		while(T-->0) 
		{
			int len = in.nextInt();
			char s1[] = new char[len];
			int w = 0, b = 0;				// w와 b의 개수
			
			for(int i=0; i<len; i++) 
			{
				s1[i] = in.nextChar();
			}
			for(int i=0; i<len; i++)
			{
				char c = in.nextChar();
				if(s1[i] != c) 
				{
					if(c == 'W') 
					{
						w++;
					}else 
					{
						b++;
					}
				}
			}
			sb.append(Math.max(w, b)).append('\n');			
		}
		System.out.print(sb.toString());
	}
}

class IN {
	final int SIZE = 1 << 15;
	byte[] buffer = new byte[SIZE];
	int index, size;

	int nextInt() throws Exception{
		int n = 0;
		byte c;
		while ((c = read()) <= 32)
			;
		boolean neg = c == '-';
		if (neg)
			c = read();
		do
			n = (n << 3) + (n << 1) + (c & 15); while (isNumber(c = read()));
		return neg ? -n : n;
	}

	boolean isNumber(byte c) {
		return 47 < c && c < 58;
	}

	char nextChar() throws Exception{
		byte c;
		while ((c = read()) <= 32)
			;
		return (char)c;
	}

	byte read() throws Exception{
		if (index == size) {
			size = System.in.read(buffer, index = 0, SIZE);
			if (size < 0)
				buffer[0] = -1;
		}
		return buffer[index++];
	}
}

