// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Main{

	static StringBuilder sb = new StringBuilder();
	static int N, M;
	
	public static void main(String[] args)throws Exception{
		N = read();
		M = read();	
		int number[] = new int[N];
		int result[] = new int[M];

		for(int i=0; i<N; i++)
			number[i] = read();
		
		Arrays.sort(number);
		
		DFS(0, 0, number, result);
		
		System.out.println(sb);
	}
	public static void DFS(int depth, int start, int[] number, int[] result) {
		if(depth == M) { // M개를 다고른 경우 출력 후 종료
			for(int n : result) 
				sb.append(n).append(' ');
			sb.append('\n');
			return;
		}
		for(int i=start; i<N; i++) {// i는 전달되는 start 인자 부터 시작토록 한다. 
			result[depth] = number[i]; // result에 깊이만큼 담는다.
			DFS(depth+1, i, number, result); // 깊이를 1 추가하여 전달, 이 때 start는 i값을 그대로 한다. i값을 +1할 경우 종복된 숫자가 나오지 않는다.
		}
	}
	// 빠른 입력을 위해 만듦
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}