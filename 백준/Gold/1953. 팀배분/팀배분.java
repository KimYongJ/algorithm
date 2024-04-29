// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
	
	static int N;
	static StringBuilder sb;
	static boolean[] blue, white;
	static ArrayList<Integer> map[];
	public static void print() {
		StringBuilder blueSb 	= new StringBuilder();
		StringBuilder whiteSb 	= new StringBuilder();
		int blueCnt=0, 
			whiteCnt=0;
		for(int i=1; i<N; i++) 
		{
			if(blue[i]) 
			{
				blueCnt++;
				blueSb.append(i).append(' ');
			}
			if(white[i]) 
			{
				whiteCnt++;
				whiteSb.append(i).append(' ');
			}
		}
		StringBuilder result = new StringBuilder();
		result.append(blueCnt).append('\n').append(blueSb.toString()).append('\n')
			.append(whiteCnt).append('\n').append(whiteSb.toString());
		System.out.println(result);
		System.exit(0);
	}
	public static boolean check() {
		boolean bflag = false, wflag = false;
		for(int i=1; i<N; i++) 
		{
			if(blue[i])bflag 	= true;
			if(white[i])wflag 	= true;
			if(bflag && wflag) 
				return true;
		}
		return false;
	}
	public static void TeamSelect(boolean team[], int node) {
		boolean flag = true;
		for(int hate : map[node]) {
			if(team[hate])
			{
				flag = false;
				break;
			}
		}
		if(flag) {
			team[node] = true;
			DFS(node+1);
			team[node] = false;
		}
	}
	public static void DFS(int node) {
		if(node == N) 
		{
			if(check())
				print();
			return;
		}
		TeamSelect(blue, node);
		TeamSelect(white, node);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine())+1;
		map 	= new ArrayList[N];
		blue 	= new boolean[N];
		white 	= new boolean[N];
		sb 		= new StringBuilder();
		
		for(int i=0; i<N; i++)
			map[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			int J = Integer.parseInt(st.nextToken());
			for(int j=0; j<J; j++) 
				map[i].add(Integer.parseInt(st.nextToken()));
		}
		DFS(1); // 학생번호
	}
}
