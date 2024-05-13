// https://github.com/kimyongj/algorithm
class Main{
	static int 		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 		Y, X, nextY, nextX;
	static boolean 	map[][];
	public static void DFS(int y, int x) {
		for(int xy[]:dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(map[nextY][nextX]) 
			{
				map[nextY][nextX] = false;
				DFS(nextY,nextX);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb 	= new StringBuilder();
		Reader in 			= new Reader();
		int cnt, T 			= in.nextInt();
		
		while(T-->0)
		{
			cnt	= 0;
			Y 	= in.nextInt();
			X	= in.nextInt();
			map = new boolean[Y+2][X+2];
			
			for(int y=1; y<=Y; y++) 
				for(int x=1; x<=X; x++) 
					map[y][x] = in.nextChar() == '#';
			
			for(int y=1; y<=Y; y++) 
				for(int x=1; x<=X; x++) 
					if(map[y][x]) 
					{
						cnt++;
						map[y][x] = false;
						DFS(y,x);
					}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32);
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
    boolean isNumber(byte c) {return 47 < c && c < 58;}
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}