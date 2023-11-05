// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{

    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int total = Integer.parseInt(st.nextToken());
    	int[] arr = new int[n];
    	int left = 0, right = 0, mid = 0;
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(right<arr[i])
            	right = arr[i];
    	}

        while(left<right){
        	mid = (left+right)/2;
            long sum = 0;
            for(int a: arr){
                int num =a-mid;
                if(num>0){
                    sum += num;
                }
            }
            if(sum<total){
                right = mid;
            }else if(total<=sum){
                left = mid+1;
            }
            
            
        }
        System.out.println(left-1);
    }
}