// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
    private static int read() throws Exception {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
	public static void main(String[] args)throws Exception{
		int N	= read();
		int Y[]	= new int[N];
		int X[] = new int[N];
		long sum = 0;
		for(int i=0; i<N; i++) 
		{
			Y[i] = read();
			X[i] = read();
		}
		
		Arrays.sort(Y);
		Arrays.sort(X);
		
		int baseY = Y[N/2];
		int baseX = X[N/2];
		
		for(int i=0; i<N; i++) 
		{
			sum += Math.abs(baseY - Y[i]) + Math.abs(baseX - X[i]);
		}
		System.out.print(sum);
	}
}