// https://github.com/KimYongJ/algorithm
/*
 * 자신이 몇번 째 인지 알 수 있는 학생의 조건은, 단방향 연결을 정방향과 역방향으로 하여
 * 연결 관계를 파악했을 때, 특정 노드 X가 어떻게든 모든 노드로 도달 할 수 있으면 된다.
 * 크던 작던 상관없이 모든 노드에 대해 연결이 되어있어야(관계파악이 가능해야) 한다.
 * 이를 해결하기 위해 노드 하나당 다익스트라나, 벨만포드를 쓰면 느리다. 
 * 플로이드 워셜로 해결가능하다. 노드가 500개일 때 플로이드워셜 사용시 500의 3제곱이기 때문에
 * 시간 초과가 뜰거 같지만(1억 연산이상이기에) N이 500까지는 시간초과를 피할 수 있다 본다.
 * 보통 플로이드 워셜 사용시 map을 INF로 초기화 하고, 자기는 0으로 초기화 하지만 이번엔 거리가 아니라
 * 단순 연결이기 때문에 boolean으로 가능하다. 연결이 되었냐 안되었냐만 확인하면 되기 때문
 * 
 * 
 * */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[]args)throws Exception{new Main().solution();}
    
    int N, M, a, b;
    boolean map[][];
    public void solution()throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N+1][N+1];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            map[a][b] = true; // 단방향 연결로 진행한다.
        }
        
        for(int k=1; k<=N; k++)
	        for(int i=1; i<=N; i++) 
	        {
	        	if(i==k) continue; 				// 빠른 연산을 위해 자기 자신은 스킵
	            for(int j=1; j<=N; j++)
	            {
	            	if(j==k || j==i) continue; 	// 빠른 연산을 위해 j가 나머지랑 같다면 스킵
	                if(map[i][k] && map[k][j])
	                	map[i][j] = true;
	            }
	        }

        int cnt, result = 0;
        for(int i=1; i<=N; i++)
        {
        	cnt = 0;							// 어떻게든 연결되어 있는 것이 자기를 제외하고(N-1)전부여야 하기에 체킹
            for(int j=1; j<=N; j++)
            {
            	if(i==j)continue;				// 자기 자신 스킵
                if(map[i][j] || map[j][i])		// 어떻게든 연결되어있다면 +1
                    cnt++;
            }
            if(cnt == N-1)  					// 자기를 제외하고 모든 노드에 도달 가능하면 결과에 +1
            	result++;
        }
        System.out.println(result);
    }
    
}