// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		String str;
		int arr[] = new int[3];
		
		while((str = br.readLine()) != null) {
			st		= new StringTokenizer(str);
			arr[0]	= Integer.parseInt(st.nextToken());
			arr[1]	= Integer.parseInt(st.nextToken());
			arr[2]	= Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			int left	= arr[1] - arr[0];
			int right	= arr[2] - arr[1];
			if(left < right) {
				sb.append(right - 1);
			}
			else {
				sb.append(left - 1);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}