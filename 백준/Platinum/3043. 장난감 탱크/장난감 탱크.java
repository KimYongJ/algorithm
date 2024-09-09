//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3043
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
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
	
	public static void mark(int num, char c) {
		sb.append(num).append(' ').append(c).append('\n');
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine()); // N(5<=500)
		Node row[]	= new Node[N];// 가로 정보
		Node col[]	= new Node[N];// 세로정보
		int cnt		= 0;
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r	= Integer.parseInt(st.nextToken());
			int c	= Integer.parseInt(st.nextToken());
			row[i]	= new Node(i+1, r);
			col[i]	= new Node(i+1, c);
		}
		Arrays.sort(row,(a,b)->a.idx - b.idx);
		Arrays.sort(col,(a,b)->a.idx - b.idx);
		
		
		for(int i=0; i<N; i++)
		{
			Node now = row[i];
			int idx = now.idx;
			int target = i + 1;
			while(idx > target) {
				++cnt;
				--idx;
				mark(now.num, MOVE_UP);
			}
		}
		for(int i=N-1; i>=0; i--) {
			Node now = row[i];
			int idx = now.idx;
			int target = i + 1;
			while(idx < target) {
				++cnt;
				++idx;
				mark(now.num, MOVE_DOWN);
			}
		}
		for(int i=0; i<N; i++)
		{
			Node now = col[i];
			int idx = now.idx;
			int target = i + 1;
			while(idx > target) {
				++cnt;
				--idx;
				mark(now.num, MOVE_LEFT);
			}
		}
		for(int i=N-1; i>=0; i--) {
			Node now = col[i];
			int idx = now.idx;
			int target = i + 1;
			while(idx < target) {
				++cnt;
				++idx;
				mark(now.num, MOVE_RIGHT);
			}
		}
		
		
		
		
		System.out.println(cnt);
		System.out.print(sb.toString());
	}
}
