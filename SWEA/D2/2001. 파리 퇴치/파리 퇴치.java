import java.io.*;
import java.util.*;
class Solution{
 	public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        
        for(int r = 0; r<l; r++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            int[][] arr = new int[n][n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++)
                    arr[i][j] = Integer.parseInt(st.nextToken());
            }
            int max = find(arr,n,m);
            bw.write("#"+(r+1)+" "+max+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static int find(int[][] arr,int n,int m){
        int max = 0;
        for(int i=0; i<(n-m)+1; i++)
            for(int j=0; j<(n-m)+1; j++){
                int sum = 0;
                for(int x=i; x<i+m; x++)
                    for(int y=j; y<j+m; y++)
                        sum += arr[x][y];
                if(max<sum) max = sum;
            }
        return max;
    }
}