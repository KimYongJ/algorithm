//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		String str[]	= new String[N];
		ArrayList<String> result = new ArrayList<>();
		
		for(int i=0; i<N; i++)
			str[i] = br.readLine();
		
		Arrays.sort(str,(a,b)->b.length() - a.length());
		
		for(int i=0; i<N; i++)
		{
			boolean flag = true;
			for(String s : result) 
			{
				if(s.startsWith(str[i])) 
				{
					flag = false;
					break;
				}
			}
			if(flag) 
			{
				result.add(str[i]);
			}
		}
		System.out.print(result.size());
	}
}