import java.util.Scanner;

class Solution{
 	public static void main(String[] args){
        int x =  new Scanner(System.in).nextInt();
        for(int i=0; i<=x; i++)
            System.out.print((1<<i)+" ");
        
    }
}