//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1912
class Main{
	public static void main(String[] args)throws Exception{
		int N		= readInt();
		int curSum	= 0;
		int maxSum	= ~(1<<30);
		while(N-->0)
		{
			int n	= readInt();
			curSum	= Math.max(curSum + n, n);
			maxSum	= Math.max(curSum, maxSum);
		}
		System.out.print(maxSum);
	}
    private static int readInt() throws Exception {
        int t = 0;
        byte v;
        while ((v = read()) <= 32);
        boolean negative = (v == '-');
        if (negative) v = read();
        do {
            t = t * 10 + (v - '0');
        } while ((v = read()) >= '0' && v <= '9');
        return (negative) ? -t : t;
    }

    static final int SIZE = 1 << 13;
    static byte[] buffer = new byte[SIZE];
    static int index, size;
    private static byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}