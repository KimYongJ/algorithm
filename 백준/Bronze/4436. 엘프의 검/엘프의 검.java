//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/4436
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static HashSet<Long> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        while ((s = br.readLine()) != null)
        {
        	for (long i = 0; i < 10; i++)
        	{
        		set.add(i);
        	}
            long n = Integer.parseInt(s);
            int k = 0;
            while (!set.isEmpty())
            {
            	calc(n * ++k);
            }
            sb.append(k).append("\n");
        }
        System.out.print(sb);
    }
    private static void calc(long n) {
        do set.remove(n % 10); while ((n /= 10) > 0);
    }
}
