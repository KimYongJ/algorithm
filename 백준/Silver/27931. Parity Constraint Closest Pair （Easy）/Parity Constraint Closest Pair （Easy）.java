//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27931
//2초 / 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 점개수
		long oddMin	= Long.MAX_VALUE;
		long eveMin	= Long.MAX_VALUE;
		ArrayList<Long> odd = new ArrayList<>();
		ArrayList<Long> even= new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			long n = Integer.parseInt(st.nextToken());
			if(n%2 == 0)
				even.add(n);
			else
				odd.add(n);
		}
		
		Collections.sort(odd);
		Collections.sort(even);
		
		// 차이가 홀수인 경우 : 홀수 - 짝수 했을 때만 홀수가된다.
		int o = 0;
		int e = 0;
		while(o<odd.size() && e<even.size())
		{
			long numO = odd.get(o);
			long numE = even.get(e);
			long diff = Math.abs(numO - numE);
			oddMin = Math.min(diff, oddMin);
			if(numO < numE)
				++o;
			else ++e;
		}
		
		
		// 차이가 짝수인 경우 : 짝수 - 짝수 or 홀수 - 홀수
		for(int i=1; i<odd.size(); i++)
			eveMin = Math.min(eveMin, Math.abs(odd.get(i)-odd.get(i-1)));
		for(int i=1; i<even.size(); i++)
			eveMin = Math.min(eveMin, Math.abs(even.get(i)-even.get(i-1)));
		
		if(oddMin == Long.MAX_VALUE)oddMin = -1;
		if(eveMin == Long.MAX_VALUE)eveMin = -1;
		
		System.out.printf("%d %d", eveMin, oddMin);
	}
}