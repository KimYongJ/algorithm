//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25206
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		HashMap<String, Double> hm = new HashMap<>() {{
			put("A+",4.5);
			put("A0",4.0);
			put("B+",3.5);
			put("B0",3.0);
			put("C+",2.5);
			put("C0",2.0);
			put("D+",1.5);
			put("D0",1.0);
			put("F",0.0);
		}};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 20;
		double sum = 0, total = 0, sc, va;
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			sc = Double.parseDouble(st.nextToken());
			String g = st.nextToken();
			if("P".equals(g))
				continue;
			va = hm.get(g);
			total += sc;
			sum += sc*va;
		}
		System.out.print(sum / total);
	}
}