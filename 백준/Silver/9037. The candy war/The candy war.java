//https://www.acmicpc.net/problem/9037
//1초 128MB
//4 // 테스트 케이스
//5 // 아이 인원 1<=10
//2 4 7 8 9 // 초기 사탕 개수 1<=30
//1
//9
//6
//10 5 13 2 7 8
//4
//3 4 4 3
//답
//6
//0
//4
//0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());
			int arr[] = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			int round = 0;
			add(arr);
			while(!isSame(arr))
			{
				turn(arr);
				add(arr);
				round++;
			}
			sb.append(round).append('\n');
		}
		System.out.print(sb);
	}
	static void turn(int[] arr)
	{
		int brr[] = new int[arr.length];
		for(int i=0; i<arr.length-1; i++)
			brr[i + 1] = arr[i] / 2;

		brr[0] = arr[arr.length-1] / 2;
		
		for(int i=0; i<arr.length; i++)
			arr[i] = arr[i] / 2 + brr[i];
	}
	static void add(int[] arr) {
		for(int i=0; i<arr.length; i++)
			if((arr[i] & 1) == 1)
				++arr[i];
	}
	static boolean isSame(int[] arr)
	{
		for(int i=1; i<arr.length; i++)
			if(arr[i-1] != arr[i])
				return false;

		return arr[arr.length-1] == arr[0];
	}
}