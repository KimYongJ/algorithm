// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main{
	
	static int	len;
	static char arr[];
	static HashSet<String> set = new HashSet<>();
	
	public static void dfs(int left, int right, String order, String total) {
		if(left == -1 && right == len) {
			set.add(total);
			return;
		}
		if(left >= 0) 
			dfs( left - 1, right,arr[left]+order, total+" "+arr[left]+order);
		
		if(right < len)
			dfs(left, right + 1,order+arr[right], total+" "+order+arr[right]);
		
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr		= br.readLine().toCharArray();
		len		= arr.length;

		for(int i=0; i<len; i++) 
		{
			//if(arr[i] == 0) continue;
			
			dfs(i-1,i+1, ""+arr[i] , ""+arr[i]);
		}
		System.out.print(set.size());
	}
}