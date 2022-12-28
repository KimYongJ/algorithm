import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        int[][] arr = new int[l][2];
        
        for(int q=0; q<l; q++){
            String[] s = br.readLine().split(" ");
            arr[q][1]=Integer.parseInt(s[0]);
            arr[q][0]=Integer.parseInt(s[1]);
        }
        
        Arrays.sort(arr,(x,y)->{
            if(x[0]==y[0]){
                return x[1]-y[1];
            }else{
                return x[0]-y[0];
            }
        });
        
        for(int i=0; i<l; i++)
            sb.append(arr[i][1]).append(" ").append(arr[i][0]).append("\n");
        System.out.println(sb);
        
        
        
    }
}