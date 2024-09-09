//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3043
import java.util.Arrays;
class Node{
	int num, idx;
	Node(int n,int i){this.num=n;this.idx=i;}
}
class Main{
	
	static StringBuilder sb = new StringBuilder();
	static final char MOVE_UP		= 'U';
	static final char MOVE_DOWN		= 'D';
	static final char MOVE_LEFT		= 'L';
	static final char MOVE_RIGHT	= 'R';

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void mark(int num, char c) 
	{
		sb.append(num).append(' ').append(c).append('\n');
	}
	public static int MOVE_UP_OR_LEFT(Node arr[], int N, char MOVE) {
		int cnt = 0;
		for(int i=0; i<N; i++)
		{
			Node now = arr[i];
			int idx = now.idx;
			int target = i;
			while(idx > target) {
				++cnt;
				--idx;
				mark(now.num, MOVE);
			}
		}
		return cnt;
	}
	public static int MOVE_DOWN_OR_RIGHT(Node arr[], int N, char MOVE) {
		int cnt = 0;
		for(int i=N-1; i>=0; i--) {
			Node now = arr[i];
			int idx = now.idx;
			int target = i;
			while(idx < target) {
				++cnt;
				++idx;
				mark(now.num, MOVE);
			}
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// N(5<=500)
		int cnt		= 0;
		Node row[]	= new Node[N];	// 가로 정보
		Node col[]	= new Node[N];	// 세로정보
		
		for(int i=0; i<N; i++)
		{
			int r	= read()-1;
			int c	= read()-1;
			row[i]	= new Node(i+1, r);
			col[i]	= new Node(i+1, c);
		}
		
		Arrays.sort(row,(a,b)->a.idx - b.idx);
		Arrays.sort(col,(a,b)->a.idx - b.idx);
		
		cnt += MOVE_UP_OR_LEFT(		row, N, MOVE_UP);
		cnt += MOVE_DOWN_OR_RIGHT(	row, N, MOVE_DOWN);
		cnt += MOVE_UP_OR_LEFT(		col, N, MOVE_LEFT);
		cnt += MOVE_DOWN_OR_RIGHT(	col, N, MOVE_RIGHT);

		System.out.print(new StringBuilder().append(cnt).append('\n')
                         .append(sb.toString()).toString());
	}
}
/*
입력
6
1 1
1 2
2 1
5 6
6 5
6 6
출력
8
2 R
2 D
3 D
3 R
4 U
4 L
5 L
5 U
 * */
