// https://github.com/KimYongJ/algorithm
// 0부터 N 사이 까지 비밀번호를 내마음대로 정할 수 있다(0,1,2,3,4, ... , N까지 가능) 
// 0부터 N 사이 특정수를 num이라할 때, num에 대해 집합M(지금까지 시도된 비밀번호들)의 원소들에 대해 안전거리를 각각 구해서 그 중 최소 안전거리가 num의 안전거리가 된다. 
// 문제는 특정수 num의 안전거리가 가장 큰 값일 때 안전거리를 구하는 것이다.
// 그럼 집합M(해커가 이미 대입을 시도했던 비번들)에 있는 숫자는 특정수 num이랑 같을 때 안전거리가 0일 테니, 집합M에 대한 숫자는 제거하고,
// 집합M에 포함되지 않는 숫자에 대해 안전거리를 구해야한다. 
// 집합M에 없는 특정수 num에 대해, 집합M의 원소를 하나하나 돌면서 안전거리를 각각 다 구해서 가장 최소인 것을 num의 안전거리고 하며 이것을 (N-M)만큼 반복해야한다(N가지 경우의 수에서 이미 시도한M을 뺀 횟수 만큼.)
// 이렇게 하면 시간 복잡도는 (N-M) * M = 최악의 경우 90,000,000,000( 약 900억 )
// 1초안에 해결하기 위해 다른 방법을 모색한다.
// 문제 요구사항은 가장 먼 거리만 구하는 것이지 가장 먼 특정 숫자(0~N사이숫자)를 구하는 것이 아니다.
// 그럼 집합 M의 숫자들에 대해서 각각 BFS를 돌면서 가장 먼 수를 구하면된다.
// 집합M의 각 숫자들에 대해서 각각 1단계씩 떨어진 숫자들을 구해나간다.
// 근데 특정 num에 대해 1단계 먼거리가, 다른 num에 대해서는 4단계먼 숫자일 경우가 있을 수 있다. 
// 그러나 우리는 해커가 대입한 모든 숫자들을 하나의 그룹으로 보고, 그 그룹에서 안전거리가 최대인 값만 구하는것이다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
public class Main {
	static int N, M, num, flag, size, depth, next;
	static boolean visit[];
	static ArrayDeque<Integer> q;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visit = new boolean[N+1];
		q = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) 
		{
			next = Integer.parseInt(st.nextToken());// 이미 대입해본 비번을 가져온다.
			visit[next] = true;						// 해당 비번 방문처리
			q.add(next);							// 대입해본 비번을 통해 가장 먼거리를 구해야 하기 때문에 
		}
		
		while(!q.isEmpty()) 
		{
			depth++;								// 해커가 대입해본 비번들로 부터 1단계씩 떨어진 숫자들을 구해나간다.
			size = q.size();						// 해커가 대입해본 비번들을 갖고 거리가 1씩 떨어진 것들을 구해나간다. 
			for(int i=0; i<size; i++) 
			{
				flag=1;								// XOR(^)연산을 위해 사용할 flag
				num = q.poll();						// 해커가 대입해본 숫자를꺼낸다( 추후에는 해커가 대입해본 숫자로 부터 계속 1씩 떨어진 숫자를 꺼내게 된다 )
				while(flag<=N) 
				{
					next = flag^num;				// num(큐에서 꺼낸 데이터)에 대해 1만큼 떨어진 숫자를 구한다.	
					if(next <= N && !visit[next])	// 큐에서꺼낸 num과 1만큼 차이나는 숫자가 N보다 작거나같고 방문하지 않았을 때 연산처리 
					{
						visit[next] = true;
						q.add(next);
					}
					flag <<= 1;						// num에서 1만큼 떨어진 숫자를 지속적으로 구해 나가기 위한 shift 연산 (ex 0001 , 0010, 0100, 1000)
				}
			}
		}
		System.out.println(depth-1);
	}
}