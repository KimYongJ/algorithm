//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22988
//1초 / 1024mb
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());	// 용기 수 1<=십만
		long X	= Long.parseLong(st.nextToken());	// 10의18승
		long P	= (X + 1) / 2;
		int res = 0;
		
		ArrayList<Long> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			long num = Long.parseLong(st.nextToken());
			if(num == X)
				++res;
			else
				list.add(num);
		}
		
		Collections.sort(list);
		
		int remain = 0;// 그리디하게 못만드는 값들
		int s = 0;
		int e = list.size()-1;
		while(s<=e)
		{
			long sum = list.get(s) + list.get(e);
			if(s<e && P <= sum)
			{
				++res;
				++s;
				--e;
			}
			else
			{
				++s;
				++remain;
			}
		}
		System.out.print(res + remain / 3);
	}
}