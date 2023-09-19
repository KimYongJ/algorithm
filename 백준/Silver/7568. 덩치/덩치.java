import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int cnt = 1;
            for(int j=0; j<n; j++){
                if(i==j)continue;
                
                if(arr[i][0]<arr[j][0]&&arr[i][1]<arr[j][1])
                    cnt++;
            }
            sb.append(cnt).append(" ");
        }
        
        System.out.println(sb.toString());
    }
}