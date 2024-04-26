//https://github.com/KimYongJ/algorithm
import java.util.Arrays;

class Node{
	int y, x, fuel;
	Node(int y, int x, int fuel){
		this.y=y;this.x=x;this.fuel=fuel;
	}
}
class Main{
	
	static int N;
	static Node[] nlist;
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static boolean check(int mid) {
		int distance,
		dp[] = new int[N]; // dp[i]에 담기는 것은 1,1부터 i의 위치까지 이동할 때 연료가 가장 많이 남는 상황의 연료가 담긴다. 
		Arrays.fill(dp, -1);
		
		dp[1] = mid;
		for(int i=2; i<N; i++) // 목표지점
			for(int j=1; j<i; j++) // 시작지점, 시작지점 -> 목표지점
			{
				distance = Math.abs(nlist[i].y - nlist[j].y) + Math.abs(nlist[i].x - nlist[j].x);
				// 오른쪽 이나 아래인지 체크
				if(nlist[i].y < nlist[j].y || nlist[i].x < nlist[j].x)
					continue;
				// 다음 장소까지 이동할 연료가 있는지 체크
				if(dp[j] < distance)
					continue;
				// dp[i]에는 항상 최대 값만 담기도록 한다. i위치 까지 이동하는데 새로 연산한 결과(dp[j] - distance + nlist[j].fuel) 와 
				// 기존 연산해놓은 결과(dp[i]) 중 큰 것을 dp[i]에 항상 담음으로 죄종 목적지까지 도착했을 때 가장 연료가 많이 남아있음이 보장되고
				// 이것은 가장 연료를 적게 썻다는 것을 의미한다. 여기서 i는 목표지점이다. j에서 시작해서 i로 가는 상황
				dp[i] = Math.max(dp[i], dp[j] - distance + nlist[i].fuel);
			}
		
		return dp[N-1] != -1;
	}
	public static void main(String[] args)throws Exception{
		int Y 	= read();
		int X 	= read();
		N 		= read()+3;
		nlist 	= new Node[N];
		nlist[0] = new Node(0,0,0); // 안씀 패팅임
		nlist[1] = new Node(1,1,0);
		nlist[2] = new Node(Y,X,0);
		
		for(int i=3; i<N; i++) 
			nlist[i] = new Node( read(),read(),read() );
		
		Arrays.sort(nlist, (a,b) -> (a.y+a.x)-(b.y+b.x));
		
		int left = 0, right=6000, mid;
		while(left < right) {
			mid = (left + right) >> 1;
			if(check(mid)) 
				right = mid;
			else 
				left = mid + 1;
		}
		System.out.println(right);
	}
}