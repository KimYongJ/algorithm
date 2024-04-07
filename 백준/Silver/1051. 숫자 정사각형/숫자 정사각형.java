//https://github.com/KimYongJ/algorithm

class Main{
	
	static int Y, X, max, arr[][];
    private static int read() throws Exception{
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32)
	    	n = (n << 3) + (n << 1) + (c & 15);
	    return n;
	}
	public static void main(String[] args)throws Exception{
		Y 	= read();
		X 	= read();
		max = Math.min(Y, X);
		arr = new int[Y][X];
		for(int y=0; y<Y; y++) {
			for(int x=0; x<X; x++)
				arr[y][x] = (int)System.in.read();
			System.in.read();
		}
		int a,b,c;
		Loop:
		while(max-- > 0)
			for(int y=0; y<Y-max; y++)
				for(int x=0; x<X-max; x++) {
					a = arr[y+max][x];
					b = arr[y][x+max];
					c = arr[y+max][x+max];
					if( arr[y][x] == a && a== b && b== c)
						break Loop;
				}
	
		max++;
		System.out.println(max * max);
	}
}