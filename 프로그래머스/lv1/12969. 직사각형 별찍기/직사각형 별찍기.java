import java.util.*;

class Solution {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        for(int x=0; x<b; x++){
            for(int i=0; i<a; i++)
                sb.append("*");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}