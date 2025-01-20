//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15831
//1초 / 512MB
//요약 : 'B'문자는 B개이하로, 'W'문자는 W개이상을 갖는 가장 긴 연속된 구간의 길이 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 조약돌개수(1<=삼십만)
		int B		= Integer.parseInt(st.nextToken());	// 검은조약돌 최대 수(0<=B+W<=N)
		int W		= Integer.parseInt(st.nextToken());	// 흰 조약돌 최소 개수(0<=B+W<=N)
		int bCnt	= 0;
		int wCnt	= 0;
		char arr[]	= br.readLine().toCharArray();
		int len		= 0;
		
		int s = 0;
		int e = 0;
		while(e<N)
		{
			if(arr[e++] == 'B')
				bCnt++;
			else wCnt++;
			
			if(B < bCnt){
				while(B < bCnt)
				{
					if(arr[s++] == 'B')
						bCnt--;
					else
						wCnt--;
				}
			}

			if(W <= wCnt)
				len = Math.max(len, e - s);
		}

		System.out.print(len);
	}
}
