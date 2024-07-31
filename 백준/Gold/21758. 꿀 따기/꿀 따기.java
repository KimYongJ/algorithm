// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N];
		int inc[]	= new int[N];
		int dec[]	= new int[N];
		//////////////////////
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			inc[i] = dec[i] = arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1,j=N-2; i<N; i++,j--) 
		{
			inc[i] += inc[i-1];
			dec[j] += dec[j+1];
		}
		//////////////////////
		int max = 0;
		int dummyMax = 0;
		// 벌 벌 꿀통
		for(int i=N-2; i>=1; i--) {
			int leftBee = inc[N-1] - inc[0] - arr[i];
			int middleBee = inc[N-1] - inc[i];
			dummyMax = leftBee + middleBee;
			if(max < dummyMax) {
				max = dummyMax;
			}
		}
		// 꿀통 벌 벌
		for(int i=N-2; i>=1; i--) {
			int rightBee = dec[0] - dec[N-1] - arr[i];
			int middleBee = dec[0] - dec[i];
			dummyMax = rightBee + middleBee;
			if(max < dummyMax) {
				max = dummyMax;
			}
		}
		// 벌 꿀통 벌
		for(int i=N-2; i>=1; i--) {
			int rightBee = dec[i] - arr[N-1];
			int leftBee = inc[i] - arr[0];
			dummyMax = rightBee + leftBee;
			if(max < dummyMax) {
				max = dummyMax;
			}
		}

		System.out.print(max);
	}
}