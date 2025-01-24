//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27085
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 방개수(1<=이십만)
		int S		= Integer.parseInt(st.nextToken());	// 현재 위치(1<=N)
		int arr[]	= new int[N+1];

		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		long leftMax	= 0;
		long rightMax	= 0;
		long leftSum	= 0;
		long rightSum	= 0;
		int leftIdx		= S;
		int rightIdx	= S;
		boolean isChanged = true;
		
		while(isChanged)
		{
			isChanged = false;
			while(0 < leftIdx && leftSum + rightMax + arr[leftIdx] >= 0) {
				isChanged = true;
				leftSum += arr[leftIdx--];
				if(leftMax < leftSum)
					leftMax = leftSum;
			}
			while(rightIdx<=N && rightSum + leftMax + arr[rightIdx] >= 0) {
				isChanged = true;
				rightSum += arr[rightIdx++];
				if(rightMax < rightSum)
					rightMax = rightSum;
			}
		}
		
		System.out.print(leftMax + rightMax);
	}
}
