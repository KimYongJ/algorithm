// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Main{
	
	
    static int read() throws Exception {
        int n = 0;
        boolean isNegative = false;
        while (true) {
            int input = System.in.read();
            if (input <= 32) {
                return isNegative ? n * -1 : n;
            } else if (input == '-')
                isNegative = true;
            else
                n = (n << 3) + (n << 1) + (input & 15);
        }
    }
	public static void main(String[] args)throws Exception{
		int N	  = read();
		int arr[] = new int[N];
		int left, right, num, cnt=0;
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		N--;
		for(int i=0; i<=N; i++)
		{
			left  = 0;
			right = N;
			while(left < right) 
			{
				if(i==left || i==right) {
					if(i == left)  left++;
					if(i == right) right--;
					continue;
				}
				num = arr[left] + arr[right];
				if(num == arr[i]) {
					cnt++;
					break;
				}
				else if(num > arr[i])
					right--;
				else 
					left++;
			}
		}
		System.out.print(cnt);
	}
}