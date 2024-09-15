//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/17266
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static boolean check(int pos[], int mid, int N) {
		int s = 0;// 가장왼쪽
		for(int p : pos) {
			if(p - mid > s) {
				return false;
			}
			s = p + mid;
		}
		return pos[pos.length-1] + mid >= N;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 굴다리 길이 (1<=십만)
		int M		= Integer.parseInt(br.readLine());	// 가로등의 개수 M (1<=N)
		int pos[]	= new int[M];						// 가로등의 위치(오름차순 정렬)
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
		{
			pos[i] = Integer.parseInt(st.nextToken());
		}
		
		int res	= 0;
		int s	= 0;
		int e	= N;
		while(s <= e)
		{
			int mid = (s + e) / 2;
			if(check(pos, mid, N)) {
				res = mid;
				e = mid - 1;
			}else {
				s = mid + 1;
			}
		}
		System.out.print(res);
	}
}