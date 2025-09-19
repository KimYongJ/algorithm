//https://www.acmicpc.net/problem/17202
//2초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arr[] = merge(br.readLine(), br.readLine());
		
		int len = arr.length;
		
		while(len != 2)
		{
			for(int i=1; i<len; i++) {
				arr[i-1] += arr[i];
				arr[i-1] %= 10;
			}
			
			--len;
		}
		
		System.out.print(arr[0]);
		System.out.print(arr[1]);
	}
	static int[] merge(String s1, String s2) {
		int arr[] = new int[s1.length() * 2];
		
		for(int i=0, j = 0; i<s1.length(); i++)
		{
			arr[j++] = s1.charAt(i) - '0';
			arr[j++] = s2.charAt(i) - '0';
		}
		return arr;
	}
}