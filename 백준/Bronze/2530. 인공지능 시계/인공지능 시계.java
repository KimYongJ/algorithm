import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt(), m = sc.nextInt(), s= sc.nextInt();
        int d = sc.nextInt();
        System.out.println((h+(m+(s+d)/60)/60)%24+" "+(m+(s+d)/60)%60+" "+(s+d)%60);
    }
}