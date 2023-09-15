import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        int m = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            int x = Integer.parseInt(st.nextToken());
            // 2분탐색 시작
            int left = 0;
            int right = n-1;
            while(true){
                int base = (left+right)/2;
                if(x==arr[base]){
                    sb.append(1).append("\n");
                    break;
                }else if(left>=right){
                    sb.append(0).append("\n");
                    break;
                }
                if(x<arr[base]){
                    right = base-1;
                }else{
                    left = base+1;
                }
            }
            
        }
        
        System.out.println(sb.toString());
    }
}