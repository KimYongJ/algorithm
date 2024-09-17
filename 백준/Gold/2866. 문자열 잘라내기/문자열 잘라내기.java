//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2866
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	public static boolean check(char arr[][], int mid,int Y, int X)
	{
		HashSet<String> set = new HashSet<>();
		for(int x=0; x<X; x++) {
			StringBuilder sb = new StringBuilder();
			for(int y= mid;y<Y; y++) {
				sb.append(arr[y][x]);
			}
			if(set.contains(sb.toString())) {
				return false;
			}
			set.add(sb.toString());
		}
		return true;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y = Integer.parseInt(st.nextToken());	// 2<=천
		int X = Integer.parseInt(st.nextToken());	// 2<=천
		char arr[][] = new char[Y][X];
		
		for(int y=0; y<Y; y++)
			arr[y] = br.readLine().toCharArray();
		
		int s = 1;
		int e = Y - 1;
		int res = 0; // 지울 수 있는 가장 밑에 행
		while(s <= e) {
			int mid = (s + e) >> 1;
			if(check(arr, mid,Y, X)) {
				res = mid;
				s = mid + 1;
			}else {
				e = mid - 1;
			}
		}
		
		 System.out.print(res);
	}
}