// https://github.com/KimYongJ/algorithm

class Main{
	
	public static void main(String[] args)throws Exception{
		Reader r = new Reader();
		StringBuilder   sb 	= new StringBuilder();
		int x1, y1, x2, y2, total;
		int N 				= r.read();
		int M 				= r.read();
		int sum[][]			= new int[N+1][N+1];
		
		// 배열 insert
		for(int i=1; i<=N; i++) 
			// arr배열에 원 값을 넣음 과 동시에 구간합을 구합니다. 
			for(int j=1; j<=N; j++) 
				sum[i][j] = r.read() + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];// 해당 좌표의 구간합 구하는 공식
			
		// 좌표를 입력 받음과 동시에 구간합을 계산해 저장합니다.
		for(int i=0; i<M; i++) {
			x1 = r.read();
			y1 = r.read();
			x2 = r.read();
			y2 = r.read();
			// 왼쪽 사각형 : x2 , y1-1
			// 위쪽 사각형 : x1-1 , y2
			// 겹치는 사각형 : x1-1, y1-1
			total = sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
			sb.append( total ).append('\n');
		}
		System.out.println(sb);
	}

}
// 빠른 입력을 위해 만듦
class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int read() throws Exception {
        int n = 0;
        byte c;
        while ((c = get()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = get()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte get() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}