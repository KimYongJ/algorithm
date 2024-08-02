//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		int N				= Integer.parseInt(br.readLine());
		HashSet<String> set	= new HashSet<>();
		
		for(int i=0; i<N; i++)
			set.add(br.readLine());
		
		ArrayList<String> list = new ArrayList<>(set);
		
		Collections.sort(list);
		
		N = set.size();
		
		int cnt = 1;
		for(int i=1; i<N; i++)
			if(!list.get(i).startsWith(list.get(i-1)))
				cnt++;
		
		System.out.print(cnt);
	}
}