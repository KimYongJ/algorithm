// https://github.com/kimyongj
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution{
    static int[] number = {2,3,5,7,11};
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1;i<=l; i++){
            int[] arr = new int[5];
            int num = Integer.parseInt(br.readLine());
            int idx = 4;
            while(num!=0){
                int dummy = num;
                
                if(num%number[idx]==0){
                    arr[idx]++;
                    num/=number[idx];
                }
                if(num==1) break;

                if(dummy==num) idx--;
                
            }           
            sb.append("#").append(i).append(" ").append(arr[0]).append(" ").append(arr[1]).
                append(" ").append(arr[2]).append(" ").append(arr[3]).append(" ").append(arr[4]).append("\n");
        }
        System.out.println(sb);
    }
}