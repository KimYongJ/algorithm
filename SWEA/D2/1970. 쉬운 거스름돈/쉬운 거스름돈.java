import java.io.*;

class Solution{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int x = 1; x<=l; x++){
            sb.append("#").append(x).append("\n");
            int num = Integer.parseInt(br.readLine());
            int base = 50000;
            boolean baseCheck = false; // false일 때 base나누기 5, true일때 base나누기 2
            while(base>9){
                sb.append( num/base ).append(" ");
                num %= base;
                base /= baseCheck ? 2 : 5;
                baseCheck = !baseCheck;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}