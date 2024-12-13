//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2450
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final int comb[][] = {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		int CNT[]	= new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			CNT[arr[i]]++;
		}
		
		int min = 1<<30;
		for(int c[] : comb)
		{
			int changeCnt[] = new int[4];
			int cnt = 0;
			// 첫번째 구간 나와야할 숫자가 안나오면, 그 안나온 숫자의 개수를 세준다.
			for(int i=0; i<CNT[c[0]]; i++)
				if(arr[i] != c[0])
				{
					++changeCnt[arr[i]];	// 바꾼 그 숫자를 더 한다.
					++cnt; // 결과 추가
				}
			for(int i=CNT[c[0]]; i<CNT[c[1]] + CNT[c[0]]; i++)
				if(arr[i] == c[0])
				{
					if(changeCnt[c[1]] != 0)
						--changeCnt[c[1]];
					else ++cnt;
				}
				else if(arr[i] == c[2])
				{
					++cnt;
				}
			
			if(cnt < min)
				min = cnt;
		}
		System.out.print(min);
	}
}
