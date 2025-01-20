//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22945
//1초 / 1024MB
//요약 : 배열에서 2명을 뽑는데, 뽑을 때 능력치가 최대가 되는 능력치를 출력, 능력치 공식 : [A,B사이 존재하는 다른 원소 수 *MIN(A,B능력치)]
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 사람수(2<=십만)
		int arr[]	= new int[N];						// 능력치(1<=만)
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int res = 0;
		int s = 0;
		int e = N - 1;
		while(s<e)
		{
			int min2 = Math.min(arr[e], arr[s]);
			int value = (e - s - 1) * min2;
			if(res < value)
				res = value;
			
			if(arr[e] < arr[s])
				--e;
			else
				++s;
			
		}
		System.out.print(res);
	}
}
