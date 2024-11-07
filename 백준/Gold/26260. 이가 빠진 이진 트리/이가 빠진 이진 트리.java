//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26260
import java.util.Arrays;

class Main{
	
	static int N, idx, map[];
	static StringBuilder sb = new StringBuilder();
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	
	public static void postOrderDFS(int start, int end) {
		if(start == end)	// 리프노드에 닿으면 결과를 출력한다.
		{
			sb.append(map[start]).append(' ');
			return;
		}
		int mid = (start + end) >> 1;	// 왼쪽 오른쪽을 나눌 기준이 되는 노드
		
		postOrderDFS(start, mid -1);	// 왼쪽 서브트리를 먼저 탐색
		postOrderDFS(mid + 1, end);		// 오른쪽 서브트리를 그 다음 탐색
		
		sb.append(map[mid]).append(' ');// 자기자신을 넣음(리프노드가 넣어진 후 자기자신을 넣기 때문에 후위순회임.
	}
	public static void main(String[] args)throws Exception{
		N	= read();
		map = new int[N];
		idx = 0;
		
		for(int i=0; i<N; i++)
			if((map[i] = read())==-1)
				idx = i;

		map[idx] = read();
		
		Arrays.sort(map);
		
		postOrderDFS(0, N-1);
		
		System.out.print(sb.toString());
	}
}