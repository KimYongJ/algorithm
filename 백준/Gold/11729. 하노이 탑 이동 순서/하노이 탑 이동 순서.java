//https://github.com/KimYongJ/algorithm
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
class Main{
 static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int count;
 public static void main(String[] args)throws Exception{
     Scanner sc = new Scanner(System.in);
     int n = sc.nextInt();
     bw.write(((1<<n)-1)+"");
     hanoi(n,'1','3','2'); // 1번에서 3번으로 간다.
     bw.flush();
     bw.close();
 }
 public static void hanoi(int n,char one, char three, char two)throws Exception {
 	if(n>0) {
 		hanoi(n-1,one,two,three); // 1번을 2번으로 먼저 옮긴다.
 		print(one,three);
 		hanoi(n-1,two,three,one); // 2번을 3번으로 옮긴다.
 	}
 }
 public static void print(char from, char to)throws Exception{
     bw.write('\n');
     bw.write(from);
     bw.write(' ');
     bw.write(to);
 }
}