//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17503
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node{
	int pre, lev;// 선호도(0<=만), 도수(1<=인트최대)
	Node(int p, int l){this.pre = p; this.lev = l;}
}
class Main{
	public static boolean check(Node[] node, long level, int target, int day) {
		long sum = 0;
		for(Node n : node)
		{
			if(n.lev <= level)
			{
				sum += n.pre;
				if(--day == 0)
					break;
			}
		}
		return day == 0 && sum >= target;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 축제 기간(1<=이십만)
		int M		= Integer.parseInt(st.nextToken());	// 채워야하는 선호도의 합(1<=인트형최대)
		int K		= Integer.parseInt(st.nextToken());	// 마실 수 있는 맥주 종류수 (N<=이십만)
		Node node[] = new Node[K];

		for(int i=0; i<K; i++)
		{
			st		= new StringTokenizer(br.readLine());
			int p	= Integer.parseInt(st.nextToken());	// 선호도
			int l	= Integer.parseInt(st.nextToken());	// 도수
			node[i] = new Node(p, l);
		}
		
		Arrays.sort(node, (a,b)-> b.pre - a.pre);		// 선호도 기준 내림차순
		
		long s = 0;
		long e = Integer.MAX_VALUE;
		long res = -1;
		
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