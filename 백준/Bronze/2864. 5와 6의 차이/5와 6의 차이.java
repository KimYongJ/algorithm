//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		StringBuilder sb	= new StringBuilder();
		String str1 = st.nextToken();
		String str2 = st.nextToken();
		
		sb.append(Integer.parseInt(str1.replaceAll("6", "5"))+Integer.parseInt(str2.replaceAll("6", "5")));
		sb.append(' ');
		sb.append(Integer.parseInt(str1.replaceAll("5", "6"))+Integer.parseInt(str2.replaceAll("5", "6")));
		System.out.print(sb.toString());
	}
}