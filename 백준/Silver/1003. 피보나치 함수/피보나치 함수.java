//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
 public static void main(String[] args)throws Exception{
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringBuilder sb = new StringBuilder();
     int T = Integer.parseInt(br.readLine());
     int[] zero = new int[41];
     int[] one = new int[41];
     
     zero[0] = one[1] = 1;
     zero[1] = one[0] = 0;
     zero[2] = one[2] = 1;
     zero[3] = 1;
     one[3] = 2;
     for(int i=4; i<=40; i++){
         zero[i] = zero[i-1] + zero[i-2];
          one[i] =  one[i-1] +  one[i-2];
     }
     while(T-->0){
         int num = Integer.parseInt(br.readLine());
         sb.append(zero[num]).append(" ")
             .append(one[num]).append('\n');
     }
     
     System.out.println(sb);
 }
}