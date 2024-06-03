// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int 		result = Integer.MAX_VALUE;
	static int 		N;
	static int 		price[];
	static boolean 	visit[];
	static ArrayList<int[]>[] saleList;
	
	public static void changePrice(boolean falg,int idx) {
		if(falg)
		{// flag = true 면 price에 값을 뺀다.
			for(int[] p : saleList[idx])
				price[p[0]] -= p[1];
		}
		else
		{// flag = false 면 price에 값을 더한다.
			for(int[] p : saleList[idx])
				price[p[0]] += p[1];
		}
	}
	public static void backtracking(int depth,int sum) {
		if(sum > result) return;
		if(depth == N) 
		{
			result = sum;
			return;
		}
		
		for(int i=0; i<N; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] = true;
				changePrice(true,i);
				backtracking(depth + 1,sum + (price[i] < 1 ? 1 : price[i]));
				changePrice(false,i);
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N			= Integer.parseInt(br.readLine());
		price 		= new int[N];
		visit		= new boolean[N];
		saleList 	= new ArrayList[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			price[i]	= Integer.parseInt(st.nextToken());
			saleList[i] = new ArrayList<>();
		}
		
		int a,b,n;
		for(int i=0; i<N; i++) 
		{
			n = Integer.parseInt(br.readLine());
			for(int j=0; j<n; j++) 
			{
				st	= new StringTokenizer(br.readLine());
				a	= Integer.parseInt(st.nextToken())-1;
				b 	= Integer.parseInt(st.nextToken());
				saleList[i].add(new int[] {a,b});	// 특정 노드의 할인 정보를 담는다.
			}
		}
		
		backtracking(0,0);
		System.out.print(result);
	}
}