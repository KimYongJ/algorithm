//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11048
//1초 / 256MB
class Main{
	public static void main(String[] args)throws Exception{
		Reader in	= new Reader();
		int Y		= in.nextInt();// 1<=천
		int X		= in.nextInt();// 1<=천
		int map[][] = new int[Y+1][X+1];
		
		for(int y=1; y<=Y; y++)
			for(int x=1; x<=X; x++)
				map[y][x] = in.nextInt()
						+ Math.max(map[y-1][x], Math.max(map[y][x-1], map[y-1][x-1])); 

		System.out.print(map[Y][X]);
	}
}
class Reader {
    private final int SIZE = 1 << 13;
    private byte[] buffer = new byte[SIZE];
    private int index, size;

    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isnumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    private boolean isnumber(byte c) {
        return 47 < c && c < 58;
    }

    private byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}
