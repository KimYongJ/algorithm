// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static final char	maxArr[] = {'9','8','7','6','5','4','3','2','1','0'};
	static final char 	minArr[] = {'0','1','2','3','4','5','6','7','8','9'};
	static int			K, IDX;
	static String		result[];
	static char 		sign[], number[];
	static boolean 		visit[];
	
	public static boolean validate(int idx, int num) {
		if(idx<1) return true;
		
		else if(sign[idx-1] == '<')
			return number[idx-1] < num;
		else
			return number[idx-1] > num;
	}
	public static boolean backtracking(char arr[],int depth) {
		if(depth > K) 
		{
			result[IDX] = String.valueOf((number));
			return true;
		}
		for(int i=0; i<10; i++)
			if(!visit[i]) 				// 방문하지 않은 것만 진행
			{
				visit[i] = true;
				if(validate(depth, arr[i])) // 값 넣기 전 부등호 먼저 비교해 연산을 줄임
				{
					number[depth] = arr[i];	// 값 삽입
					if(backtracking(arr, depth + 1))
						return true;
				}
				visit[i] = false;
			}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K 		= Integer.parseInt(br.readLine());
		sign 	= new char[K];
		number	= new char[K+1];
		result	= new String[2];
		st 		= new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++)
			sign[i] = st.nextToken().charAt(0);
		
		visit	= new boolean[10];	// 방문 초기화
		backtracking(maxArr,0);		// MAX를 먼저 구해온다.
		
		IDX ++;						// 결과 배열의 인덱스 +1
		
		visit	= new boolean[10];	// 방문 초기화
		backtracking(minArr,0);		// MIN을 구한다.
		
		System.out.print(
							new StringBuilder().append(result[0])
							.append('\n').append(result[1]).toString()
						);
	}
}