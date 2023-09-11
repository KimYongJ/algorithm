import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Loop : while(true){
            String num = br.readLine();
            if("0".equals(num)) break;
            int len = num.length();
            for(int i=0; i<len/2; i++){
                if(num.charAt(i)!= num.charAt(len-1-i)){
                    sb.append("no").append("\n");
                    continue Loop;
                }
            }
            sb.append("yes").append("\n");
        }
        System.out.println(sb.toString());
    }
}