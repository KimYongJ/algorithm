import java.io.*;

class Solution{
    static int[][] arr;
    static int n , cnt, v=1;
    static StringBuilder sb = new StringBuilder();
 	public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        while(l-->0){
        	int d = n  = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            cnt = 1;
            firstLineInput();
            int left = 0, right = n-1;
           // boolean match = true;
            int lv = 1, rv = -1;
            while(d-->0){ // 달팽이 입력 로직 
                int repeat = d;
                // 처음 left ++ , right --
                while(repeat-->0){
                    arr[left][right] = cnt++;
                    left+=lv;
                }              
                repeat = d;
                while(repeat-->0){
                    arr[left][right] = cnt++;
                    right += rv;
                }
                lv *= -1;
                rv *= -1;
            }
            arr[left][right] = cnt; // 마지막 인덱스 추가
            makeString();
        }
        System.out.println(sb);
    }
    public static void firstLineInput(){
        for(int i=0; i<n-1; i++)
            arr[0][i] = cnt++;
    }
    public static void makeString(){
        sb.append("#").append(v++).append("\n");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                sb.append(arr[i][j]).append(" ");
            sb.append("\n");
        }
    }
}