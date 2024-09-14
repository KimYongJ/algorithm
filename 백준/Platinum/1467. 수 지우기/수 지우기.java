//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1467
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Ncnt[] = new int[10];
		int Dcnt[] = new int[10];
		
		ArrayList<Integer> nlist = new ArrayList<>();
		ArrayList<Integer> result= new ArrayList<>();
		
		for(char c : br.readLine().toCharArray()) {
			int n = c-'0';
			nlist.add(n);
			Ncnt[n]++;
		}
		for(char c : br.readLine().toCharArray()) {
			Dcnt[c-'0']++;
		}
		
		for(int now : nlist) {
			if(Dcnt[now] > 0 && Ncnt[now] == Dcnt[now]) {
				Ncnt[now]--;
				Dcnt[now]--;
			}else {
				while(!result.isEmpty()) {
					int last = result.get(result.size() - 1);
					if(last >= now || Dcnt[last] == 0) {
						break;
					}else {
						Dcnt[last]--;
						result.remove(result.size()-1);
					}
				}

				result.add(now);
				Ncnt[now]--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int r : result)sb.append(r);
		System.out.print(sb.toString());
	}
}