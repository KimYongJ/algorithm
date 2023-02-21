import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong(); 
        long B = sc.nextLong(); 
        long C = sc.nextLong();
        System.out.println(cal(A, B, C) % C);
    }

    private static long cal(long a, long b, long c) {
        if (b == 0) {
            return 1;
        } else if (b == 1) {
            return a;
        } else if (b % 2 == 0) {
            long n = cal(a, b / 2, c) % c;
            return (n * n) % c;
        } else { 
            long n = cal(a, b / 2, c) % c;
            return (((n * n) % c) * a) % c;
        }
    }
}