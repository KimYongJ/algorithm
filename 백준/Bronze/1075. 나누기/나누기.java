import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int F = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(str.substring(0,str.length()-2)) * 100;
        
        for(int i=0; i<=99; i++, N++) {
        	if(N % F == 0) {
        		break;
        	}
        }
        System.out.print(String.format("%02d",N % 100));
    }
}