//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/28081
// 1초 / 1024MB
// 요약 : 사각형에서 가로커팅위치, 세로커팅 위치가 주어지면 커팅된 사각형의 넓이가 K이하인 것들 찾기
// 해설 : 가로길이 리스트, 세로길이 리스트를 각각 구해서 한개만 정렬한 후 이분 탐색으로 곱했을 때 K이하인 인덱스만큼 결과에 더해준다.
import java.util.ArrayList;
import java.util.Collections;
class Main{
	
	static long W,H,K,s, cut;
	static ArrayList<Long> hlist = new ArrayList<>();
	static ArrayList<Long> wlist = new ArrayList<>();
	
	public static void main(String[]args)throws Exception{
		W = read();	//가로(1<=십억)
		H = read();	//세로(1<=십억)
		K = read();	//먹을 수 있는 최대크기(1<=가로*세로)
		
		// 가로 커팅 개수, H를 나누는 것, 오름차순으로 주어짐
		cut = read();
		s	= 0;
		while(cut-->0)
		{
			long e = read();
			hlist.add(e-s);
			s = e;
		}
		hlist.add(H - s);	// 마지막 커팅
		
		// 세로 커팅 개수, W를 나누는 것, 오름차순으로 주어짐
		cut = read();

		s	= 0;
		while(cut-->0)
		{
			long e = read();
			wlist.add(e - s);
			s = e;
		}
		wlist.add(W-s);

		Collections.sort(wlist);
		
		long result = 0;
		for(long h : hlist)
		{
			int idx = binarySearch(h);
			if(0<=idx)
				result += idx + 1;
		}
		System.out.print(result);
	}
	public static int binarySearch(long h) {
		int s = 0;
		int e = wlist.size()-1;
		int r = -1;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(wlist.get(mid) * h <= K)
			{
				r = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		
		return r;
	}
	static long read() throws Exception {
		long c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}