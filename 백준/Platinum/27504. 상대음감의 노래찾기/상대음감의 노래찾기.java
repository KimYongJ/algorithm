//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27504
//1초, 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[][]	= new int[N][];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			arr[i] = new int[len - 1];
			int s = Integer.parseInt(st.nextToken());
			for(int j=0; j<len-1; j++)
			{
				int e = Integer.parseInt(st.nextToken());
				arr[i][j] = e - s;
				s = e;
			}
		}
		int len = Integer.parseInt(br.readLine()) - 1;
		int pattern[] = new int[len];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		for(int i=0; i<len; i++)
		{
			int e = Integer.parseInt(st.nextToken());
			pattern[i] = e - s;
			s = e;
		}
		
		int fail[] = new int[len];
		for(int i=1, j=0; i<len; i++)
		{
			while(0<j && pattern[i] != pattern[j])
				j = fail[j - 1];
			
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		StringBuilder sb = new StringBuilder();
		for(int idx=0; idx<N; idx++)
		{
			int len2 = arr[idx].length;
			if(len <= len2)
			{
				for(int i=0, j=0; i<len2; i++)
				{
					while(0<j && arr[idx][i] != pattern[j])
						j = fail[j - 1];
					
					if(arr[idx][i] == pattern[j])
					{
						if(j == len - 1)
						{
							sb.append(idx + 1).append(' ');
							break;
						}
						else ++j;
					}
				}
				
			}
		}
		System.out.print(sb.length() == 0 ? -1 : sb.toString());
	}
}
