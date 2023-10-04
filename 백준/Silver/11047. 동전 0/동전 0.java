// https://github.com/KimYongJ/algorithm
import java.util.Scanner;
class Main{
    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        
        int result = 0;
        
        for(int i=n-1; i>=0; i--){
            result += k/arr[i];
            k%=arr[i];
        }
        
        System.out.print(result);
    }
}