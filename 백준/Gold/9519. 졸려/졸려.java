//https://www.acmicpc.net/problem/9519
//1초 256MB
//4 // X(1<=1,000,000,000)
//acefdb // 길이 : 3<=1000
//답 : abcdef
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
	
	static StringBuilder front, back;
	static List<String> list;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());// X(1<=1,000,000,000)
		String str = br.readLine();
		front = new StringBuilder();
		back = new StringBuilder();
		list = new ArrayList<>();
		
		list.add(str);
		
		String origin = str;
		
		for(int i=0; i<X; i++)
		{
			str = nextString(str);
			if(!origin.equals(str))
			{
				list.add(str);
				continue;
			}
			int idx = X % list.size();
			System.out.print(list.get(idx));
			return;
		}
		
		System.out.print(str);
	}
	static String nextString(String str) {
		front.setLength(0);
		back.setLength(0);
		
		for(int i=0; i<str.length(); i++)
		{
			if(i%2 == 0) front.append(str.charAt(i));
			else back.append(str.charAt(i));
		}
		
		return new StringBuilder().append(front.toString()).append(back.reverse().toString()).toString();
	}
}