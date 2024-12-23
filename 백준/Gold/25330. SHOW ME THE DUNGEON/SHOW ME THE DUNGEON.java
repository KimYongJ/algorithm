//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25330
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, K, res;
	static int[] attack, people;
	static boolean[] visit;
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	//몬스터수 1<=20
		K		= Integer.parseInt(st.nextToken());	//초기 체력1<=십만
		attack	= new int[N];
		people	= new int[N];
		visit	= new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			attack[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			people[i] = Integer.parseInt(st.nextToken());
		
		back(0, K, 0, 0);
		
		System.out.print(res);
	}
	public static void back(int depth, int energy, int damageSum, int peopleCnt) {
		res = Math.max(res, peopleCnt);
        
		if(energy <= 0 || depth == N)
			return;
		
		int remain = 0;
		
		
		for(int i=0; i<N; i++)
			if(!visit[i] && 0<= energy - attack[i])
				remain += people[i];

		if(remain + peopleCnt <= res)
			return;
		
		for(int i=0; i<N; i++)
			if(!visit[i])
			{
				int damage = damageSum + attack[i];
				int nextEnergy = energy - damage;
				if(0 <= nextEnergy)
				{
					int nextPeople = peopleCnt + people[i];
					visit[i] = true;
					back(depth + 1, nextEnergy, damage, nextPeople);
					visit[i] = false;
				}
			}
	}
}