//https://github.com/KimYongJ/algorithm
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];// 입력되는 값을 담을 배열
        int[] dp = new int[n+1];// 인덱스 마다의 최대길이를 dp로 담는다.
        int max = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=1; i<=n; i++){
            int min_idx = getIndex(arr,dp,i);// arr안에서 인덱스가 i값인데 arr[i]보다 작으면서 가장 큰 수를 찾는다.
            dp[i] = dp[min_idx] + 1;
            if(max<dp[i]){
                max = dp[i];
            }
        }
        System.out.println(max);
    }
    public static int getIndex(int[]arr,int[] dp ,int i){
        int idx = 0;
        int max = 0;
        for(int j=0; j<i; j++){
           if(arr[i]>arr[j] && dp[i]<dp[j]+1){
               if(dp[j]>max){
                   max = dp[j];
                   idx = j;
               }
           }
        }
        return idx;
    }
    
}