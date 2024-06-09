// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int a,b,c,d,e;
	Node(int a, int b, int c, int d, int e){
		this.a=a; this.b=b; 
		this.c=c; this.d=d; 
		this.e=e;
	}
}

class Main{
	
	static int 	result = Integer.MAX_VALUE;
	static int 	resultArr[];
	static int 	arr[];
	static int	N, A, B, C, D;
	static int	MAX;
	static Node	node[];
	
	public static boolean validate() {
		if(result == Integer.MAX_VALUE)
			return true;
		
		for(int i=0; i<N; i++) 
		{
			if(resultArr[i] == 0 && arr[i] != 0 || resultArr[i] < arr[i]) {
				return false;
			}else if(resultArr[i] != 0 && arr[i] == 0 || resultArr[i] > arr[i]) {
				return true;
			}
		}
		return true;
	}
	public static void dfs(int depth, int idx, int a, int b, int c, int d, int e) {
		if(A<=a && B<=b && C<=c && D<=d) 
		{
			if(result == e) {
				if(validate()) {
					result = e;
					resultArr = arr.clone();
				}
			}else if(result >  e){
				result = e;
				resultArr = arr.clone();
			}
		}
		if(depth == N) 
			return;
		
		for(int i=idx; i<=N; i++) 
		{
			Node n = node[i];
			arr[depth] = i;
			dfs(depth + 1, i+1, a+n.a, b+n.b, c+n.c, d+n.d, e+n.e);
			arr[depth] = 0;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N			= Integer.parseInt(br.readLine());
		arr 		= new int[N];
		resultArr	= new int[N];
		node		= new Node[N+1];
		
		st	= new StringTokenizer(br.readLine());
		A	= Integer.parseInt(st.nextToken());
		B	= Integer.parseInt(st.nextToken());
		C	= Integer.parseInt(st.nextToken());
		D	= Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++) 
		{
			st		= new StringTokenizer(br.readLine());
			node[i] = new Node(
								Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken())
								);
		}
		
		dfs(0,1,0,0,0,0,0);
		
		if(result == Integer.MAX_VALUE) 
		{
			System.out.print(-1);
		}
		else {
			StringBuilder sb = new StringBuilder();
			
			sb.append(result).append('\n');
			
			for(int r : resultArr)
				if(r == 0)	
					break;
				else 		
					sb.append(r).append(' ');
			
			System.out.print(sb);
		}
	}
}