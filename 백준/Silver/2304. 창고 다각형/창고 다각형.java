//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2304
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N	= Integer.parseInt(br.readLine());
		int H[] = new int[1001];
		int len = 0;
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i	= Integer.parseInt(st.nextToken());	// 가로 인덱스
			H[i]	= Integer.parseInt(st.nextToken());	// 높이
			len		= Math.max(len, i);
		}
		
		int res = 0;
		int h	= 0;
		int IDX	= 0;
		
		for(int i=0; i<=len; i++)
			if(h < H[i])				// 기존 높이보다 큰것이 나타나면
			{
				res += (i - IDX) * h;
				h	= H[i];				// 높이 다시 세팅
				IDX	= i;				// 시작 인덱스 다시 세팅
			}
		
		int sIdx= 0;
		h = 0;
		for(int i=len; i>=IDX; i--)
			if(h < H[i])
			{
				res		+= (sIdx - i) * h;
				h		= H[i];
				sIdx	= i;
				if(IDX == i)
					res += h;
			}
			else if(IDX == i)
				res += (sIdx - i + 1) * h;
			

		System.out.print(res);
	}
}