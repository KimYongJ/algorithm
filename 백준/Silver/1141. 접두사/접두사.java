//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br			= new BufferedReader(new InputStreamReader(System.in));
		int N						= Integer.parseInt(br.readLine());
		HashSet<String> set			= new HashSet<>();
		ArrayList<String> result	= new ArrayList<>();
		
		for(int i=0; i<N; i++)
			set.add(br.readLine());
		
		ArrayList<String> list	= new ArrayList<>(set);
		
		Collections.sort(list,(a,b)->b.length() - a.length());
		
		N = set.size();
		
		for(int i=0; i<N; i++)
		{
			boolean flag = true;
			for(String s : result) 
				if(s.startsWith(list.get(i))) 
				{
					flag = false;
					break;
				}
			
			if(flag) 
				result.add(list.get(i));
		}
		System.out.print(result.size());
	}
}