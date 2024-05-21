// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final char 	arr[] = {'0','1','2','3','4','5','6','7','8','9'};
	static int			K;
	static String		MAX, MIN;
	static char 		sign[], number[];
	static boolean 		visit[];
	
	public static boolean validate(int idx, int num) {
		if(idx<1) return true;
		
		else if(sign[idx-1] == '<')
			return number[idx-1] < num;
		else
			return number[idx-1] > num;
	}
	public static void backtracking(int depth) {
		if(depth > K) 
		{
			MAX = String.valueOf((number));
			if("".equals(MIN))
				MIN = MAX;
			return;
		}
		for(int i=0; i<10; i++)
			if(!visit[i]) 				// 방문하지 않은 것만 진행
			{
				visit[i] = true;
				if(validate(depth, arr[i])) // 값 넣기 전 부등호 먼저 비교해 연산을 줄임
				{
					number[depth] = arr[i];	// 값 삽입
					backtracking(depth + 1);// 재귀
				}
				visit[i] = false;
			}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K 		= Integer.parseInt(br.readLine());
		sign 	= new char[K];
		number	= new char[K+1];
		visit	= new boolean[10];
		MIN 	= MAX = "";
		st 		= new StringTokenizer(br.readLine());
		
		for(int i=0; i<K; i++)
			sign[i] = st.nextToken().charAt(0);
		
		backtracking(0);
		
		System.out.print(
							new StringBuilder().append(MAX)
							.append('\n').append(MIN).toString()
						);
	}
}