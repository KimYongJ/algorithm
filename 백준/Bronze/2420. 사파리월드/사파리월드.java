import java.io.*;
import java.math.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        BigDecimal x = new BigDecimal(arr[0]);
        BigDecimal y = new BigDecimal(arr[1]);
        
        System.out.println((x.subtract(y).abs()));
        
    }
    
}