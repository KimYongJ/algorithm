//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11046
//1초, 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int len		= (N<<1) + 1;
		int arr[]	= new int[len];
		int A[]		= new int[len];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0,j = 0; i<N; i++,j += 2)
		{
			arr[j]	= -1;
			arr[j+1]= Integer.parseInt(st.nextToken());
		}
		arr[len-1] = -1;
		
		
		for(int i=0,r=0,p=0; i<len; i++)
		{
			if(i<=r)
				A[i] = Math.min(r-i, A[2*p-i]);
			
			while(0<=i-A[i]-1 && i+A[i]+1<len && arr[i-A[i]-1] == arr[i+A[i]+1])
				++A[i];
			
			if(r < i + A[i])
			{
				r = i + A[i];
				p = i;
			}
		}
		
		StringBuilder res = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(e < s)
			{
				int tmp = e;
				e = s;
				s = tmp;
			}
			int r = e - s + 1;
			res.append(A[(s*2-1 + e*2-1)/2] >= r ? 1 : 0).append('\n');
		}
		System.out.print(res.toString());
	}
}