//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14746
//1.5초 / 512MB
//요약 : P,Q배열에 x좌표를 주고, y좌표는 c1,c2로 고정해놓고 P,Q간 가장 가까운 좌표거리와, 그 거리를 갖는 모든 쌍의 개수를 구하는 것
//비슷한 문제 : 두 배열에서의 차이가 최솟값인 것을 빠르게 찾는 문제(17095, 27931)
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N	= read();	// P집합 점 개수(1 ≤ 500,000)
		int M	= read();	// Q집합 점 개수(1 ≤ 500,000)
		int P[] = new int[N];
		int Q[] = new int[M];
		int c1	= read();	// |억|, P의 모든 점은 y = c1인 점에 있음 
		int c2	= read();	// |억|, Q의 모든 점은 y = c2인 점에 있음

		for(int i=0; i<N; i++) P[i] = read(); 
		for(int i=0; i<M; i++) Q[i] = read();
		
		Arrays.sort(P);
		Arrays.sort(Q);
		
		int idx1	= 0;
		int idx2	= 0;
		int minDist = 1<<30;
		int pairCnt = 0;
		
		while(idx1<N && idx2<M)
		{
			int diff = Math.abs(P[idx1] - Q[idx2]);
			
			if(diff == minDist)
			{
				++pairCnt;
			}
			else if(diff < minDist)
			{
				minDist = diff;
				pairCnt	= 1;
			}
			
			if(P[idx1] < Q[idx2])
				++idx1;
			else
				++idx2;
		}
		
		System.out.printf("%d %d",minDist + Math.abs(c1-c2),pairCnt);
	}
    static int read() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {c = System.in.read();}
        boolean minus = false;
        if (c == '-') {minus = true;
            c = System.in.read();}
        do {val = 10 * val + c - 48;}
        while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
}