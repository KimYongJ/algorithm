//https://www.acmicpc.net/problem/5464
//1초 128MB
//3 4	// 주차장 개수(1<=100), 차량 대수(1<=2,000)
//2		// 주차 공간들의 단위당 무게(1<=100)
//3
//5
//200	// 차량 무게(1<=10,000)
//100
//300
//800
//3		// 2*M 개수만큼 양수는 차가들어온것, 음수는 차가 나간것을 의미
//2
//-3
//1
//4
//-4
//-2
//-1
//답 : 5300
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	static int [] cost, weight, park, pos;
	static ArrayDeque<Integer> q;
	
	public static void main(String[]args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 주차장 개수(1<=100)
		M		= Integer.parseInt(st.nextToken());// 차량 대수(1<=2,000)
		cost	= new int[N + 1];
		weight	= new int[M + 1];
		park	= new int[N + 1];
		pos		= new int[M + 1];
		q		= new ArrayDeque<>();
		
		for(int i=1; i<=N; i++)
			cost[i] = Integer.parseInt(br.readLine());
		for(int i=1; i<=M; i++)
			weight[i] = Integer.parseInt(br.readLine());
		
		int res = 0;
		int T = 2 * M;
		
		while(T-->0)
		{
			int num = Integer.parseInt(br.readLine());
			
			if(num < 0)
			{
				num = -num;

				res += cost[pos[num]] * weight[num];
				park[pos[num]] = 0;
				parking();
				continue;
			}
			
			q.add(num);
			
			parking();
		}
		System.out.print(res);
	}
	static void parking() {
		if(q.isEmpty())
			return;
		
		int num = q.peek();
		
		for(int i=1; i<=N; i++)
		{
			if(park[i] == 0)
			{
				park[i] = num;
				pos[num] = i;
				q.poll();
				break;
			}
		}
	}
}