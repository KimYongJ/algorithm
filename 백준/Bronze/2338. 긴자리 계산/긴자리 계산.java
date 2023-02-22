import java.util.*;
import java.math.*;
class Main{
    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);
        BigDecimal x = sc.nextBigDecimal();
        BigDecimal y = sc.nextBigDecimal();
        
        System.out.println(x.add(y));
        System.out.println(x.subtract(y));
        System.out.println(x.multiply(y));
        
    }
}