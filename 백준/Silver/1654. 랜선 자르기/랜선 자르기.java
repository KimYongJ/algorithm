import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long hi = 0;
        
        int[] arr = new int[k];
        
        for(int i=0; i<k; i++){
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i]>hi){
                hi = arr[i];
            }
        }
        hi++;
        long low = 0;
        long mid = 0;
        while(low<hi){
            
            mid = (low+hi)/2;
            
            long cnt = 0;
            for(int a : arr){
                cnt += a/mid;
            }
            
            if(cnt<n){
                hi = mid;
            }else{
                low = mid+1;
            }
            
        }
        System.out.println(low-1);
    }
}