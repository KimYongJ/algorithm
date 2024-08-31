//https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str[] = new String[5];
		
		for(int i=0; i<5; i++)
			str[i] = br.readLine();
		
		for(int i=0; i<15; i++)
			for(int j=0; j<5; j++)
				if(str[j].length() > i)
					sb.append(str[j].charAt(i));

		System.out.print(sb.toString());
	}
}