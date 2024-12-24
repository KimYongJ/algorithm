//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31837
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Sub{
	int idx, credit, day, start, end;
	Sub(int i, int c, int d, int s, int e){idx=i;credit=c; day=d; start=s; end=e;}
}

class Main{
	
	static int N, cnt;
	static Sub[] order;
	static ArrayList<Sub>[] list;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine()); // 그룹의 개수 N(1<=15)
		list	= new ArrayList[N];
		order	= new Sub[N];
		
		for(int i=0; i<N; i++)
		{
			list[i] = new ArrayList<>();
			int t = Integer.parseInt(br.readLine());// 해당 그룹에 포함된 과목 개수 (1<=15)
			for(int j=0; j<t; j++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int credit		= Integer.parseInt(st.nextToken());	// 학점(1<=22)
				int day			= Integer.parseInt(st.nextToken());	//요일(1<=7)
				String start[]	= st.nextToken().split(":");		//시작시간(00<=23 : 00<=59)
				String end[]	= st.nextToken().split(":");		//종료시간(00<=23 : 00<=59)
				int s			= Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
				int e			= Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);
				list[i].add(new Sub(j+1,credit, day, s, e));
			}
		}
		DFS(0, 0);
		
		System.out.print(cnt);
	}
	public static void DFS(int depth, int sum) {
		if(22 < sum) return;
		if(depth == N)
		{
			if(sum == 22)
			{
				++cnt;
			}
			return;
		}
		
		order[depth] = null;
		DFS(depth + 1, sum); //선택하지 않고 넘어간다

		for(Sub now : list[depth])
		{
			boolean isContinue = true;
			for(int i=0; i<depth; i++)
				if(order[i] != null && order[i].day == now.day && !((now.end <= order[i].start || order[i].end <= now.start)))
				{
					isContinue = false;
					break;
				}
			if(isContinue)
			{
				order[depth] = now;
				DFS(depth + 1, sum + now.credit);
			}
		}
	}
}