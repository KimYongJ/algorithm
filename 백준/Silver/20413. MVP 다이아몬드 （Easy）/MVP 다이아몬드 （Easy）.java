// https://github.com/kimyongj/algorithm
class Main{
	public static void main(String[] args)throws Exception{
		READ in		= new READ();
		int N		= in.nextInt();
		int money[] = new int[5];
		int before	= 0;
		int result	= 0;
		int idx; 
		
		for(int i=0; i<4; i++)
			money[i] = in.nextInt() - 1;
		
		money[4] = money[3]+1;
		
		for(int i=0; i<N; i++) 
		{
			switch(in.nextChar()) 
			{
				case 'B':	idx=0;break;
				case 'S':	idx=1;break;
				case 'G':	idx=2;break;
				case 'P':	idx=3;break;
				default:	idx=4;break;
			}
			if(idx == 4) 
			{
				result += money[idx] * (N-i);
				break;
			}
			else result += before = money[idx] - before;
		}
		System.out.print(result);
	}
}

class READ {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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