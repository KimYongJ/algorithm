// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		I in  = new I();
		int N = in.nextInt(); // 참가인원
		int M = in.nextInt(); // 장르 개수
		int K = in.nextInt(); // 본선에 나갈 인원
		int n, idx = N;
		double a, ans = 0.0;
		double arr[] = new double[N+1];
		
		while(M-->0)
		{
			for(int i=0; i<N; i++) 
			{
				n = in.nextInt();		// 참가번호
				a = in.nextDouble();	// 능력치
				if(arr[n] < a) 
				{
					arr[n] = a;
				}
			}
		}
		
		Arrays.sort(arr);
		
		while(K-->0)
		{
			ans += arr[idx--];
		}
		
		System.out.print(String.format("%.1f", ans));
	}
}

class I {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
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
    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
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
