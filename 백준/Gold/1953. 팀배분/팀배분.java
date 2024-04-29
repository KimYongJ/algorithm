// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int N;
	static StringBuilder sb;
	static ArrayList<Integer> map[], blue,white;
	public static void set(ArrayList<Integer> list) {
		Collections.sort(list);
		sb.append(list.size()).append('\n');
		for(int i=0; i<list.size(); i++)
			sb.append(list.get(i)).append(' ');
		sb.append('\n');
	}
	public static void DFS(int node) {
		if(node == N) {
			if(blue.size() !=0 && white.size() !=0) {
				set(blue);
				set(white);
				System.out.print(sb);
				System.exit(0);
			}
			return;
		}
		boolean flag = true;
		for(int hate : map[node]) {
			if(blue.contains(hate)) // blue에 싫어 하는 사람이 없다면
			{
				flag = false;
				break;
			}
		}
		if(flag) {
			blue.add(node);
			DFS(node+1);
			blue.remove(blue.size()-1);
		}
		flag = true;
		for(int hate : map[node]) {
			if(white.contains(hate)) // white에 싫어 하는 사람이 없다면
			{
				flag = false;
				break;
			}
		}
		if(flag) {
			white.add(node);
			DFS(node+1);
			white.remove(white.size()-1);
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine())+1;
		map 	= new ArrayList[N];
		blue 	= new ArrayList<>();
		white 	= new ArrayList<>();
		sb 		= new StringBuilder();
		for(int i=0; i<N; i++)
			map[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			int J = Integer.parseInt(st.nextToken());
			for(int j=0; j<J; j++) 
			{
				int X = Integer.parseInt(st.nextToken());
				if(!map[i].contains(X))
					map[i].add(X);
				if(!map[X].contains(i))
					map[X].add(i);
			}
		}
		DFS(1); // 학생번호
	}
}
