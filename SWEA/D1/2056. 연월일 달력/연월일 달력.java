import java.io.*;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] check = {31,28,31,30,31,30,31,31,30,31,30,31};
        int l = Integer.parseInt(br.readLine());
        String[] arr = new String[l];
        int cnt = 1;
        
        for(int i=0; i<l; i++)
            arr[i] = br.readLine();
        
        for(String str : arr){
            String year = str.substring(0,4);
            String month = str.substring(4,6);
            String day = str.substring(6,8);
            if(Integer.parseInt(month)>12 || Integer.parseInt(month)<1 ){
                sb.append("#").append(cnt++).append(" ").append(-1).append("\n");
                continue;
            }else if( Integer.parseInt(day)>check[Integer.parseInt(month)-1] ||  Integer.parseInt(day)<1){
                sb.append("#").append(cnt++).append(" ").append(-1).append("\n");
                continue;
            }
            sb.append("#").append(cnt++).append(" ").append(year).append("/").append(month).append("/").append(day).append("\n");
        }
        System.out.println(sb);
    }
}