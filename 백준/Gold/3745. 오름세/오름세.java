//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3745
class Main{
	public static int getIdx(int LIS[], int target, int len) {
		int idx = 0;
		int s	= 0;
		int e	= len - 1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(LIS[mid] == target)
				return mid;
			if(LIS[mid] < target)
				s = mid + 1;
			else
			{
				idx = mid;
				e	= mid - 1;
			}
		}
		return idx;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		while(true)
		{
			int N = in.nextInt();	// 1<=100000
			if(N <= 0)
				break;
			
			int len		= 1;
			int arr[]	= new int[N];
			int LIS[]	= new int[N];
			
			for(int i=0; i<N; i++)
				arr[i] = in.nextInt();
			
			LIS[0] = arr[0];
			
			for(int i=1; i<N; i++) {
				if(LIS[len - 1] < arr[i])
					LIS[len++] = arr[i];
				else
					LIS[getIdx(LIS, arr[i], len)] = arr[i];
			}
			
			sb.append(len).append('\n');
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

