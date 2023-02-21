import java.util.*;
import java.math.*;
class Main{
    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);
        
        BigInteger x = sc.nextBigInteger();
        BigInteger y = sc.nextBigInteger();
        
        
        System.out.println(x.divide(y));
        System.out.println(x.remainder(y));
        
    }
    
}