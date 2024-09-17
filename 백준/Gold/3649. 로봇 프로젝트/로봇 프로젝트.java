//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3649
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			
			int X		= in.nextInt() * 10_000_000;	// 구멍의 넓이 cm(1<=20) => 나노미터로전환(1센티미터 = 10,000,000 나노미터)
			if(X <= 0)
				break;
			
			int N		= in.nextInt();					// 레고조각수 (0<=백만)
			int arr[]	= new int[N];					// 레고조각의 길이 L(나노미터 단위 1<=100,000,000)
			
			for(int i=0; i<N; i++)
				arr[i] = in.nextInt();
			
			Arrays.sort(arr);
			
			int s			= 0;
			int e			= N-1;
			int res1		= 0;
			int res2		= 0;
			boolean flag	= false;
			while(s < e)
			{
				int sum = arr[s] + arr[e];
				if(sum == X)
				{
					res1 = arr[s];
					res2 = arr[e];
					flag = true;
					break;
				}
				if(sum < X)
					s++;
				else
					e--;
			}
			
			if(flag)
				sb.append("yes ").append(res1).append(' ').append(res2).append('\n');
			else
				sb.append("danger\n");
		}
		System.out.print(sb.toString());
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    int nextInt() throws Exception {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) 
        { 
        	if (size < 0)
        		return -1; 
        }
        if (c == 45) { c = read(); }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return n;
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