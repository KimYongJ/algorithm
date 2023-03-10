import java.util.Scanner;

class Solution{
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        int x = new Scanner(System.in).nextInt();
        for(int i=1; i<=x/2; i++)
            if(x%i==0)	sb.append(i).append(" ");
        sb.append(x);
        System.out.println(sb);
    }
}