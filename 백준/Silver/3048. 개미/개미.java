//https://www.acmicpc.net/problem/3048
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		char arr[] = new char[n1+n2];
		boolean isLeft[] = new boolean['Z' + 1];
		String str;
		
		str = br.readLine();
		for(int i=0, j = n1 - 1; i<n1; i++,j--)
			isLeft[(arr[i] = str.charAt(j))] = true;
		
		str = br.readLine();
		for(int i=n1, j=0; i<arr.length; i++,j++)
			arr[i] = str.charAt(j);
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-->0)
		{
			for(int i=1; i<arr.length; i++)
			{
				if(!isLeft[arr[i]] && isLeft[arr[i-1]])
				{
					char tmp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = tmp;
					++i;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(char c : arr)
			sb.append(c);
		
		System.out.print(sb.toString());
	}
}