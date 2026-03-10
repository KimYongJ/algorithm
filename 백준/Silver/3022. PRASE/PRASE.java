//https://www.acmicpc.net/problem/3022
//1초 128MB
//4 // 가져간 횟수, 1<=100
//mirko // 가져간 사람 이름
//stanko
//stanko
//stanko
//경고 횟수 : 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		int idx = 0;
		int warn = 0;
		int food[] = new int[N + 1];
		
		for(int i=0; i<N; i++)
		{
			String name = br.readLine();
			
			if(!map.containsKey(name))
			{
				map.put(name,idx);
				++food[idx];
				++idx;
				continue;
			}
			
			int index = map.get(name);

			if(i - food[index] < food[index])
				++warn;
			
			++food[index];
		}
		System.out.print(warn);
	}
}