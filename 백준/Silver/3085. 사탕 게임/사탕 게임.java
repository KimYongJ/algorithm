import java.io.*;
import java.util.*;

class Main{
    static int l;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        l = Integer.parseInt(br.readLine());
        char[][] arr = new char[l+2][l+2];
        for(int i=1; i<l+1; i++){
            String str = new StringTokenizer(br.readLine()).nextToken();
            for(int j=1; j<l+1; j++){
                arr[i][j] = str.charAt(j-1);
            }
        }
        System.out.println(find(arr));
    }
    public static int find(char[][] arr){
        int result = 0;
        for(int i=1; i<l+1; i++){
            int max = 0;
            char dummy;
            for(int j=1; j<l+1; j++){
                if(arr[i][j] != arr[i][j+1] && (int)arr[i][j+1] != 0){
                    dummy = arr[i][j];
                    arr[i][j] = arr[i][j+1];
                    arr[i][j+1] = dummy;
                    max = sameLine(arr);
                    if(result<max)
                        result = max;
                    dummy = arr[i][j];
                    arr[i][j] = arr[i][j+1];
                    arr[i][j+1] = dummy; 
                }
                if(arr[j][i] != arr[j+1][i] && (int)arr[j+1][i] != 0){
                    dummy = arr[j][i];
                    arr[j][i] = arr[j+1][i];
                    arr[j+1][i] = dummy;
                    max = sameLine(arr);
                    if(result<max)
                        result = max;
                    dummy = arr[j][i];
                    arr[j][i] = arr[j+1][i];
                    arr[j+1][i] = dummy; 
                }
                if(result==l) return result;
            }
        }
        
        return result;
    }
    public static int sameLine(char[][] arr){ // 가장 숫자 반환
        int result = 0;
        for(int i=1; i<l+1; i++){
        	int[] max = new int[2];
            for(int j=1; j<l+1;j++){
                if(arr[i][j]==arr[i][j-1] || (int)arr[i][j-1]==0){
                    max[0]++;
                    if(result<max[0]) result = max[0];
                }else{
                    max[0]=1;
                }
                if(arr[j][i]==arr[j-1][i] || (int)arr[j-1][i]==0){
                    max[1]++;
                    if(result<max[1]) result = max[1];
                }else{
                    max[1]=1;
                }
            }
        }
        return result;
    }
}