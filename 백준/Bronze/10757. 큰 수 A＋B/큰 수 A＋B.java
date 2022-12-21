import java.io.*;
import java.math.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        BigInteger big1 = new BigInteger(str[0]);
        BigInteger big2 = new BigInteger(str[1]);
        
        System.out.println(big1.add(big2));        
        
    }
}