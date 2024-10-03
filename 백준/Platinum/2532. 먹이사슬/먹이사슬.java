//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2532
import java.util.Arrays;
class Node{
	int l, r;Node(int l, int r){this.l=l; this.r=r;}
}
class Main{
	
	static int N, len, LIS[];
	static Node node[];
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int binarySearch(int target) {
		int s	= 0;
		int e	= len - 1;
		int res	= 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target < LIS[mid])
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		
		return res;
	}
	public static void main(String[] args)throws Exception{
		N		= read();		// 동물의 수 (1<=50만)
		len		= 1;			// 가장긴 부분수열의 길이를 나타낼 변수
		LIS		= new int[N];	// 가장긴 부분수열을 담을 배열
		node	= new Node[N];
		
		for(int i=0; i<N; i++)
		{
			read();
			node[i] = new Node(read(), read());
		}
		
		Arrays.sort(node, (a,b) -> a.l != b.l ? a.l - b.l : b.r - a.r);
		
		LIS[0] = -node[0].r;				// 음수로 바꿔줌으로, 가장 긴 감소하는 부분수열을 가장 긴 증가하는 부분수열로 연산토록한다.
		
		for(int i=1; i<N; i++)
		{
			if(node[i].l == node[i-1].l && node[i].r == node[i-1].r)
				continue;
			int val = -node[i].r;			// 음수로 바꿔줌으로, 가장 긴 감소하는 부분수열을 가장 긴 증가하는 부분수열로 연산토록한다.
			if(LIS[len-1] <= val)
				LIS[len++] = val;
			else {
				int idx = binarySearch(val);// 가장 긴 증가하는 부분 수열
				LIS[idx] = val;
			}
		}
		System.out.print(len);
	}
}