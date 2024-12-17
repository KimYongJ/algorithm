//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16163
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(char c : br.readLine().toCharArray())
			sb.append(' ').append(c);
		sb.append(' ');
		
		int len = sb.length();
		int A[] = new int[len];
		long cnt = 0;
		for(int i=0,r=0,p=0; i<len; i++)
		{
			
			if(i<=r)
				A[i] = Math.min(r-i, A[2*p-i]);
			
			while(0<=i-A[i]-1 && i+A[i]+1<len && sb.charAt(i-A[i]-1) == sb.charAt(i+A[i]+1))
				++A[i];
			
			if(r < i+A[i])
			{
				r = i + A[i];
				p = i;
			}

			cnt +=(A[i]+1)/2;
		}
		System.out.print(cnt);
	}
}