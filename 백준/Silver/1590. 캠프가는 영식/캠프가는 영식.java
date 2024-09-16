//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1590
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 버스의 개수(1<=50)
		int T = Integer.parseInt(st.nextToken());	// 터미널에 도착하는시간 (1<=백만)
		
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());	// 버스 시작 시각(1<=백만)
			int I = Integer.parseInt(st.nextToken());	// 간격(1<=만)
			int C = Integer.parseInt(st.nextToken());	// 대수(1<=백)
			while(C-->0)
			{
				set.add(S);
				S += I;
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>(set);
		
		Collections.sort(list);
		
		int s = 0;
		int e = list.size()-1;
		int res = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(list.get(mid) < T) {
				s = mid + 1;
			}else {
				e = mid - 1;
				res = mid;
			}
		}
		if(res != -1)
			res = list.get(res) - T;

		System.out.print(res);
	}
}