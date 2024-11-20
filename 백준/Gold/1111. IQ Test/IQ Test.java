//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1111
import java.io.DataInputStream;
class Main{
	
	static int N, arr[];
	
	public static void main(String[] args)throws Exception{
		N	= readInt();	// 1<=50
		arr = new int[N];	// 절대값 100이하 다음수가 여러개면 A출력, 없으면 B출력
		
		for(int i=0; i<N; i++)
			arr[i] = readInt();

		if(N == 1 || (N == 2 && arr[0] != arr[1]))
			System.out.print('A');

		else if(N == 2)
			System.out.print(arr[0]);

		else
		{
			int A = 1;
			int B = 0;
			if(arr[0] != arr[1])
			{
				A = (arr[2] - arr[1]) / (arr[1] - arr[0]);
				B = arr[2] - arr[1] * A;
			}
			for(int i=1; i<N; i++)
				if(arr[i-1]* A + B != arr[i])
				{
					System.out.print('B');
					return;
				}
			System.out.print(arr[arr.length-1] * A + B);
		}
	}
    static DataInputStream inputStream = new DataInputStream(System.in);;
    static byte[] buffer = new byte[65536];
    static int curIdx, maxIdx;

    static int readInt() throws Exception {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = (c == '-');
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }

    static byte read() throws Exception {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, 65536);
            if (maxIdx == -1) buffer[0] = -1;
        }
        return buffer[curIdx++];
    }
}