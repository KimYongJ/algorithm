//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17503
import java.util.Arrays;
class Node{
	int pre, lev;// 선호도(0<=만), 도수(1<=인트최대)
	Node(int p, int l){this.pre = p; this.lev = l;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(Node[] node, long level, int target, int day) {
		long sum = 0;
		for(Node n : node)
			if(n.lev <= level)
			{
				sum += n.pre;
				if(--day == 0)
					break;
			}

		return day == 0 && sum >= target;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();	// 축제 기간(1<=이십만)
		int M		= read();	// 채워야하는 선호도의 합(1<=인트형최대)
		int K		= read();	// 마실 수 있는 맥주 종류수 (N<=이십만)
		Node node[] = new Node[K];

		for(int i=0; i<K; i++)
			node[i] = new Node(read(), read()); // 선호도, 도수
		
		Arrays.sort(node, (a,b)-> b.pre - a.pre);		// 선호도 기준 내림차순
		
		long s		= 0;
		long e		= Integer.MAX_VALUE;
		long res 	= -1;
		
		while(s <= e)
		{
			long mid = (s + e) >> 1;
			if(check(node, mid, M, N))
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		System.out.print(res);
	}
}