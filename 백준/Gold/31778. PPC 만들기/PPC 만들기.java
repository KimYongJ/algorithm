//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31778
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 길이(1<=이십만)
		int K		= Integer.parseInt(st.nextToken());	// 치환 최대사용 횟수(1<=N)
		char arr[]	= br.readLine().toCharArray();
		
		int s = 0;
		int e = N-1;
		while(--K>=0)
		{
			while(s<N && arr[s] != 'C')++s;
			while(0<=e && arr[e] != 'P')--e;

			if(e<=s || arr[s] !='C' || arr[e] !='P')
				break;
			arr[s] = 'P';
			arr[e] = 'C';
		}
		
        long a = 0;
        long b = 0;
        long c = 0;
        for (char cur : arr)
        {
            if (cur == 'P') b += a++;
            else c += b;
        }
        System.out.println(c);
	}
}
