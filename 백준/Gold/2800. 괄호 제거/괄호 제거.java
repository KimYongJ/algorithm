//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2800
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
class Point{
	int a, b;
	Point(int a, int b){this.a=a; this.b=b;}
}
class Main{
	
	static char arr[];
	static Set<String> result	= new TreeSet<>();
	static List<Point> list		= new ArrayList<>();
	
	public static void bruteforce(int idx) {
		if(idx == list.size())
		{
			StringBuilder sb = new StringBuilder();
			for(char c : arr)
				if(c != 0)
					sb.append(c);
			result.add(sb.toString());
			return;
		}
		
		bruteforce(idx + 1);	// 해당 괄호를 안지운다.
		
		Point p = list.get(idx);
		arr[p.a]= 0;
		arr[p.b]= 0;
		bruteforce(idx + 1);	// 해당 괄호를 지운다.
		arr[p.a]= '(';
		arr[p.b]= ')';
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str	= br.readLine();
		arr			= new char[str.length()];
		int[] par	= new int[11];				// 시작괄호('(') 를 담을 배열
		
		for(int i=0, parIdx = 0; i<str.length(); i++)
		{
			arr[i] = str.charAt(i);
			if(arr[i] == '(')		// 시작괄호를 만나면 배열에 담음
				par[parIdx++] = i;
			else if(arr[i] == ')')	// 닫는 괄호를 만나면 여는괄호와 홤께 리스트에 담음
				list.add(new Point(par[--parIdx],i));
		}
		
		bruteforce(0);

		result.remove(str);

		StringBuilder sb = new StringBuilder();
		for(String r : result)
			sb.append(r).append('\n');
		System.out.print(sb);
	}
}