//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15823
//1초 / 512mb
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;
class Main{
	
	static int N, M, arr[];
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());	// 카드수(1<=십만)
		M	= Integer.parseInt(st.nextToken());	// 목표 카드팩수(1<=N)
		arr	= new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());//1<=50만
		
		int s	= 1;
		int e	= N / M;
		int res = 0;
		
		while(s <= e)
		{
			//하나의 카드팩에 들어갈 수 있는 카드의 수
			int mid = (s + e) >> 1;
			if(validate(mid))
			{
				s = mid + 1;
				res = mid;
			}
			else e = mid - 1;
		}
		System.out.print(res);
	}
	public static boolean validate(int cnt) {
		// 하나의 카드팩에 cnt개가 들어갈 수 있느냐 ? 
		// 그렇게 M개의 카드팩을 구매할 수 있느냐 ? 
		// 한 카드팩 안에 같은 종류 카드 불가
		int Mcnt = 0;
		int s = 0;
		int e = 0;
		BitSet bit = new BitSet();
		while(s<N && e<N)
		{
			if(bit.get(arr[e]))
			{
				while(arr[s] != arr[e])
					bit.flip(arr[s++]);
				
				++s;
			}
			
			bit.set(arr[e], true);
			
			int len = e - s + 1;
			if(cnt <= len)
			{
				Mcnt++;
				s = ++e;
				bit.clear();
			}
			else
				++e;
		}
		return M <= Mcnt;
	}
}