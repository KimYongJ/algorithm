import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            int n = scanner.nextInt();
            if (n == -1) { // 입력 값이 -1이면 반복문 종료
                break;
            }
            
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                if (n % i == 0) {
                    arr.add(i);
                }
            }
            
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            
            if (sum == n) {
                System.out.print(n + " = ");
                for (int i = 0; i < arr.size(); i++) {
                    System.out.print(arr.get(i));
                    if (i != arr.size() - 1) {
                        System.out.print(" + ");
                    }
                }
                System.out.println();
            } else {
                System.out.println(n + " is NOT perfect.");
            }
        }
        
        scanner.close();
    }
}