//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16639
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine()); // N(1 ≤ N ≤ 19)
		char arr[]	= br.readLine().toCharArray();
		int min[][]	= new int[N][N];
		int max[][] = new int[N][N];
		int tmp[]	= new int[4];
		for(int i=0; i<N; i++)
		{
			Arrays.fill(min[i], Integer.MAX_VALUE);
			Arrays.fill(max[i], Integer.MIN_VALUE);
		}
		
		for(int i=0; i<N; i+=2)
			max[i][i] = min[i][i] = arr[i] - '0';
		
		for(int j=2; j<N; j+=2)
			for(int i=0; i<N-j; i+=2)
				for(int k=2; k<=j; k+=2)
				{
					char op = arr[i + k - 1];
					tmp[0] = cal(max[i][i+k-2], max[i+k][i+j], op);
					tmp[1] = cal(min[i][i+k-2], max[i+k][i+j], op);
					tmp[2] = cal(max[i][i+k-2], min[i+k][i+j], op);
					tmp[3] = cal(min[i][i+k-2], min[i+k][i+j], op);
					
					Arrays.sort(tmp);
					
					max[i][i+j] = Math.max(max[i][i+j], tmp[3]);
					min[i][i+j] = Math.min(min[i][i+j], tmp[0]);
				}

		System.out.print(max[0][N-1]);
	}
	public static int cal(int num1, int num2, char cal) {
		switch(cal)
		{
		case '+': return num1+num2;
		case '-': return num1-num2;
		default: return num1*num2;
		}
	}
}