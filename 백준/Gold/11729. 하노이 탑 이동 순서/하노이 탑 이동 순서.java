// https://github.com/KimYongJ/algorithm
import java.util.Scanner;
class Main{
	static StringBuilder sb = new StringBuilder();
	static int count;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        hanoi(n,1,3,2); // 1번에서 3번으로 간다.
        
        System.out.println(count);
        System.out.println(sb);
    }
    public static void hanoi(int n,int one, int three, int two) {
    	if(n>0) {
    		hanoi(n-1,one,two,three); // 1번을 2번으로 먼저 옮긴다.
    		count++;
    		sb.append(one).append(" ").append(three).append("\n");
    		hanoi(n-1,two,three,one); // 2번을 3번으로 옮긴다.
    	}
    }
}