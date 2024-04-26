// https://github.com/kimyongj/algorithm
import java.util.Arrays;
import java.util.function.BiFunction;

class Node{
	int y, x, fuel;
	Node(int y, int x, int fuel){
		this.y=y; this.x=x; this.fuel=fuel;
	}
}
class Main{
	
	static int Y, X, N, left, right, mid, ans, dp[], listSize, distance;
	static Node list[];
	static BiFunction<Node, Node, Boolean> isMovePossible;
	static BiFunction<Node, Node, Integer> getDistance;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int mid) {
		dp = new int[listSize];
		Arrays.fill(dp, -1);
		dp[0] = mid;
		for(int i=1; i<listSize; i++) 
			for(int j=0; j<i; j++) 
			{
				distance = getDistance.apply(list[j],list[i]);
				if(!isMovePossible.apply(list[j],list[i])) 
					continue;
				if(dp[j] < distance) continue;
				
				dp[i] = Math.max(dp[i], dp[j] - distance + list[i].fuel);
			}
		return dp[listSize-1] != -1;
	}
	public static void main(String[] args)throws Exception{
		isMovePossible 	= (a,b) -> a.y<=b.y && a.x<=b.x;
		getDistance 	= (a,b) -> Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
		Y 		= read();
		X 		= read();
		N 		= read();
		list	= new Node[N+2];
		int a1,a2,a3;
		for(int i=0; i<N; i++) {
			a1 		= read()-1;
			a2 		= read()-1;
			a3 		= read();
			list[i] = new Node(a1,a2,a3);
		}
		list[N] 	= new Node(0,0,0);
		list[N+1] 	= new Node(Y-1,X-1,0);
		listSize 	= N+2;
		
		Arrays.sort(list,(a,b)->(a.x+a.y)-(b.x+b.y));
		
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