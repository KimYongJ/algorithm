import java.io.*;
class Solution{
    static int[][] arr;
 	public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        arr = new int[l][l];
        for(int r=1; r<=l; r++){
            int pascalnum = Integer.parseInt(br.readLine());
            pascal(pascalnum);
            
            sb.append("#").append(r).append("\n");
            for(int i=0; i<pascalnum; i++){
                for(int j=0; j<i+1; j++)
                    sb.append(arr[i][j]).append(" ");
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
    public static void pascal(int n){
        arr[0][0] = 1;
        for(int r = 1; r<n; r++){
            arr[r][0] = 1;
            for(int i=1; i<r+1; i++)
                arr[r][i] = arr[r-1][i] + arr[r-1][i-1];
            }
     }

}