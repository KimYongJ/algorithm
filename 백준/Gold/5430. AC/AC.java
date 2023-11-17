// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			String cmd = br.readLine();
			int N = Integer.parseInt(br.readLine());
			String before1 = br.readLine();
			String[] before2 = before1.substring(1,before1.length()-1).split(",");
			int startIndex = 0;
			int endIndex = N;
			boolean error = false;
			boolean flag = false; // false : 정방향 / true : 역방향
			for(int i=0; i<cmd.length(); i++) {
				char c = cmd.charAt(i);
				if(startIndex>endIndex || (c=='D' && startIndex == endIndex)) {
					error = true;
					break;
				}
				if( c == 'R') flag = !flag;
				else {
					if(flag) endIndex--;
					else startIndex++;
				}
			}
			if(error) {
				bw.write("error\n");
			}else {
				sb.append('[');
				if(!flag) {
					for(int i=startIndex; i<endIndex; i++)
						sb.append(before2[i]).append(',');
				}else {
					for(int i=endIndex-1; i>=startIndex; i--)
						sb.append(before2[i]).append(',');
				}
				if(startIndex!=endIndex)sb.deleteCharAt(sb.length() - 1);
				sb.append(']').append('\n');
				bw.write(sb.toString());
				sb = new StringBuilder();
			}
		}
		bw.flush();
		bw.close();
	}
}