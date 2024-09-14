//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/4158
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0 && M == 0)
				break;
			int res = 0;
			HashSet<Integer> set = new HashSet<>();
			while(N-->0)
				set.add(Integer.parseInt(br.readLine()));
			while(M-->0)
				if(set.contains(Integer.parseInt(br.readLine())))
					res++;

			sb.append(res).append('\n');
		}

		System.out.print(sb.toString());
	}
}