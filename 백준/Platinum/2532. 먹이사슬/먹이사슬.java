//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2532
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node{
	int l, r;Node(int l, int r){this.l=l; this.r=r;}
}
class Main{
	
	static int N, len, LIS[];
	static Node node[];
	public static int upperBound(int target) {
		int s	= 0;
		int e	= len - 1;
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target <= LIS[mid])
			{
				s = mid + 1;
				res = mid;
			}
			else
				e = mid - 1;
		}
		
		return res+1;
	}
	public static int binarySearch(int target) {
		int s	= 0;
		int e	= len - 1;
		int res	= 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target == LIS[mid])
			{
				return upperBound(target); // target을 갖는 가장큰 idx
			}
			if(LIS[mid] < target)
			{
				e = mid - 1;
				res = mid;
			}
			else {
				s= mid + 1;
			}
		}
		
		return res;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());	// 동물의 수 (1<=50만)
		len		= 1;								// 가장긴 부분수열의 길이를 나타낼 변수
		LIS		= new int[N];						// 가장긴 부분수열을 담을 배열
		node	= new Node[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			node[i] = new Node(l, r);
		}
		
		Arrays.sort(node, (a,b) -> a.l != b.l ? a.l - b.l : b.r - a.r);
		
		LIS[0] = node[0].r;
		
		for(int i=1; i<N; i++)
		{
			if(node[i].l == node[i-1].l && node[i].r == node[i-1].r)
				continue;
			if(node[i].r <= LIS[len-1])
				LIS[len++] = node[i].r;
			else {
				int idx = binarySearch(node[i].r);// 가장 긴 감소하는 부분수열
				LIS[idx] = node[i].r;
			}
		}
		System.out.print(len);
	}
}