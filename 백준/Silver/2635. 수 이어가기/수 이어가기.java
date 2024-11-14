//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2635
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> result = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());	// 0<=30,000;
		for(int i=0; i<=N; i++)
		{
			List<Integer> part = new ArrayList<>();
			part.add(N);
			part.add(i);
			int s = part.get(0) - part.get(1);
			while(0<=s)
			{
				part.add(s);
				s = part.get(part.size()-2) - s;
			}
			if(result.size() < part.size())
				result = part;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(result.size()).append('\n');
		for(int l : result)
			sb.append(l).append(' ');
		System.out.print(sb);
	}
}