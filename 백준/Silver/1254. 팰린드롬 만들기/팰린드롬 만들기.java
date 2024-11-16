//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14926
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char arr[]	= br.readLine().toCharArray();
		int len		= arr.length-1;
		int ans		= arr.length;
		
		for(int i=0; i<=len; ++i, ++ans)
			if(isPal(arr, i, len))
				break;

		System.out.print(ans);
	}
	public static boolean isPal(char arr[], int l, int r) {
		while(l < r) {
			if(arr[l] != arr[r])
				return false;
			++l;
			--r;
		}
		return true;
	}
}