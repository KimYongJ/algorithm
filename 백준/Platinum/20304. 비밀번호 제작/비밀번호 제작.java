// https://github.com/KimYongJ/algorithm
// 0부터 N 사이 까지 비밀번호를 내마음대로 정할 수 있다(0,1,2,3,4, ... , N까지 가능) 
// 집합N(지금까지 시도되지 않은 비번들)에 대해 집합M(지금까지 시도된 비밀번호들)으로 부터 안전거리가 최대인 것을 구한다. 
// 문제 요구사항은 가장 먼 거리만 구하는 것이지 가장 먼 특정 숫자(0~N사이숫자)를 구하는 것이 아니다.
// 해커가 대입한 모든 숫자들을 하나의 그룹으로 보고, 그 그룹에서 안전거리가 최대인 값만 구하는것이다.(범위는 집합N 안쪽)
// 그럼 집합 M(해커가 이미 대입한 비번들)의 숫자들에 대해서 각각 한번씩 BFS를 돌면서 가장 먼 수를 구하면된다.
// 집합M의 각 숫자들에 대해서 각각 1단계씩 떨어진 숫자들을 구해나간다.
import java.util.ArrayDeque;
public class Main {
	static int N, M, num, flag, size, depth, next;
	static boolean visit[];
	static ArrayDeque<Integer> q;
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void main(String[] args)throws Exception{
		N     = read();
		M     = read();
		visit = new boolean[N+1];
		q     = new ArrayDeque<>();
		
		for(int i=0; i<M; i++) 
		{
			next = read();							// 이미 대입해본 비번을 입력
			visit[next] = true;						// 해당 비번 방문처리
			q.add(next);							// 대입해본 비번을 통해 가장 먼거리를 구할 것이기 때문에 큐에 추가 
		}
		
		while(!q.isEmpty()) 
		{
			depth++;								// 해커가 대입해본 비번들로 부터 1단계씩 떨어진 숫자들을 구해나간다.
			size = q.size();						// 한 단계씩 처리하기 위해 큐의 사이즈만큼만 반복 
			for(int i=0; i<size; i++) 
			{
				flag=1;								// XOR(^)연산을 위해 사용할 flag
				num = q.poll();						// 해커가 대입해본 숫자를꺼낸다( 추후에는 해커가 대입해본 숫자로 부터 계속 1씩 떨어진 숫자를 꺼내게 된다 )
				while(flag<=N) 
				{
					next = flag^num;				// num(큐에서 꺼낸 데이터)에 대해 1만큼 떨어진 숫자를 구한다. xor연산을 통해 1비트가 다른 것만 가져옴 이 때 flag는 1부터 N까지 증가시켜 구하기에  특정num에 대해 모든 1비트 다른 숫자를 가져올 수 있음	
					if(next <= N && !visit[next])	// num과 1만큼 차이나는 숫자가 N보다 작거나같고 방문하지 않았을 때 연산 
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