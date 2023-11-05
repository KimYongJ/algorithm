//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = 0, left = 0, right = 0, mid;
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            s += arr[i];
            if(arr[i]>right)
            	right = arr[i];
        }
        int max = Integer.parseInt(br.readLine());
        if(max>=s) {
        	System.out.println(right);
        	return;
        }

        while(left<=right){
            mid = right+left>>1;
            
            int sum = 0;
            for(int a : arr){
                sum += mid-a<0 ? mid : a;
            }
            if(sum>max){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        System.out.println(left-1);
    }
}