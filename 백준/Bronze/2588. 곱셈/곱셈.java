import java.util.*;
public class Main{

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int m = b/10;
        int n = b%10;
        System.out.println(a*n);
        while(!(m<=0)) {
        	n=m%10;
        	System.out.println(a*n);
        	m/=10;
        }
        System.out.println(a*b);
	}
}
