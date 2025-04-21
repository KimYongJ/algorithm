//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/16993
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static class Node{
		int left, right, sum, max;
		Node(int l, int r, int s, int m){
			left = l;
			right = r;
			sum = s;
			max = m;
		}
	}
	
	static final int MIN = -100_000_005;
	static int[] arr;
	static Node[] tree;
	static Node dummy = new Node(MIN, MIN, 0, MIN);
	
	public static Node init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = new Node(arr[s], arr[s], arr[s], arr[s]);

		int mid = (s + e) >> 1;
		
		Node l = init(treeNode << 1, s, mid);
		Node r = init(treeNode << 1 | 1, mid + 1, e);
		
		return tree[treeNode] 
					= new Node(
								Math.max(l.left, l.sum + r.left),
								Math.max(r.right, r.sum + l.right),
								r.sum + l.sum,
								Math.max(l.right+r.left, Math.max(l.max, r.max))
							);
	}
	
	public static Node query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return dummy;
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		Node l = query(treeNode << 1, s, mid, left, right);
		Node r = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return new Node(
					Math.max(l.left, l.sum + r.left),
					Math.max(r.right, r.sum + l.right),
					l.sum + r.sum,
					Math.max(l.right + r.left, Math.max(l.max, r.max))
				);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());//수열의 크기 N(1<=100,000)
		arr		= new int[N + 1];
		tree	= new Node[N * 4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 수열 ((절대값) |Ai| <= 1,000)
		
		init(1 ,1, N);
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());	// 1<=N
			int j = Integer.parseInt(st.nextToken());	// 1<=N
			
			// i,j 구간의 가장 큰 부분합 출력
			Node ans = query(1, 1, N, i, j);
			
			sb.append(ans.max).append('\n');
		}
		System.out.print(sb);		
	}
}

//10									// 수열의 크기 N(1<=100,000)
//10 -4 3 1 5 6 -35 12 21 -1			// 수열 ((절대값) |Ai| <= 1,000)
//10									// 쿼리개수 M(1<=100,000)
//1 1
//3 4
//1 6
//2 6
//6 6
//7 7
//8 9
//8 10
//1 10
//5 8
//// M개 줄에 쿼리 요청에 대한 출력을 한 줄에 하나씩 순서대로 출력
//10
//4
//21
//15
//6
//-35
//33
//33
//33
//12