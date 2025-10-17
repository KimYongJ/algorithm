//https://www.acmicpc.net/problem/9047
//1초 128MB
//3 // 테스트 케이스 수 (1<=20)
//6174// 4자리 숫자
//1789
//2005
//답
//0
//3
//7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) sb.append( dfs(0, Integer.parseInt(br.readLine()))).append('\n');
		
		System.out.print(sb);
	}
	static int dfs(int depth, int num) {
		if(num == 6174) return depth;
		
		int arr[] = new int[4];
		
		for(int i=0; i<4; i++)
		{
			arr[i] = num % 10;
			num /= 10;
		}
		
		Arrays.sort(arr);
		
		int max = arr[0] + arr[1] * 10 + arr[2] * 100 + arr[3] * 1000;
		int min = arr[3] + arr[2] * 10 + arr[1] * 100 + arr[0] * 1000;
		
		return dfs(depth + 1, max - min);
	}
}