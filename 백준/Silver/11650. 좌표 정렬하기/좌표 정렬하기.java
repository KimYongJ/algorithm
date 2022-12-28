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
            arr[q][0] = Integer.parseInt(s[0]);
            arr[q][1] = Integer.parseInt(s[1]);
        }
        
        Arrays.sort(arr, (x,y)->{
            if(x[0]==y[0]){
                return x[1]-y[1];
            }else{
                return x[0]-y[0];
            }
        });
        for(int i=0; i<l; i++)
            sb.append(arr[i][0]).append(" ").append(arr[i][1]).append("\n");
        System.out.println(sb);
//        int len = l;
//        for(int i=0; i<l-1;i++){
//            for(int j=0; j<l-1-i; j++){
//                if(arr[j][0]<arr[j+1][0]){
//                    int d = arr[j][0];
//                    arr[j][0] = arr[j+1][0];
//                    arr[j+1][0] = d;
//                    d = arr[j][1];
//                    arr[j][1] = arr[j+1][1];
//                    arr[j+1][1] = d;
//                }else if(arr[j][0] == arr[j+1][0])
//                    if(arr[j][1]<arr[j+1][1]){
//                        int d = arr[j][1];
//                        arr[j][1] = arr[j+1][1];
//                        arr[j+1][1] = d;
//                    }
//            }
//            sb.append(arr[--len][0]).append(" ").append(arr[len][1]).append("\n");
//        }
//        sb.append(arr[--len][0]).append(" ").append(arr[len][1]).append("\n");
//        System.out.println(sb);
    }
}