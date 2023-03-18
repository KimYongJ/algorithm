import java.util.Scanner;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        if(A%3 ==  (B+1)%3)
            System.out.println("A");
        else
            System.out.println("B");
    }
}