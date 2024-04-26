// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

class Node{
	int y, x, fuel;
	Node(int y, int x, int fuel){
		this.y=y; this.x=x; this.fuel=fuel;
	}
}
class Main{
	
	static int Y, X, N, left, right, mid, ans, dp[], listSize, distance;
	static ArrayList<Node> list;
	static BiFunction<Node, Node, Boolean> isMovePossible;
	static BiFunction<Node, Node, Integer> getDistance;
	
	public static boolean check(int mid) {
		dp = new int[listSize];
		Arrays.fill(dp, -1);
		dp[0] = mid;
		for(int i=1; i<listSize; i++) 
			for(int j=0; j<i; j++) 
			{
				distance = getDistance.apply(list.get(j),list.get(i));
				if(!isMovePossible.apply(list.get(j),list.get(i))) 
					continue;
				if(dp[j] < distance) continue;
				
				dp[i] = Math.max(dp[i], dp[j] - distance + list.get(i).fuel);
			}
		return dp[listSize-1] != -1;
	}
	public static void main(String[] args)throws Exception{
		isMovePossible 	= (a,b) -> a.y<=b.y && a.x<=b.x;
		getDistance 	= (a,b) -> Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 		= Integer.parseInt(st.nextToken());
		X 		= Integer.parseInt(st.nextToken());
		N 		= Integer.parseInt(br.readLine());
		list 	= new ArrayList<Node>() {{
			add(new Node(0,0,0));
			add(new Node(Y-1,X-1,0));
			}};
		int a1,a2,a3;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			a1 = Integer.parseInt(st.nextToken())-1;
			a2 = Integer.parseInt(st.nextToken())-1;
			a3 = Integer.parseInt(st.nextToken());
			list.add(new Node(a1,a2,a3));
		}
		listSize = list.size();
		Collections.sort(list,(a,b)->Integer.compare(a.x+a.y, b.x+b.y));
		
		right = 6000;
		while(left <= right) {
			mid = (left + right) >> 1;
			if(check(mid)) {
				ans 	= mid;
				right 	= mid - 1;
			}else left 	= mid + 1;
		}
		
		System.out.println(ans);
	}
}