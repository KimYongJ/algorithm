
public class Main {
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
    public static void main(String[] args) throws Exception {
        System.out.print(new StringBuilder().append(read()).append('\n').append(1).toString());
    }
}