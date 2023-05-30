import java.io.*;
import java.util.*;

class Solution{
    static int[][] arr;
    static int n,k,cnt;
    static String str;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(st.nextToken());
        for(int i=1; i<=l; i++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            cnt = 0;
            for(int a=0; a<n; a++){
                st = new StringTokenizer(br.readLine());
                for(int b=0; b<n; b++){
                    arr[a][b] = Integer.parseInt(st.nextToken());
                    str += arr[a][b];
                }
                findOne(str);
                str = "";
            }
            for(int a=0; a<n; a++){
                for(int b=0; b<n; b++)
                    str += arr[b][a];
				findOne(str);
                str = "";
            }
            sb.append("#").append(i).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }
    public static int findOne(String str){
        str = str.replaceAll(" ","");
        for(String s : str.split("0"))
            if(s.length()==k)  cnt++;
        return cnt;
    }

}