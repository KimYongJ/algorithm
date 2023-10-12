//https://github.com/KimYongJ/algorithm
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
class Main{

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args)throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
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