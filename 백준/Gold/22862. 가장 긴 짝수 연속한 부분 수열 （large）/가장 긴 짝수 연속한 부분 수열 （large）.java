//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22862
// 1초 / 1024MB
// 수열에서 임의로 K개 수를 삭제한 후 짝수로 이뤄진 연속한 부분 수열 중 가장 긴 길이 출력
// 비슷한 백준 문제 : 22857
class Main{
	public static void main(String[] args)throws Exception{
		Reader in	= new Reader();
		int N		= in.nextInt();// 수열길이(1<=백만)
		int K		= in.nextInt();// 최대 삭제 횟수(1<=십만)
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = in.nextInt() % 2;
		
		int s	= 0;
		int e	= 0;
		int k	= 0;
		int max = 0;
		
		while(e < N)
		{
			if(arr[e++] == 1)
				++k;
			
			max = Math.max(max, e - s - k);
			
			while(K<k)
				if(arr[s++] == 1)
					--k;
		}
		System.out.print(max);
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
